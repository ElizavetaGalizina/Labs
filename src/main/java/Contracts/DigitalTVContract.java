package Contracts;

import Contracts.Contract;
import Enum.Channels;
import Enity.People;

import java.time.LocalDate;

/**
 * класс конракта на цифровое телевидение
 */
public class DigitalTVContract extends Contract {

    private Channels channels;

    /**
     * конструктор без параметров
     */
    public DigitalTVContract(){

    }

    /**
     * конструктор с параметрами
     * @param id идентификатор
     * @param startDate дата заключения
     * @param endDate дата расторжения
     * @param numberContract номер контракта
     * @param owner владелец
     * @param channels пакет каналов
     */
    public DigitalTVContract(int id, LocalDate startDate, LocalDate endDate, int numberContract, People owner, Channels channels) {
        super(id, startDate, endDate, numberContract, owner);
        this.channels = channels;
    }

    /**
     * получение пакета каналов
     * @return пакет каналов
     */
    public Channels getChannels() {
        return channels;
    }

    /**
     * задание пакета каналов
     * @param channels пакет каналов
     */
    public void setChannels(Channels channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "DigitalTVContract{" +
                "  id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberContract=" + numberContract +
                ", owner=" + owner +
                ", channels=" + channels +
                '}';
    }
}
