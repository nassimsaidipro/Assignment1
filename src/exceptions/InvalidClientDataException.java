// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

package exceptions;

// This custom checked exception is used to validate client information.
// It ensures that fields like names and email addresses meet the required
// formatting and length constraints before a Client object is created or updated.

// Extends Exception to enforce error handling for invalid client inputs
public class InvalidClientDataException extends Exception {
    
    // Default constructor: provides a standard error message for client validation failures
    public InvalidClientDataException() {
        super("Invalid client data provided.");
    }
    
    // Parameterized constructor: allows passing specific details about which rule was broken
    public InvalidClientDataException(String message) {
        super(message);
    }
}