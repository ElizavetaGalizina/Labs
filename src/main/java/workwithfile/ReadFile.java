package workwithfile;

import com.opencsv.*;
import contracts.*;
import enums.Gender;
import enums.Channels;
import enity.People;
import validators.*;

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
     * проверка на корректность данный владельца.
     */
    private ValidatorPerson validatePerson = new ValidatorPerson();
    /**
     * проверка на корректность данных контракта.
     */
    private ValidatorContract validateContract = new ValidatorContract();
    /**
     * результат проверки владельца.
     */
    private ArrayList<String> resulPerson = new ArrayList<String>(4);
    /**
     * результат проверки контракта.
     */
    private ArrayList<String> resultContract = new ArrayList<String>(5);

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
            throws IOException {
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

            resulPerson = new ArrayList<String>(4);
            resulPerson = validatePerson.validate(person);
            int errorP = 0;
            for (String s : resulPerson) {
                if (!s.equals("Корректная запись")) {
                    logger.warning(s);
                    errorP++;
                    logger.warning("Клиент не может быть добавлен в репозиторий");
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

            resultContract = new ArrayList<String>(5);
            resultContract = validateContract.resultValidateContracts(contract, string[9], addInfo);
            int errorC = 0;
            for (String s : resultContract) {
                if (!s.equals("Корректная запись")) {
                    logger.warning(s);
                    errorC++;
                    logger.warning("Контаркт не может быть добавлен в репозиторий");
                }
            }

            if (errorP == 0 && errorC == 0) {
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
}