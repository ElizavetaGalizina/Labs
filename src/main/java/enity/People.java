package enity;

import enums.Gender;
import xml.LocalDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * класс владельца контракта.
 */
@XmlRootElement
public class People {
    /**
     * идентификатор владельца.
     */
    private int id;
    /**
     * ФИО владельца.
     */
    private String fio;
    /**
     * дата рождения владельца.
     */
    private LocalDate bithday;
    /**
     * серия паспорта.
     */
    private String passportSeries;
    /**
     * номер паспорта.
     */
    private String passportNumber;
    /**
     * пол.
     */
    private Gender sex;

    public People() {
    }

    /**
     * конструктор с параметрами.
     * @param personId идентификатор
     * @param fio  фамилия,имя,отчество
     * @param bithday  день рождения
     * @param passportSeries  серия паспорта
     * @param passportNumber  номер паспорта
     * @param sex  пол
     */



    public People(final int personId, final String fio, final LocalDate bithday,
                  final String passportSeries, final String passportNumber,
                  final Gender sex) {
        this.id = personId;
        this.fio = fio;
        this.bithday = bithday;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.sex = sex;
    }

    /**
     *возвращает id персоны.
     * @return id идентификатор
     */
    @XmlElement
    public final int getId() {
        return id;
    }

    /**
     * задает id персоны.
     * @param id идентификатор
     */
    public final void setId(final int id) {
        this.id = id;
    }

    /**
     * возвращает фио персоны.
     * @return fio фамилия,имя,отчество
     */
    @XmlElement
    public final String getFio() {
        return fio;
    }

    /**
     * задает фио персоны.
     * @param fio фамилия,имя,отчество
     */
    public final void setFio(final String fio) {
        this.fio = fio;
    }

    /**
     * возвращает дату рождения персоны.
     * @return bithday дата рождения
     */
    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public final LocalDate getBithday() {
        return bithday;
    }

    /**
     * устанавливает дату рождения персоны.
     * @param bithday дата рождения
     */
    public final void setBithday(final LocalDate bithday) {
        this.bithday = bithday;
    }

    /**
     * возвращает серию паспорта песроны.
     * @return passportSeries серия паспорта
     */
    @XmlElement
    public final String getPassportSeries() {
        return passportSeries;
    }

    /**
     * устанавливает серию паспорта персоны.
     * @param passportSeries серия паспорта
     */
    public final void setPassportSeries(final String passportSeries) {
        this.passportSeries = passportSeries;
    }

    /**
     * возвращает номер паспорта.
     * @return passportNumber номер паспорта
     */
    @XmlElement
    public final String getPassportNumber() {
        return passportNumber;
    }

    /**
     * устанавливает номер паспорта.
     * @param passportNumber номер паспорта
     */
    public final void setPassportNumber(final String passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * возвращает пол персоны.
     * @return sex пол
     */
    @XmlElement
    public final Gender getSex() {
        return sex;
    }

    /**
     * устанавливает пол персоны.
     * @param sex пол
     */
    public final void setSex(final Gender sex) {
        this.sex = sex;
    }

    /**
     * вычисление возраста человека.
     * @return возраст человека
     */
    public final int agePerson() {
        LocalDate now = LocalDate.now();
        int age;
        if (bithday.getMonthValue() < now.getMonthValue()) {
            age = now.getYear() - bithday.getYear();
        } else if (bithday.getMonthValue() == now.getMonthValue()) {
            if (bithday.getDayOfMonth() < now.getDayOfMonth()) {
                age = now.getYear() - bithday.getYear() - 1;
            }
            age = now.getYear() - bithday.getYear();
        } else {
            age = now.getYear() - bithday.getYear() - 1;
        }
        return age;
    }

    @Override
    public final String toString() {
        return "People{"
                + "id=" + id
                + ", fio='" + fio + '\''
                + ", bithday=" + bithday
                + ", passportSeries='" + passportSeries + '\''
                + ", passportNumber='" + passportNumber + '\''
                + ", sex='" + sex + '\''
                + '}';
    }
}
