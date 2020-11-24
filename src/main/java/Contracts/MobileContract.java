package Contracts;

import Enity.People;

import java.time.LocalDate;

/**
 * класс контракта на мобильную связь
 */
public class MobileContract extends Contract {

    private int sms;
    private int min;
    private int gb;

    /**
     * конструктор без параметров
     */
    public MobileContract(){

    }

    /**
     * конструктор с параметрами
     @param id идентификатор контракта
      * @param startDate  дата заключения контракта
     * @param endDate   дата расторжения контракта
     * @param numberContract  номер контракта
     * @param owner  владелец контракта
     * @param sms количество смс
     * @param min количество минут
     * @param gb количество гб трафика
     */
    public MobileContract(int id, LocalDate startDate, LocalDate endDate, int numberContract, People owner, int sms, int min, int gb) {
        super(id, startDate, endDate, numberContract, owner);
        this.sms = sms;
        this.min = min;
        this.gb = gb;
    }

    /**
     * возвращает количество смс
     * @return sms количество смс
     */
    public int getSms() {
        return sms;
    }

    /**
     * задает количество смс
     * @param sms количество смс
     */
    public void setSms(int sms) {
        this.sms = sms;
    }

    /**
     * возвращает количество минут
     * @return  min количество минут
     */
    public int getMin() {
        return min;
    }

    /**
     * задает количество минут
     * @param min кол-во минут
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * возвращает количество гб
     * @return gb количество гб
     */
    public int getGb() {
        return gb;
    }

    /**
     * задает гб трафик
     * @param gb гб трафик
     */
    public void setGb(int gb) {
        this.gb = gb;
    }

    @Override
    public String toString() {
        return "MobileContract{" +
                "  id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberContract=" + numberContract +
                ", owner=" + owner +
                ", sms=" + sms +
                ", min=" + min +
                ", gb=" + gb +
                '}';
    }
}
