package validators;

import contracts.Contract;
import validatorsutils.Message;
import validatorsutils.Status;

/**
 * Валидатор проверки возраста клиента.
 */
public class AgeValidator implements Validator {

    @Override
    public Message validate(Contract contract) {
        if (contract.getOwner().agePerson()>=18){
            return new Message("Возраст клиента", Status.OK);
        } else {
            return new Message("Возраст клиента",Status.ERROR);
        }
    }

    @Override
    public Class<?> getAppliableFor() {
        return Contract.class;
    }

}
