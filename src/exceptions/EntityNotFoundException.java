// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

package exceptions;

// This custom checked exception is designed to handle cases where a search 
// operation fails to locate a specific object (Client, Trip, etc.) in the system.
// By extending Exception, it forces the calling method to handle the missing 
// entity gracefully, ensuring the program doesn't crash during searches.

// Extends the Exception class to create a checked exception for search failures
public class EntityNotFoundException extends Exception {

	// Default constructor: passes a generic "not found" message to the Exception parent
	public EntityNotFoundException() {
		super("The requested entity could not be found in the system.");
	}

	// Parameterized constructor: allows for a specific error message (e.g., "Client ID C101 not found")
	public EntityNotFoundException(String message) {
		super(message);
	}
}