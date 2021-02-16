package reflection;

/**
 * Класс для обработки исключений.
 */
public class InjectorException extends Exception {

    public InjectorException() {
    }

    public InjectorException(String message) {
        super(message);
    }

    public InjectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InjectorException(Throwable cause) {
        super(cause);
    }
}
