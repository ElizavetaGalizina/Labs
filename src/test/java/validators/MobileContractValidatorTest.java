package validators;

import contracts.Contract;
import contracts.MobileContract;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MobileContractValidatorTest {

    @Test
    void validate() {
        MobileContractValidator mobileContractValidator = new MobileContractValidator();
        Message message1 = new Message("Мобильный контракт",Status.OK);
        Message message2 = new Message("Мобильный контракт",Status.ERROR);
        Contract contract1 = new MobileContract(3, LocalDate.of(2010, 1, 1),
                LocalDate.of(2020, 12, 10), 1, null, 10, 100, 500);
        Contract contract2 = new MobileContract(3, LocalDate.of(2010, 1, 1),
                LocalDate.of(2020, 12, 10), 1, null, -10, 100, 500);
        assertEquals(message1.getStatus(),mobileContractValidator.validate(contract1).getStatus());
        assertEquals(message2.getStatus(),mobileContractValidator.validate(contract2).getStatus());
    }
}