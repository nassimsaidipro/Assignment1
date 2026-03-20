package exceptions;

public class InvalidTransportDataException extends Exception {
	public InvalidTransportDataException() {
		super("Invalid transportation data: check luggage or bus stops.");
	}
	public InvalidTransportDataException(String message) {
		super(message);
	}
}

