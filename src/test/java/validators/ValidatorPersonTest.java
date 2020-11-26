package validators;


import enity.People;
import enums.Gender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class ValidatorPersonTest {

    @Test
    void checkFio() {
        People person1=new People(1, "Ivanov Ivan Ivanovich1", LocalDate.of(1985, 5, 12),"2015", "365458", Gender.MAN);
        People person2=new People(2, "Sidorova Alla Petrovna", LocalDate.of(1972, 7, 24),"201798", "356874", Gender.WOMEN);
        People person3=new People(3, "Safonov Nikolay Leonidovich", LocalDate.of(1990, 10, 1),"2005", "254789", Gender.MAN);
        assertEquals("Ошибка:некорректная записи ФИО владельца",ValidatorPerson.checkFio(person1.getFio()));
        assertEquals("Ошибка:некорректная запись серии паспорта клиента",ValidatorPerson.checkPasSeries(person2.getPassportSeries()));
        assertEquals("Корректная запись",ValidatorPerson.checkPasNumber(person3.getPassportNumber()));
    }

    @Test
    void checkBD() {
        People person1=new People(1, "Ivanov Ivan Ivanovich", LocalDate.of(1985, 5, 12),"2015", "365458", Gender.MAN);
        People person2=new People(2, "Sidorova Alla Petrovna", LocalDate.of(2005, 7, 24),"2017", "356874", Gender.WOMEN);
        assertEquals("Корректная запись",ValidatorPerson.checkBD(person1.getBithday()));
        assertEquals("Ошибка: клиент не достиг совершенолетия",ValidatorPerson.checkBD(person2.getBithday()));
    }

    @Test
    void checkPasSeries() {
        People person1=new People(1, "Ivanov Ivan Ivanovich", LocalDate.of(1985, 5, 12),"2015", "365458", Gender.MAN);
        People person2=new People(2, "Sidorova Alla Petrovna", LocalDate.of(2005, 7, 24),"201774", "356874", Gender.WOMEN);
        People person3=new People(2, "Sidorova Alla Petrovna", LocalDate.of(2005, 7, 24),"//2017", "356874", Gender.WOMEN);
        assertEquals("Корректная запись",ValidatorPerson.checkPasSeries(person1.getPassportSeries()));
        assertEquals("Ошибка:некорректная запись серии паспорта клиента",ValidatorPerson.checkPasSeries(person2.getPassportSeries()));
        assertEquals("Ошибка:некорректная запись серии паспорта клиента",ValidatorPerson.checkPasSeries(person3.getPassportSeries()));
    }

    @Test
    void checkPasNumber() {
        People person1=new People(1, "Ivanov Ivan Ivanovich", LocalDate.of(1985, 5, 12),"2015", "365458", Gender.MAN);
        People person2=new People(2, "Sidorova Alla Petrovna", LocalDate.of(2005, 7, 24),"2017", "35687444", Gender.WOMEN);
        People person3=new People(2, "Sidorova Alla Petrovna", LocalDate.of(2005, 7, 24),"2017", "s356874", Gender.WOMEN);
        assertEquals("Корректная запись",ValidatorPerson.checkPasNumber(person1.getPassportNumber()));
        assertEquals("Ошибка:некорректная запись номера паспорта",ValidatorPerson.checkPasNumber(person2.getPassportNumber()));
        assertEquals("Ошибка:некорректная запись номера паспорта",ValidatorPerson.checkPasNumber(person3.getPassportNumber()));
    }
}