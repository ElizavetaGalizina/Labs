package validators;

import contracts.*;
import validatorsutils.Message;

/**
 * Интерфейс валидатора.
 */
public interface Validator {

    public Message validate(Contract contract);

    public Class<?> getAppliableFor() throws ClassNotFoundException;

}
