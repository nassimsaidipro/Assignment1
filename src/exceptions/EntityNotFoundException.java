package exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException() {
        super("The requested entity could not be found in the system.");
    }
    public EntityNotFoundException(String message) {
        super(message);
    }
}