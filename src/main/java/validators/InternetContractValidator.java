package validators;

import contracts.Contract;
import contracts.InternetContract;
import validatorsutils.Message;
import validatorsutils.Status;

/**
 * Валидатор проверки контракта на интернет.
 */
public class InternetContractValidator implements Validator {
    @Override
    public Message validate(Contract contract) {
        int speed = ((InternetContract) contract).getSpeed();
        if (speed>0) {
            return new Message("Интернет контракт", Status.OK);
        } else {
            return new Message("Интернет контракт",Status.ERROR);
        }
    }

    @Override
    public Class<?> getAppliableFor() {
        return InternetContract.class;
    }

}
