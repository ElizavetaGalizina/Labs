package validators;

import contracts.Contract;
import validatorsutils.Message;
import validatorsutils.Status;

/**
 * Валидатор проверки ID контаркта.
 */
public class IdValidator implements Validator {

    @Override
    public Message validate(Contract contract) {
        if (contract.getId()>0) {
            return new Message("ID контракта", Status.OK);
        } else {
            return new Message("ID контракта",Status.ERROR);
        }
    }

    @Override
    public Class<?> getAppliableFor() {
        return Contract.class;
    }
}
