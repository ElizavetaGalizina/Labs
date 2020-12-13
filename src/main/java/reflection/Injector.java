package reflection;

import contracts.*;
import enums.Channels;
import workwithfile.ReadFile;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration(packages = {"sort","validators"})
/**
 * класс для инъекции зависимостей.
 */
public class Injector {
    /**
     * Лист классов.
     */
    public List<Object> list = new ArrayList<>();

    /**
     * Конструктор без параметров.
     */
    public Injector() {
        if (this.getClass().isAnnotationPresent(Configuration.class)) {
            Configuration annotation = this.getClass().getAnnotation(Configuration.class);

            for (String packages : annotation.packages()) {
                try (DataInputStream dataInputStream = new DataInputStream((InputStream) Objects.requireNonNull(
                        this.getClass().getClassLoader().getResource(packages)).getContent())) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.endsWith(".class")) {
                            String nameClass = packages + "."
                                    + line.substring(0, line.length() - 6);
                            list.add(Class.forName(nameClass).getConstructor().newInstance());
                        }
                    }
                } catch (IOException | InstantiationException | InvocationTargetException
                        |NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
                    System.out.println("");
                }
            }
        }
    }

    /**
     * инъекция зависимостей.
     * @param o объект для инъекции.
     * @param <T>
     * @return о объект с инъекцией.
     * @throws IllegalAccessException
     */
    public <T> T inject(T o) throws IllegalAccessException {
        List<Object> classInject = new ArrayList<>();
        Class<T> clazz = (Class<T>) o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(MyInject.class)) {
                f.setAccessible(true);
                if (f.getType().getName().contains("java.util.List")) {
                    ParameterizedType fieldListType = (ParameterizedType) f.getGenericType();
                    Class<?> fieldGenericType = (Class<?>) fieldListType.getActualTypeArguments()[0];
                    for (Object object : list) {
                        if (object != null && fieldGenericType.isAssignableFrom(object.getClass())) {
                            classInject.add(object);
                        }
                        f.set(o, classInject);
                    }
                } else {
                    for (Object object : list) {
                        if (object != null && f.getType().isAssignableFrom(object.getClass())) {
                            classInject.add(object);
                        }
                        if (classInject.size() == 1) {
                            f.set(o, classInject.get(0));
                        } else {
                            throw new RuntimeException("Количество классов превышает 1");
                        }
                    }
                }
            }
        }
        return o;
    }
}