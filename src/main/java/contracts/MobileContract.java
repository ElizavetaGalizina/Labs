package contracts;

import enity.People;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

/**
 * класс контракта на мобильную связь.
 */
@XmlRootElement
public class MobileContract extends Contract {

    /**
     * количество смс.
     */
    private int sms;
    /**
     * количество минут.
     */
    private int min;
    /**
     * количество гигабайт.
     */
    private int gb;

    /**
     * конструктор без параметров.
     */
    public MobileContract() {

    }

    /**
     * конструктор с параметрами.
     * @param id идентификатор контракта
     * @param startDate  дата заключения контракта
     * @param endDate   дата расторжения контракта
     * @param numberContract  номер контракта
     * @param owner  владелец контракта
     * @param sms количество смс
     * @param min количество минут
     * @param gb количество гб трафика
     */
    public MobileContract(final int id, final LocalDate startDate, final LocalDate endDate,
                          final int numberContract, final People owner, final int sms,
                          final int min, final int gb) {
        super(id, startDate, endDate, numberContract, owner);
        this.sms = sms;
        this.min = min;
        this.gb = gb;
    }

    /**
     * возвращает количество смс.
     * @return sms количество смс
     */
    @XmlElement
    public final int getSms() {
        return sms;
    }

    /**
     * задает количество смс.
     * @param sms количество смс
     */
    public final void setSms(final int sms) {
        this.sms = sms;
    }

    /**
     * возвращает количество минут.
     * @return  min количество минут
     */
    @XmlElement
    public final int getMin() {
        return min;
    }

    /**
     * задает количество минут.
     * @param min кол-во минут
     */
    public final void setMin(final int min) {
        this.min = min;
    }

    /**
     * возвращает количество гб.
     * @return gb количество гб
     */
    @XmlElement
    public final int getGb() {
        return gb;
    }

    /**
     * задает гб трафик.
     * @param gb гб трафик
     */
    public final void setGb(final int gb) {
        this.gb = gb;
    }

    @Override
     public final String toString() {
        return "MobileContract{"
                + "  id=" + id
                + ", startDate=" + startDate
                + ", endDate=" + endDate
                + ", numberContract=" + numberContract
                + ", owner=" + owner
                + ", sms=" + sms
                + ", min=" + min
                + ", gb=" + gb
                + '}';
    }
}
