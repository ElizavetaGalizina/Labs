package Validators;

import Contracts.Contract;
import Enum.*;
import Contracts.InternetContract;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorContractTest {

    @Test
    void checkIDContract() {
        assertEquals("Корректная запись", ValidatorContract.checkIDContract(12));
        assertEquals("Ошибка: некорректно указан ID контракта", ValidatorContract.checkIDContract(-12));
    }

    @Test
    void checkNumberContract() {
        assertEquals("Ошибка: некорректно указан номер контракта",ValidatorContract.checkNumberContract(-100));
        assertEquals("Корректная запись",ValidatorContract.checkNumberContract(100));
    }

    @Test
    void checkDateContract() {
        Contract contract1=new InternetContract(1, LocalDate.of(2015, 5, 10),LocalDate.of(2012, 5, 10),1,null,100);
        Contract contract2=new InternetContract(1, LocalDate.of(2015, 5, 10),LocalDate.of(2017, 5, 10),1,null,100);
        assertEquals("Корректная запись",ValidatorContract.checkDateContract(contract2.getStartDate(),contract2.getEndDate()));
        assertEquals("Ошибка: некорректная запись даты начала и завершения контракта",ValidatorContract.checkDateContract(contract1.getStartDate(),contract1.getEndDate()));
    }

    @Test
    void checkTVContracts() {
        String[] addInfo1={String.valueOf(Channels.ALLINCLUSIVE)};
        String[] addInfo2={String.valueOf(Channels.ALLINCLUSIVE),String.valueOf(Channels.SPORTS)};
        String[] addInfo3={};
        assertEquals("Корректная запись",ValidatorContract.checkTVContracts(addInfo1));
        assertEquals("Ошибка: некорректная запись пакета каналов",ValidatorContract.checkTVContracts(addInfo2));
        assertEquals("Ошибка: некорректная запись пакета каналов",ValidatorContract.checkTVContracts(addInfo3));
    }

    @Test
    void checkInternetContract() {
        String[] addInfo1={"200"};
        String[] addInfo2={"-200"};
        String[] addInfo3={};
        assertEquals("Корректная запись",ValidatorContract.checkInternetContract(addInfo1));
        assertEquals("Ошибка: некорректная запись скорости интернета",ValidatorContract.checkInternetContract(addInfo2));
        assertEquals("Ошибка: некорректная запись скорости интернета",ValidatorContract.checkInternetContract(addInfo3));
    }

    @Test
    void checkMobileContract() {
        String[] addInfo1={"200","200","200"};
        String[] addInfo2={"-200","100","100"};
        String[] addInfo3={"10","10","10","10"};
        assertEquals("Корректная запись",ValidatorContract.checkMobileContract(addInfo1));
        assertEquals("Ошибка: некорректная запись параметров контракта на мобильную связь",ValidatorContract.checkMobileContract(addInfo2));
        assertEquals("Ошибка: некорректная запись параметров контракта на мобильную связь",ValidatorContract.checkMobileContract(addInfo3));
    }

    @Test
    void checkOwner() {
        assertEquals("Корректная запись",ValidatorContract.checkOwner(LocalDate.of(1965, 5, 10)));
        assertEquals("Ошибка: клиент не достиг совершенолетия",ValidatorContract.checkOwner(LocalDate.of(2005, 5, 10)));
    }
}