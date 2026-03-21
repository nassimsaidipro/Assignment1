// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean(40341644)
// -----------------------------------------------------------------------
/*The SmartTravelService class is the central business logic layer. 
 * It maintains four arrays to store records for 
 * Clients, Trips, Transportations, and Accommodations. 
 * those functionalities are included:
 * adding clients with duplicate email detection,
 *  creating trips linked to existing clients, searching by ID,
 *  calculating trip totals, and loading/saving all data through the four
 *  FileManager classes in the persistence package
 *  custom exceptions are thrown to handle input validation.
 */

package service;

import client.Client;
import travel.*;
import exceptions.*;
import persistence.*;

import java.io.IOException;

public class SmartTravelService {

	// In-memory arrays
	private Client[] clients = new Client[100];

	private Trip[] trips = new Trip[200];
	private Transportation[] transports = new Transportation[50];
	private Accommodation[] accommodations = new Accommodation[50];

	private int clientCount = 0;

	private int tripCount = 0;
	private int transCount = 0;
	private int accomCount = 0;

	public Client getClient(int i) {
		return clients[i];
	}

	public void setClients(Client[] clients) {
		this.clients = clients;
	}

	public Trip getTrip(int i) {
		return trips[i];
	}

	public Trip[] getAllTrips() {
		return trips;
	}

	public void setTrips(Trip[] trips) {
		this.trips = trips;
	}

	public Transportation getTransports(int i) {
		return transports[i];
	}

	public void setTransports(Transportation[] transports) {
		this.transports = transports;
	}

	public Accommodation getAccoms(int i) {
		return accommodations[i];
	}

	public void setAccommodations(Accommodation[] accommodations) {
		this.accommodations = accommodations;
	}

	public int getClientCount() {
		return clientCount;
	}

	public void setClientCount(int clientCount) {
		this.clientCount = clientCount;
	}

	public int getTripCount() {
		return tripCount;
	}

	public void setTripCount(int tripCount) {
		this.tripCount = tripCount;
	}

	public int getTransCount() {
		return transCount;
	}

	public void setTransCount(int transCount) {
		this.transCount = transCount;
	}

	public int getAccomCount() {
		return accomCount;
	}

	public void setAccomCount(int accomCount) {
		this.accomCount = accomCount;
	}

	// Adds a client after verifying the email is unique.
	public void addClient(Client c) throws InvalidClientDataException, DuplicateEmailException {

		if (clientCount >= clients.length) {
			System.out.println("Client array is full.");
		}
		for (int i = 0; i < clientCount; i++) {
			if (clients[i].getEmailAdress().equalsIgnoreCase(c.getEmailAdress())) {
				throw new DuplicateEmailException("Email already exists.");
			}

		}
		clients[clientCount++] = c;
	}

	// Finds a client
	public Client findClientById(String id) throws EntityNotFoundException {

		for (int i = 0; i < clientCount; i++) {
			if (clients[i].getClientId().equalsIgnoreCase(id)) {
				return clients[i];
			}

		}

		throw new EntityNotFoundException();
	}

	// Checks if a client exists or not
	public boolean clientExists(String id) {
		for (int i = 0; i < clientCount; i++) {
			if (clients[i].getClientId().equalsIgnoreCase(id)) {
				return true;

			}
		}
		return false;

	}

	// Adds an already validated Trip to the array.
	public Trip createTrip(String destination, int duration, double basePrice, String clientId)
			throws InvalidTripDataException, EntityNotFoundException {
		if (tripCount >= trips.length) {
			System.out.println("Trip array is full.");
			return null;
		}
		Client c = findClientById(clientId);
		Trip t = new Trip(destination, duration, basePrice, c);
		trips[tripCount++] = t;

		return t;

	}
	
	
	// Adds a transportation
	public void addTransportation(Transportation t) {
		transports[transCount++] = t;
	}
	
	
	//Adds an accomodation
	public void addAccommodation(Accommodation a) {
		accommodations[accomCount++] = a;
	}

	// Calculates and returns the total cost of the trip of the chosen index.
	public double calculateTripTotal(int index) {
		if (index < 0 || index >= tripCount) {
			return 0;
		}
		return trips[index].calculateTotalCost();
	}

	// Loads all data from CSV files. Clients are loaded first.

	public void loadAllData(String directory) throws IOException {

		clientCount = ClientFileManager.loadClients(clients, directory + "clients.csv");
		transCount = TransportationFileManager.loadTransports(transports, directory + "transports.csv");
		accomCount = AccommodationFileManager.loadAccommodations(accommodations, directory + "accommodations.csv");
		tripCount = TripFileManager.loadTrips(trips, directory + "trips.csv", clients, clientCount, transports,
				transCount, accommodations, accomCount);
	}

	// Saves all data currently in arrays back to CSV files[cite: 99, 118].

	public void saveAllData(String directory) throws IOException {
		ClientFileManager.saveClients(clients, clientCount, directory + "clients.csv");
		TransportationFileManager.saveTransports(transports, transCount, directory + "transports.csv");
		AccommodationFileManager.saveAccommodations(accommodations, accomCount, directory + "accommodations.csv");
		TripFileManager.saveTrips(trips, tripCount, directory + "trips.csv");
	}

}
