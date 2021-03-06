package jdbc;

import contracts.*;
import enity.People;
import enums.Channels;
import enums.Gender;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PostgresTest {

    @Test
    void saveRepo () {
        Repository repository = new Repository();

        People person1 = new People(1, "Ivanov Ivan Ivanovich", LocalDate.of(1985, 5, 12), "2015",
                "365458", Gender.MAN);
        People person2 = new People(2, "Sidorova Alla Petrovna", LocalDate.of(1972, 7, 24), "2017",
                "356874", Gender.WOMEN);
        People person3 = new People(3, "Safonov Nikolay Leonidovich", LocalDate.of(1990, 10, 1),
                "2005", "254789", Gender.MAN);

        Contract contract1 = new InternetContract(1, LocalDate.of(2015, 5, 10),
                LocalDate.of(2017, 5, 10), 1, person1, 100);
        Contract contract2 = new DigitalTVContract(2, LocalDate.of(2020, 5, 10),
                LocalDate.of(2020, 12, 10), 2, person2, Channels.ALLINCLUSIVE);
        Contract contract3 = new MobileContract(3, LocalDate.of(2010, 1, 1),
                LocalDate.of(2020, 12, 10), 1, person3, 10, 100, 500);
        Contract contract4 = new DigitalTVContract(4, LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 12, 10), 2, person3, Channels.PREMIUM);

        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract3);
        repository.add(contract4);

        Postgres postgres = new Postgres();
        postgres.saveRepo(repository);

    }

    @Test
    void selectRepo() {
        Postgres postgres = new Postgres();
        Repository repository = postgres.insertRepo();
        System.out.println(repository.toString());
    }
}