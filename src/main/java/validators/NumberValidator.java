package validators;

import contracts.Contract;

/**
 * Вадитор проверки номера контракта.
 */
public class NumberValidator implements Validator {

    @Override
    public Message validate(Contract contract) {
        if (contract.getNumberContract()>0) {
            return new Message("Номер контракта",Status.OK);
        } else {
            return new Message("Номер контракта",Status.ERROR);
        }
    }

    @Override
    public Class<?> getAppliableFor() {
        return Contract.class;
    }
}
