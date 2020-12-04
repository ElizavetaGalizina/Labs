package validators;

import contracts.*;

/**
 * Интерфейс валидатора.
 */
public interface Validator {

    public Message validate(Contract contract);

    public Class<?> getAppliableFor() throws ClassNotFoundException;

}
