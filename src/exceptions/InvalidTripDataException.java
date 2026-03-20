package exceptions;

public class InvalidTripDataException extends Exception {
    public InvalidTripDataException() {
        super("Invalid trip data: check price, duration, or client ID.");
    }
    public InvalidTripDataException(String message) {
        super(message);
    }
}