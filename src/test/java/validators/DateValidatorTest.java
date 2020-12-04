package validators;

import contracts.Contract;
import contracts.InternetContract;
import enity.People;
import enums.Gender;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateValidatorTest {

    @Test
    void validate() {
        DateValidator dateValidator = new DateValidator();
        Message message1 = new Message("Дата контаркта",Status.OK);
        Message message2 = new Message("Дата контаркта",Status.ERROR);
        People person1 = new People(1, "Ivanov Ivan Ivanovich", LocalDate.of(1985, 5, 12), "2015",
                "365458", Gender.MAN);
        Contract contract1 = new InternetContract(1, LocalDate.of(2015, 5, 10),
                LocalDate.of(2017, 5, 10), 1, person1, 100);
        Contract contract2 = new InternetContract(1, LocalDate.of(2015, 5, 10),
                LocalDate.of(2005, 5, 10), 1, person1, 100);
        assertEquals(message1.getStatus(),dateValidator.validate(contract1).getStatus());
        assertEquals(message2.getStatus(),dateValidator.validate(contract2).getStatus());
    }
}