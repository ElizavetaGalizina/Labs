package WorkWithFile;

import com.opencsv.*;
import Contracts.*;
import Enum.Gender;
import Enum.Channels;
import Enity.People;
import Validators.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * чтение данных с файла
 */
public class ReadFile {

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private People person;
    private List<People> peopleList = new ArrayList<>();
    private Contract contract;
    private Repository list = new Repository();
    private CSVReader csvReader;
    private CSVParser pars = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();
    private ValidatorPerson validatePerson = new ValidatorPerson();
    private ValidatorContract validateContract = new ValidatorContract();

    private ArrayList<String> resulPerson = new ArrayList<String>(4);
    private ArrayList<String> resultContract = new ArrayList<String>(5);

    /**
     * конструктор без параметров
     */
    public ReadFile() {
    }

    /**
     * Считываниe данных с файла
     * @param reader
     * @return репозиторий, хранящий разные типы контрактов
     * @throws IOException
     */
    public Repository readFile(Reader reader) throws IOException {
        csvReader = new CSVReaderBuilder(new FileReader("contracts.csv")).withCSVParser(pars).withSkipLines(0).build();
        String[] string=string = csvReader.readNext();
        int personId=1;
        int numberString = 1;
        while ((string = csvReader.readNext()) != null) {
            person = new People(personId,string[4], LocalDate.parse(string[5], format), string[6], string[7], Gender.valueOf(string[8].toUpperCase())
            );
            personId++;
            if (1 == Integer.parseInt(string[0])) {
                peopleList.add(person);
            }
            for (int i = 0; i < peopleList.size(); i++){
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
                    System.out.println(s);
                    errorP++ ;
                }
            }

            String[] addInfo = string[string.length - 1].split(" ");
            if (string[9].equals("Mobile Contract")) {
                contract = new MobileContract(Integer.parseInt(string[0]), LocalDate.parse(string[1], format), LocalDate.parse(string[2], format),
                        Integer.parseInt(string[3]), person, Integer.parseInt(addInfo[0]), Integer.parseInt(addInfo[1]), Integer.parseInt(addInfo[2]));
            }
            else if (string[9].equals("Internet Contract")) {
                contract = new InternetContract(Integer.parseInt(string[0]), LocalDate.parse(string[1], format), LocalDate.parse(string[2], format),
                        Integer.parseInt(string[3]), person, Integer.parseInt(addInfo[0]));
            }
            else if (string[9].equals("TV Contract")) {
                contract = new DigitalTVContract(Integer.parseInt(string[0]), LocalDate.parse(string[1], format), LocalDate.parse(string[2], format), Integer.parseInt(string[3]),
                        person, Channels.valueOf(addInfo[0]));
            }

            resultContract = new ArrayList<String>(5);
            resultContract = validateContract.resultValidateContracts(contract,string[9], addInfo);
            int errorC = 0;
            for (String s : resultContract) {
                if (!s.equals("Корректная запись")) {
                    System.out.println(s);
                    errorC++;
                }
            }

            if (errorP == 0 && errorC == 0) {
                list.add(contract);
            }
            numberString++;
        }
        reader.close();
        csvReader.close();
        return list;
    }
}