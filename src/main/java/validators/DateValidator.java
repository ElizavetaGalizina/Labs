package validators;

import contracts.Contract;
import validatorsutils.Message;
import validatorsutils.Status;

import java.time.LocalDate;

/**
 * Валидатор проверки даты контракта.
 */
public class DateValidator implements Validator {
    @Override
    public Message validate(Contract contract) {
        LocalDate start = contract.getStartDate();
        LocalDate end = contract.getEndDate();
        int startYear = start.getYear();
        int startMonth = start.getMonthValue();
        int startDay = start.getDayOfMonth();
        int endYear = end.getYear();
        int endMonth = end.getMonthValue();
        int endDay = end.getDayOfMonth();
        if (startYear < endYear || (startYear == endYear && startMonth < endMonth)
                || (startYear == endYear && startMonth == endMonth && startDay < endDay)) {
            return new Message("Дата контракта", Status.OK);
        } else {
            return new Message("Дата контракта",Status.ERROR);
        }
    }

    @Override
    public Class<?> getAppliableFor() {
        return Contract.class;
    }

}
