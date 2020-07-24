package projeto.pratico.data.exception;

/**
 * https://stackify.com/java-custom-exceptions/
 * https://stackify.com/specify-handle-exceptions-java/
 * https://stackify.com/best-practices-exceptions-java/
 * https://stackify.com/common-mistakes-handling-java-exception/
 */
public class InvalidFieldException extends Exception {

    private Error error;

    public InvalidFieldException(String message, Throwable cause, Error error){
        super(message);
    }
}
