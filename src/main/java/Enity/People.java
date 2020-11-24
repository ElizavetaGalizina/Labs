package Enity;

import Enum.Gender;

import java.time.LocalDate;

/**
 * класс владельца контракта
 */
public class People {

    private int id;
    private String fio;
    private LocalDate bithday;
    private String passportSeries;
    private String passportNumber;
    private Gender sex;


    /**
     * @param personId идентификатор
     * @param fio  фамилия,имя,отчество
     * @param bithday  день рождения
     * @param passportSeries  серия паспорта
     * @param passportNumber  номер паспорта
     * @param sex  пол
     */
    public People(int personId, String fio, LocalDate bithday, String passportSeries, String passportNumber, Gender sex) {
        this.id = personId;
        this.fio = fio;
        this.bithday = bithday;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.sex = sex;
    }

    /**
     *возвращает id персоны
     * @return id идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * задает id персоны
     * @param id идентификатор
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * возвращает фио персоны
     * @return fio фамилия,имя,отчество
     */
    public String getFio() {
        return fio;
    }

    /**
     * задает фио персоны
     * @param fio фамилия,имя,отчество
     */
    public void setFio(String fio) {
        this.fio = fio;
    }

    /**
     * возвращает дату рождения персоны
     * @return bithday дата рождения
     */
    public LocalDate getBithday() {
        return bithday;
    }

    /**
     * устанавливает дату рождения персоны
     * @param bithday дата рождения
     */
    public void setBithday(LocalDate bithday) {
        this.bithday = bithday;
    }

    /**
     * возвращает серию паспорта песроны
     * @return passportSeries серия паспорта
     */
    public String getPassportSeries() {
        return passportSeries;
    }

    /**
     * устанавливает серию паспорта персоны
     * @param passportSeries серия паспорта
     */
    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    /**
     * возвращает номер паспорта
     * @return passportNumber номер паспорта
     */
    public String getPassportNumber() {
        return passportNumber;
    }

    /**
     * устанавливает номер паспорта
     * @param passportNumber номер паспорта
     */
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * возвращает пол персоны
     * @return sex пол
     */
    public Gender getSex() {
        return sex;
    }

    /**
     * устанавливает пол персоны
     * @param sex пол
     */
    public void setSex(Gender sex) {
        this.sex = sex;
    }

    /**
     * вычисление возраста человека
     * @return возраст человека
     */
    public int agePerson(){
        LocalDate now=LocalDate.now();
        int age;
        if (bithday.getMonthValue()<now.getMonthValue()){
            age=now.getYear()-bithday.getYear();
        } else if (bithday.getMonthValue()==now.getMonthValue()) {
            if (bithday.getDayOfMonth()<now.getDayOfMonth()) {
                age=now.getYear()-bithday.getYear()-1;
            }
            age=now.getYear()-bithday.getYear();
        } else{
            age=now.getYear()-bithday.getYear()-1;
        }
        return age;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", bithday=" + bithday +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
