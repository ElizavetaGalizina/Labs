package workwithfile;

import com.opencsv.*;
import contracts.*;
import enums.Gender;
import enums.Channels;
import enity.People;
import reflection.MyInject;
import validators.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * чтение данных с файла.
 */
public class ReadFile {
    private static Logger logger = Logger.getLogger(ReadFile.class.getName());
    /**
     * Форматирование для правильного считывания даты из файла.
     */
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    /**
     * владелец контракта.
     */
    private People person;
    /**
     * список владельцев контаркта.
     */
    private List<People> peopleList = new ArrayList<>();
    /**
     * контракт.
     */
    private Contract contract;
    /**
     * репозиторий хранящий контаркты.     */
    private Repository list = new Repository();
    /**
     * ридер.
     */
    private CSVReader csvReader;
    /**
     * парсер.
     */
    private CSVParser pars = new CSVParserBuilder().withSeparator(';').
            withIgnoreQuotations(true).build();
    /**
     * результат проверки контракта.
     */
    @MyInject(clazz = Validator.class)
    private List<Validator> validatorList = new ArrayList<>();
    /**
     * список сообщений по проверке контракта.
     */
    private List<Message> messages = new ArrayList<>();

    /**
     * конструктор без параметров.
     *
     */
    public ReadFile() {
    }

    /**
     * Считываниe данных с файла.
     *
     * @param reader имя файла
     * @return репозиторий, хранящий разные типы контрактов
     * @throws IOException исключение
     */
    public Repository readFile(Reader reader)
            throws IOException, ClassNotFoundException {
//        validatorList.add(new AgeValidator());
//        validatorList.add(new IdValidator());
//        validatorList.add(new DateValidator());
//        validatorList.add(new InternetContractValidator());
//        validatorList.add(new MobileContractValidator());
//        validatorList.add(new NumberValidator());
//        validatorList.add(new DigitalTVContractValidate());
        csvReader = new CSVReaderBuilder(new FileReader("contracts.csv")).
                withCSVParser(pars).withSkipLines(0).build();
        logger.info("Началось считывание файла contracts.csv");
        String[] string = csvReader.readNext();
        int personId = 1;
        int numberString = 1;
        while ((string = csvReader.readNext()) != null) {
            logger.info("Чтение " + numberString + " строки");
            person = new People(personId, string[4], LocalDate.parse(string[5], format), string[6], string[7], Gender.valueOf(string[8].toUpperCase())
            );
            personId++;
            if (1 == Integer.parseInt(string[0])) {
                peopleList.add(person);
            }
            for (int i = 0; i < peopleList.size(); i++) {
                if (!(peopleList.get(i).getFio().equals(person.getFio()))) {
                    peopleList.add(person);
                } else {
                    person.setId(peopleList.get(i).getId());
                }
            }

            String[] addInfo = string[string.length - 1].split(" ");
            if (string[9].equals("Mobile Contract")) {
                contract = new MobileContract(Integer.parseInt(string[0]),
                        LocalDate.parse(string[1], format), LocalDate.parse(string[2], format),
                        Integer.parseInt(string[3]), person, Integer.parseInt(addInfo[0]),
                        Integer.parseInt(addInfo[1]), Integer.parseInt(addInfo[2]));
            } else if (string[9].equals("Internet Contract")) {
                contract = new InternetContract(Integer.parseInt(string[0]), LocalDate.parse(string[1], format), LocalDate.parse(string[2], format),
                        Integer.parseInt(string[3]), person, Integer.parseInt(addInfo[0]));
            } else if (string[9].equals("TV Contract")) {
                contract = new DigitalTVContract(Integer.parseInt(string[0]),
                        LocalDate.parse(string[1], format), LocalDate.parse(string[2], format),
                        Integer.parseInt(string[3]),
                        person, Channels.valueOf(addInfo[0]));
            }

            messages = new ArrayList<>();
            messages = check(contract,validatorList);
            int errorC = 0;
            for (Message s : messages) {
                if (!s.getStatus().equals(Status.OK)) {
                    logger.warning(s.getString());
                    errorC++;
                    logger.warning("Контаркт не может быть добавлен в репозиторий");
                }
            }

            if (errorC == 0) {
                logger.info("Считывание " + numberString+ " прошло успешно и контаркт добавлен в репозиторий");
                list.add(contract);
                logger.info("Контаркт успешно добавлен в репозиторий");
            }
            numberString++;
        }
        reader.close();
        csvReader.close();
        logger.info("Считывание файла contracts.csv завершено");
        return list;
    }

    public static List<Message> check(Contract contract, List<Validator> validatorList) throws ClassNotFoundException {
        List<Message> messageList = new ArrayList<>();
        for (Validator v: validatorList) {
            if (v.getAppliableFor().isInstance(contract)) {
                messageList.add(v.validate(contract));
            }
        }
        return  messageList;
    }
}