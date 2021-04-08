package jdbc;

import contracts.*;
import enity.People;
import enums.Channels;
import enums.Gender;
import reflection.MyInject;
import validators.Validator;
import validatorsutils.Message;
import validatorsutils.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Считывание данных из базы данных в репозиторий.
 */
public class JDBCData {

    private static Logger logger = Logger.getLogger(JDBCData.class.getName());

    /**
     * Владелец контракта.
     */
    private People person;

    /**
     * Список всех владельцев контарктов.
     */
    private List<People> peopleList = new ArrayList<>();

    /**
     * Контракт.
     */
    private Contract contract;

    /**
     * Репозиторий контарктов.
     */
    private Repository list = new Repository();

    /**
     * Подключение к базе данных.
     */
    private PostgresSQL jdbcPostgresql = new PostgresSQL();

    /**
     * Лист валидаторов для проверки контракта.
     */
    @MyInject(clazz = Validator.class)
    private List<Validator> validatorList = new ArrayList<>();

    /**
     * Список сообщений по проверке контракта.
     */
    private List<Message> messages = new ArrayList<>();

    /**
     * Считывание данных с базы данных.
     * @return репозиторий контарктов
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Repository readAll() throws SQLException, ClassNotFoundException {

        jdbcPostgresql.connection();
        ResultSet resultSet = jdbcPostgresql.getData();

        int personId = 1;
        int numberString = 1;

        logger.info("Загрузка данных началась");
        while (resultSet.next()) {

            person = new People(personId, resultSet.getString(5),
                    resultSet.getDate(6).toLocalDate(), resultSet.getString(7), resultSet.getString(8),
                    Gender.valueOf(resultSet.getString(9).toUpperCase())
            );

            personId++;

            if (1 == resultSet.getInt(1)) {
                peopleList.add(person);
            }

            for (int i = 0; i < peopleList.size(); i++) {
                if (!(peopleList.get(i).getFio().equals(person.getFio()))) {
                    peopleList.add(person);
                } else {
                    person.setId(peopleList.get(i).getId());
                }
            }

            String[] addInfo = resultSet.getString(11).split(" ");

            if (resultSet.getString(10).equals("Mobile Contract")) {
                contract = new MobileContract(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getDate(3).toLocalDate(),
                        resultSet.getInt(4),
                        person,
                        Integer.parseInt(addInfo[0]),
                        Integer.parseInt(addInfo[1]),
                        Integer.parseInt(addInfo[2])
                );
            } else if (resultSet.getString(10).equals("Internet Contract")) {
                contract = new InternetContract(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getDate(3).toLocalDate(),
                        resultSet.getInt(4),
                        person,
                        Integer.parseInt(addInfo[0])
                );
            } else if (resultSet.getString(10).equals("TV Contract")) {
                contract = new DigitalTVContract(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getDate(3).toLocalDate(),
                        resultSet.getInt(4),
                        person,
                        Channels.valueOf(addInfo[0])
                );
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
                logger.info("Считывание " + numberString+ " строки завершено");
                list.add(contract);
                logger.info("Контаркт успешно добавлен в репозиторий");
            }
            numberString++;
        }
        jdbcPostgresql.closeConnection();
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
