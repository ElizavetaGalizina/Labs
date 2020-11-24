package Validators;

import Enity.People;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, в котором реализованы методы для проверки корректности данных о владельце контракта
 */
public class ValidatorPerson {

    public ValidatorPerson(){};

    /**
     * Итоговый результат проверки клиента
     */
    static List<String> result=new ArrayList<String>();

    /**
     * Метод, проверяющий корректную запись ФИО владельца контракта. Запись ФИО должна начинаться с буквы, содержать в себе пробелы и буквы.
     * @param fio ФИО владельца
     * @return сообщение о корректной/неккоректной записи ФИО
     */
    public static String checkFio(String fio){
        Pattern pattern = Pattern.compile("^[\\W]|[0-9]|[*,/,-,+]");
        Matcher matcher = pattern.matcher(fio);
        if (matcher.find()){
            return "Ошибка:некорректная записи ФИО владельца";
        }
        else{
            return "Корректная запись";
        }
    }

    /**
     * Метод, поверяющий совершенолетие владельца контракта. Владельцу контракта должно быть 18 или более лет
     * @param bithday дата рождения клиента, возраст которого необходимо проверить
     * @return сообщение о корректной/неккоректном возрасте клиента
     */
    public static String checkBD(LocalDate bithday){
        int bithdayYear=bithday.getYear();
        LocalDate now = LocalDate.now();
        if (now.getYear()-bithdayYear>18 || (now.getYear()-bithdayYear==18)){
            return "Корректная запись";
        } else{
            return "Ошибка: клиент не достиг совершенолетия";
        }
    }

    /**
     * Метод, проверяющий корректную запись серии паспорта владельца контракта. Запись должна содержать цифры от 0 до 9 в количестве 4 штук.
     * @param pasSeries серия паспорта клиента
     * @return сообщение о корректной/неккоректной записи серии паспорта клиента
     */
    public static String checkPasSeries(String pasSeries){
        Pattern pattern = Pattern.compile("^([0-9]{4}?$)");
        Matcher matcher = pattern.matcher(pasSeries);
        if (matcher.find()){
            return "Корректная запись";
        }
        else{
            return "Ошибка:некорректная запись серии паспорта клиента";
        }
    }

    /**
     * Метод, проверяющий корректную запись номера паспорта владельца контракта. Запись должна содержать цифры от 0 до 9 в количестве 6 штук.
     * @param pasNumber номер паспорта клиента
     * @return сообщение о корректной/неккоректной записи номера паспорта клиента
     */
    public static String checkPasNumber(String pasNumber){
        Pattern pattern = Pattern.compile("^([0-9]{6}?$)");
        Matcher matcher = pattern.matcher(pasNumber);
        if (matcher.find()){
            return "Корректная запись";
        }
        else{
            return "Ошибка:некорректная запись номера паспорта";
        }
    }

    /**
     * Метод, проверяющий полностью данные о клиенте
     * @param person клиент, которого нужно проверить
     */
    public static ArrayList<String> validate (People person) {
        result.clear();
        result.add(checkFio(person.getFio()));
        result.add(checkPasSeries(person.getPassportSeries()));
        result.add(checkPasNumber(person.getPassportNumber()));
        result.add(checkBD(person.getBithday()));
        return (ArrayList<String>) result;
    };
}


