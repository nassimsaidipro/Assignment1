package interfaces;

// -----------------------------------------------------------------------
// Assignment 3
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

/*
 * This interface defines the contract for any object in the travel system that can 
 * be charged to a client. It ensures that any class implementing Billable provides 
 * standard methods to retrieve both the starting price and the final calculated 
 * cost, allowing for uniform financial processing across different service types.
 */

public interface Billable {

	/**
	 * Retrieves the initial base price of the service before any 
	 * duration or quantity multipliers are applied.
	 */
	double getBasePrice();

	/**
	 * Calculates and returns the final total cost of the service 
	 * based on its specific pricing logic.
	 */
	double getTotalCost();