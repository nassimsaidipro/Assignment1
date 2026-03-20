package exceptions;

public class InvalidAccommodationDataException extends Exception {
    public InvalidAccommodationDataException() {
        super("Invalid accommodation data: check price, stars, or hostel rules.");
    }
    public InvalidAccommodationDataException(String message) {
        super(message);
    }
}