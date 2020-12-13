package validators;

import contracts.Contract;
import contracts.MobileContract;
import validatorsutils.Message;
import validatorsutils.Status;

/**
 * Валидатор проверки мобильного интернета.
 */
public class MobileContractValidator implements Validator {
    @Override
    public Message validate(Contract contract) {
        int sms = ((MobileContract) contract).getSms();
        int min = ((MobileContract) contract).getMin();
        int gb = ((MobileContract) contract).getGb();
        if (sms>0 && min>0 && gb>0) {
            return new Message("Мобильный контракт", Status.OK);
        } else {
            return new Message("Мобильный контракт",Status.ERROR);
        }
    }

    @Override
    public Class<?> getAppliableFor() throws ClassNotFoundException {
        return MobileContract.class;
    }
}
