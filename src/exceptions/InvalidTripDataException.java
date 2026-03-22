// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

package exceptions;

// This custom checked exception handles validation for Trip objects. 
// It ensures that business rules regarding base price, trip duration limits, 
// and valid client associations are strictly followed during trip creation.

// Extends Exception to ensure trip-related errors are caught and handled by the driver
public class InvalidTripDataException extends Exception {
    
    // Default constructor: provides a standard message covering the main trip validation rules
    public InvalidTripDataException() {
        super("Invalid trip data: check price, duration, or client ID.");
    }
    
    // Parameterized constructor: allows for a tailored error message (e.g., specific missing Client ID)
    public InvalidTripDataException(String message) {
        super(message);
    }
}