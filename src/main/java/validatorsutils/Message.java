package validatorsutils;

/**
 * Класс, который представляет собой резльтат проверки контракта.
 */
public class Message {

    /**
     * Проверяющий компонент.
     */
    private String string;
    /**
     * Статус проверки.
     */
    private Status status;

    /**
     * Конструктор без параметров.
     */
    public Message() {
    }

    /**
     * Конструктор с параметрами.
     * @param string проверяющий компонент контракта
     * @param status статус проверки
     */
    public Message(String string, Status status) {
        this.string = string;
        this.status = status;
    }

    /**
     * Конструктор для проверяющего компонента.
     * @param string проверяющий компонент.
     */
    public Message(String string) {
        this.string = string;
    }

    /**
     * Конструктор для статуса.
     * @param status статус проверки
     */
    public Message(Status status) {
        this.status = status;
    }

    /**
     * Получение проверяющего компонента.
     * @return проверяющий компонент
     */
    public String getString() {
        return string;
    }

    /**
     * Установка проверяющего компонента
     * @param string проверющий компонент.
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * Получение статуса проверки.
     * @return статус проверки
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Задание статуса проверки.
     * @param status статус проверки
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{"
                + "string='" + string + '\''
                + ", status=" + status
                + '}';
    }
}
