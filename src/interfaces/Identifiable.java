package interfaces;

// -----------------------------------------------------------------------
// Assignment 3
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

/*
 * This interface establishes a contract for any object that requires a unique 
 * tracking label within the travel system. It forces implementing classes to 
 * provide a standardized way to retrieve their specific ID string, which is 
 * essential for object-linking and database lookup operations.
 */

public interface Identifiable {
	
	/**
	 * Returns the unique identification string associated with the object.
	 */
	String getId();

}