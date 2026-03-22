// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

package exceptions;

// This custom checked exception validates the creation and modification 
// of accommodation objects (Hotel, Hostel). It enforces business rules,
// ensuring data like price per night and star ratings remain within allowed limits.

// Extends Exception to force the program to catch and handle invalid accommodation data
public class InvalidAccommodationDataException extends Exception {
    
    // Default constructor: passes a standard validation error message to the parent Exception
    public InvalidAccommodationDataException() {
        super("Invalid accommodation data: check price, stars, or hostel rules.");
    }
    
    // Parameterized constructor: allows throwing a specific error message based on the rule broken
    public InvalidAccommodationDataException(String message) {
        super(message);
    }
}