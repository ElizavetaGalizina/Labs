package enums;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * пол человека.
 */
@XmlRootElement
public enum Gender {
    /**
     * мужской пол.
     */
    MAN,
    /**
     * женский пол.
     */
    WOMEN
}
