package contracts;

import enity.People;
import enums.Gender;
import xml.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * класс контракта.
 */
@XmlRootElement
@XmlSeeAlso({Gender.class, People.class, LocalDate.class})
public class Contract implements Serializable {
    /**
     * идентификатор контракта.
     */
    protected int id;
    /**
     * дата начала контракта.
     */
    protected LocalDate startDate;
    /**
     * дата завершения контракта.
     */
    protected LocalDate endDate;
    /**
     * номер контракта.
     */
    protected int numberContract;
    /**
     * владелец контаркта.
     */
    protected People owner;

    /**
     * конструктор без параметров.
     */
    public Contract() {

    }

    /**
     * конструктор с параметрами.
     * @param id идентификатор контракта
     * @param startDate  дата заключения контракта
     * @param endDate   дата расторжения контракта
     * @param numberContract  номер контракта
     * @param owner  владелец контракта
     */
    public Contract(final int id, final LocalDate startDate, final LocalDate endDate,
                    final int numberContract, final People owner) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberContract = numberContract;
        this.owner = owner;
    }

    /**
     * возвращает id контракта.
     * @return id идентификатор
     */
    @XmlElement
    public final int getId() {
        return id;
    }

    /**
     * задает id контракта.
     * @param i идентификатор
     */
    public final void setId(final int i) {
        this.id = i;
    }

    /**
     * возвращает дату заключения контракта.
     * @return дата заключения контракта
     */
    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public final LocalDate getStartDate() {
        return startDate;
    }

    /**
     * задает дату заключения контракта.
     * @param startDate дата заключения
     */
    public final void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * возвращает дату расторжения контракта.
     * @return дата расторжения
     */
    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public final LocalDate getEndDate() {
        return endDate;
    }

    /**
     * задает дату расторжения контракта.
     * @param endDate дата расторжения
     */
    public final void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * возращает номер контракта.
     * @return номер контракта
     */
    @XmlElement
    public final int getNumberContract() {
        return numberContract;
    }

    /**
     * задает номер контракта.
     * @param numberContract номер
     */
    public final void setNumberContract(final int numberContract) {
        this.numberContract = numberContract;
    }

    /**
     * возращает владельца контракта.
     * @return владелец
     */
    @XmlAnyElement
    public final People getOwner() {
        return owner;
    }

    /**
     * задает владельца контракта.
     * @param owner владелец
     */
    public final void setOwner(final People owner) {
        this.owner = owner;
    }

    @Override
     public String toString() {
        return "Contract{"
                + "id=" + id
                + ", startDate=" + startDate
                + ", endDate=" + endDate
                + ", numberContract=" + numberContract
                + ", owner=" + owner
                + '}';
    }
}

