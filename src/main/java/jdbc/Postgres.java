package jdbc;

import contracts.*;
import enity.People;
import enums.Channels;
import enums.Gender;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class Postgres {

    private static Logger logger = Logger.getLogger(Postgres.class.getName());
    private static Connection connection;
    private static String url;
    private static String user;
    private static String password;

    public Postgres() {
        try (InputStream dbp = Postgres.class.getClassLoader().getResourceAsStream("Database.properties")) {
            Properties props = new Properties();
            props.load(dbp);
            url = props.getProperty("Database.DataURL");
            user = props.getProperty("Database.Prop.user");
            password = props.getProperty("Database.Prop.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveRepo(Repository repository) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement contractInsert = connection.prepareStatement("INSERT INTO contact(id, begin_date, end_date, contract_number, fio, bdate, pas_ser, pas_num, gender, contract_type, add_info) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            for (int i = 1; i <= repository.size() ; i++) {
                Contract contract = repository.get(i);
                contractInsert.setInt(1,contract.getId());
                contractInsert.setDate(2, Date.valueOf(contract.getStartDate()));
                contractInsert.setDate(3, Date.valueOf(contract.getEndDate()));
                contractInsert.setInt(4,contract.getNumberContract());
                People people = contract.getOwner();
                contractInsert.setString(5,people.getFio());
                contractInsert.setDate(6,Date.valueOf(people.getBithday()));
                contractInsert.setString(7,people.getPassportSeries());
                contractInsert.setString(8,people.getPassportNumber());
                contractInsert.setString(9, String.valueOf(people.getSex()));
                if (contract instanceof DigitalTVContract) {
                    contractInsert.setString(10,"TV Contract");
                    contractInsert.setString(11,String.valueOf(((DigitalTVContract) contract).getChannels()));
                } else if (contract instanceof MobileContract) {
                    contractInsert.setString(10,"Mobile Contract");
                    String info = ((MobileContract) contract).getGb() + " " + ((MobileContract) contract).getMin() + " " + ((MobileContract) contract).getSms();
                    contractInsert.setString(11,info);
                } else if (contract instanceof InternetContract) {
                    contractInsert.setString(10,"Internet Contract");
                    contractInsert.setString(11, String.valueOf(((InternetContract) contract).getSpeed()));
                }
                contractInsert.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Repository insertRepo() {
        Repository repository = new Repository();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM contact");
            ResultSet set = statement.executeQuery();
            People person;
            Contract contract = new Contract();
            List<People> peopleList = new ArrayList<>();
            int personId = 1;
            while (set.next()) {
                person = new People(personId, set.getString(5),
                        set.getDate(6).toLocalDate(), set.getString(7), set.getString(8),
                        Gender.valueOf(set.getString(9).toUpperCase())
                );
                personId++;
                if (1 == set.getInt(1)) {
                    peopleList.add(person);
                }

                for (int i = 0; i < peopleList.size(); i++) {
                    if (!(peopleList.get(i).getFio().equals(person.getFio()))) {
                        peopleList.add(person);
                    } else {
                        person.setId(peopleList.get(i).getId());
                    }
                }

                String[] addInfo = set.getString(11).split(" ");

                if (set.getString(10).equals("Mobile Contract")) {
                    contract = new MobileContract(set.getInt(1), set.getDate(2).toLocalDate(),
                            set.getDate(3).toLocalDate(), set.getInt(4), person,
                            Integer.parseInt(addInfo[0]), Integer.parseInt(addInfo[1]), Integer.parseInt(addInfo[2]));

                } else if (set.getString(10).equals("Internet Contract")) {
                    contract = new InternetContract(set.getInt(1), set.getDate(2).toLocalDate(),
                            set.getDate(3).toLocalDate(), set.getInt(4), person, Integer.parseInt(addInfo[0]));
                } else if (set.getString(10).equals("TV Contract")) {
                    contract = new DigitalTVContract(set.getInt(1), set.getDate(2).toLocalDate(),
                            set.getDate(3).toLocalDate(), set.getInt(4), person, Channels.valueOf(addInfo[0]));
                }
                repository.add(contract);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return repository;
    }
}
