package projeto.pratico.data.exception;

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
