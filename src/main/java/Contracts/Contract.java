package Contracts;

import Enity.People;

import java.time.LocalDate;

/**
 * класс контракта
 */
public class Contract {

    protected int id;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected int numberContract;
    protected People owner;

    /**
     * конструктор без параметров
     */
    public Contract(){

    }

    /**
     * конструктор с параметрами
     * @param id идентификатор контракта
     * @param startDate  дата заключения контракта
     * @param endDate   дата расторжения контракта
     * @param numberContract  номер контракта
     * @param owner  владелец контракта
     */
    public Contract(int id, LocalDate startDate, LocalDate endDate, int numberContract, People owner) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberContract = numberContract;
        this.owner = owner;
    }

    /**
     * возвращает id контракта
     * @return id идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * задает id контракта
     * @param id идентификатор
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * возвращает дату заключения контракта
     * @return дата заключения контракта
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * задает дату заключения контракта
     * @param startDate дата заключения
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * возвращает дату расторжения контракта
     * @return дата расторжения
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * задает дату расторжения контракта
     * @param endDate дата расторжения
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * возращает номер контракта
     * @return номер контракта
     */
    public int getNumberContract() {
        return numberContract;
    }

    /**
     * задает номер контракта
     * @param numberContract номер
     */
    public void setNumberContract(int numberContract) {
        this.numberContract = numberContract;
    }

    /**
     * возращает владельца контракта
     * @return владелец
     */
    public People getOwner() {
        return owner;
    }

    /**
     * задает владельца контракта
     * @param owner владелец
     */
    public void setOwner(People owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberContract=" + numberContract +
                ", owner=" + owner +
                '}';
    }
}

