package contracts;

import enums.Channels;
import enity.People;

import java.time.LocalDate;

/**
 * класс конракта на цифровое телевидение.
 */
public class DigitalTVContract extends Contract {
    /**
     * пакет каналов,доступных по контракту.
     */
    private  Channels channels;

    /**
     * конструктор без параметров.
     */
    public DigitalTVContract() {

    }

    /**
     * конструктор с параметрами.
     * @param id идентификатор
     * @param startDate дата заключения
     * @param endDate дата расторжения
     * @param numberContract номер контракта
     * @param owner владелец
     * @param channels пакет каналов
     */
    public DigitalTVContract(final int id, final LocalDate startDate, final LocalDate endDate,
                             final int numberContract, final People owner,
                             final Channels channels) {
        super(id, startDate, endDate, numberContract, owner);
        this.channels = channels;
    }

    /**
     * получение пакета каналов.
     * @return пакет каналов
     */
    public final Channels getChannels() {
        return channels;
    }

    /**
     * задание пакета каналов.
     * @param channels пакет каналов
     */
    public final void  setChannels(final Channels channels) {
        this.channels = channels;
    }

    @Override
    public final String toString() {
        return "DigitalTVContract{"
                + "  id=" + id
                + ", startDate=" + startDate
                + ", endDate=" + endDate
                + ", numberContract=" + numberContract
                + ", owner=" + owner
                + ", channels=" + channels
                + '}';
    }
}
