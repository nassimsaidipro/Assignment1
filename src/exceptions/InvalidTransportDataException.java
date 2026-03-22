// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

package exceptions;

// This custom checked exception enforces business rules for transportation 
// objects (Flight, Train, Bus). It validates constraints such as minimum 
// luggage allowance or the required number of stops for a bus.

// Extends Exception to force the program to handle transportation validation errors
public class InvalidTransportDataException extends Exception {
    
    // Default constructor: passes a generic transportation rule error to the parent class
    public InvalidTransportDataException() {
        super("Invalid transportation data: check luggage or bus stops.");
    }
    
    // Parameterized constructor: allows specifying exactly which transport rule failed
    public InvalidTransportDataException(String message) {
        super(message);
    }
}