package lt.chomicenko.final_api.exception;

public class NoPostersFoundException extends RuntimeException {
    public NoPostersFoundException(String message) {
        super(message);
    }
}
