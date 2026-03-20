package exceptions;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("Operation failed: This email address already exists.");
    }
    public DuplicateEmailException(String message) {
        super(message);
    }
}