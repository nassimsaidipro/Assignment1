// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

// This class defines a custom unchecked exception for the system.
// It is thrown to prevent duplicate data entries whenever there is an attempt 
// to register or update a client using an email address that is already in use.

package exceptions;

// Extends RuntimeException to create an unchecked exception
public class DuplicateEmailException extends RuntimeException {
    
    // Default constructor: passes a standard error message to the parent class
    public DuplicateEmailException() {
        super("Operation failed: This email address already exists.");
    }
    
    // Parameterized constructor: allows the program to pass a custom error message
    public DuplicateEmailException(String message) {
        super(message);
    }
}