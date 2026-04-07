package interfaces;

// -----------------------------------------------------------------------
// Assignment 3
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

/*
 * This interface defines the contract for objects that can be saved to a 
 * flat-file database. It ensures that implementing classes provide a 
 * standardized string representation suitable for CSV storage, allowing 
 * the GenericFileManager to process diverse object types uniformly.
 */

public interface CsvPersistable {

	/**
	 * Converts the object's current state into a single semicolon-delimited 
	 * string representing one row in a CSV file.
	 */
	String toCsvRow(); 
	
}