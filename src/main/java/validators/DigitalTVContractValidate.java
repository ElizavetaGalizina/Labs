package validators;

import contracts.Contract;
import contracts.DigitalTVContract;
import enums.Channels;

/**
 * Валидатор проверки контракта на телевидение.
 */
public class DigitalTVContractValidate implements Validator {
    @Override
    public Message validate(Contract contract) {
        Channels channels = ((DigitalTVContract) contract).getChannels();
        if (channels.equals(Channels.NIGHT) || channels.equals(Channels.ALLINCLUSIVE)
        || channels.equals(Channels.SPORTS) || channels.equals(Channels.FAMAlY) || channels.equals(Channels.PREMIUM)) {
            return new Message("TV контракт",Status.OK);
        } else {
            return new Message("TV контракт",Status.ERROR);
        }
    }

    @Override
    public Class<?> getAppliableFor() {
        return DigitalTVContract.class;
    }
}
