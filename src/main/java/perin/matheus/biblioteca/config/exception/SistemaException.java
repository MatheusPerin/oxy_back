package perin.matheus.biblioteca.config.exception;

public class SistemaException extends Exception {

    public SistemaException() {}

    public SistemaException(String message) {
        super(message);
    }

    public SistemaException(Throwable cause) {
        super(cause);
    }
}
