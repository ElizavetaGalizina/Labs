package contracts;

import enity.People;

import java.time.LocalDate;

/**
 * класс контракта на проводной интернет.
 */
public class InternetContract extends Contract {
    /**
     * скорость интернета.
     */
    private int speed;

    /**
     * конструктор без параметров.
     */
    public InternetContract() {

    }

    /**
     * конструктор с параметрами.
     * @param id идентификатор
     * @param startDate дата заключения
     * @param endDate дата расторжения
     * @param numberContract номер контракта
     * @param owner владелец
     * @param speed максимальная скорость
     */
    public InternetContract(final int id, final LocalDate startDate, final LocalDate endDate,
                            final int numberContract, final People owner, final int speed) {
        super(id, startDate, endDate, numberContract, owner);
        this.speed = speed;
    }

    /**
     * возвращает максимальную скорость.
     * @return максимальная скорость
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * задает максимальную скорость.
     * @param speed максимальная скорость
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "InternetContract{"
                + " id=" + id
                + ", startDate=" + startDate
                + ", endDate=" + endDate
                + ", numberContract=" + numberContract
                + ", owner=" + owner
                + ", speed=" + speed
                + '}';
    }
}
