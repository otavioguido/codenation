package projeto.pratico.data.exception;

/**
 * https://stackify.com/java-custom-exceptions/
 * https://stackify.com/specify-handle-exceptions-java/
 * https://stackify.com/best-practices-exceptions-java/
 * https://stackify.com/common-mistakes-handling-java-exception/
 */
public class EventException extends Exception {

    public EventException(String message, Throwable cause){
        super(message, cause);
    }

    public EventException(String message) {
        super(message);
    }

    public EventException(Throwable cause) {
        super(cause);
    }
}
