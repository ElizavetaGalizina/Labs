package validators;

import contracts.Contract;
import contracts.DigitalTVContract;
import enity.People;
import enums.Channels;
import enums.Gender;
import org.junit.jupiter.api.Test;
import validatorsutils.Message;
import validatorsutils.Status;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AgeValidatorTest {

    @Test
    void validate() {
        AgeValidator ageValidator = new AgeValidator();
        Message message1 = new Message("Возраст клиента", Status.OK);
        Message message2 = new Message("Возраст клиента",Status.ERROR);
        People person1 = new People(1, "Ivanov Ivan Ivanovich", LocalDate.of(1985, 5, 12), "2015",
                "365458", Gender.MAN);
        People person2 = new People(1, "Ivanov Ivan Ivanovich", LocalDate.of(2007, 5, 12), "2015",
                "365458", Gender.MAN);
        Contract contract1 = new DigitalTVContract(4, LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 12, 10), 2, person1, Channels.PREMIUM);
        Contract contract2 = new DigitalTVContract(4, LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 12, 10), 2, person2, Channels.PREMIUM);
        assertEquals(message1.getStatus(),ageValidator.validate(contract1).getStatus());
        assertEquals(message2.getStatus(),ageValidator.validate(contract2).getStatus());
    }
}