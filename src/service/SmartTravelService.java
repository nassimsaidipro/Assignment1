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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SmartTravelService {

	// Collections
	private List<Client> clients = new ArrayList<>();

	private List<Trip> trips = new ArrayList<>();
	private List<Transportation> transports = new ArrayList<>();
	private List<Accommodation> accommodations = new ArrayList<>();
	private RecentList<Trip> recentTrips = new RecentList<>();

	public List<Client> getClients() {
		return clients;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public List<Transportation> getTransports() {
		return transports;
	}

	public List<Accommodation> getAccoms() {
		return accommodations;
	}

	public Client getClient(int i) {
	 return clients.get(i);
	 }

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Trip getTrip(int i) {
		return trips.get(i);
	}

	public List<Trip> getAllTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Transportation getTransports(int i) {
		return transports.get(i);
	}

	public void setTransports(List<Transportation> transports) {
		this.transports = transports;
	}

	public Accommodation getAccoms(int i) {
		return accommodations.get(i);
	}

	public void setAccommodations(List<Accommodation> accommodations) {
		this.accommodations = accommodations;
	}

	public int getClientCount() {
		return clients.size();
	}

	public int getTripCount() {
		return trips.size();
	}

	public int getTransCount() {
		return transports.size();
	}

	public int getAccomCount() {
		return accommodations.size();
	}

	
	  public RecentList<Trip> getRecentTrips() { return recentTrips; }
	 

	// Adds a client after verifying the email is unique.
	public void addClient(Client c) throws InvalidClientDataException, DuplicateEmailException {

		for (Client existing : clients) {
			if (existing.getEmailAdress().equalsIgnoreCase(c.getEmailAdress())) {
				throw new DuplicateEmailException("Email already exists.");

			}

		}
		clients.add(c);
	}

	// Finds a client
	public Client findClientById(String id) throws EntityNotFoundException {

		for (Client c : clients) {
			if (c.getClientId().equalsIgnoreCase(id)) {
				return c;
			}

		}

		throw new EntityNotFoundException();
	}

	// Checks if a client exists or not
	public boolean clientExists(String id) {
		for (Client c : clients) {
			if (c.getClientId().equalsIgnoreCase(id)) {
				return true;

			}
		}
		return false;

	}

	// Adds an already validated Trip to the array.
	public Trip createTrip(String destination, int duration, double basePrice, String clientId)
			throws InvalidTripDataException, EntityNotFoundException {

		Client c = findClientById(clientId);
		Trip t = new Trip(destination, duration, basePrice, c);
		trips.add(t);

		 recentTrips.addRecent(t);

		return t;

	}

	// Adds a transportation
	public void addTransportation(Transportation t) {
		transports.add(t);
	}

	// Adds an accommodation
	public void addAccommodation(Accommodation a) {
		accommodations.add(a);
	}

	// Calculates and returns the total cost of the trip of the chosen index.
	public double calculateTripTotal(int index) {
		if (index < 0 || index >= trips.size()) {
			return 0;
		}
		Trip t = trips.get(index);
		 recentTrips.addRecent(t);
		return t.calculateTotalCost();
	}

	// Loads all data from CSV files. Clients are loaded first.

	public void loadAllData(String directory) throws IOException {

		// Clients
		Client[] clientArr = new Client[100];
		int cCount = ClientFileManager.loadClients(clientArr, directory + "clients.csv");
		clients.clear();
		for (int i = 0; i < cCount; i++) {
			clients.add(clientArr[i]);
		}

		// Transports
		Transportation[] transArr = new Transportation[50];
		int tCount = TransportationFileManager.loadTransports(transArr, directory + "transports.csv");
		transports.clear();
		for (int i = 0; i < tCount; i++) {
			transports.add(transArr[i]);
		}

		// Accommodations
		Accommodation[] accArr = new Accommodation[50];
		int aCount = AccommodationFileManager.loadAccommodations(accArr, directory + "accommodations.csv");
		accommodations.clear();
		for (int i = 0; i < aCount; i++) {
			accommodations.add(accArr[i]);
		}

		// Trips
		Trip[] tripArr = new Trip[200];
		int rCount = TripFileManager.loadTrips(tripArr, directory + "trips.csv", clientArr, cCount, transArr, tCount,
				accArr, aCount);
		trips.clear();
		for (int i = 0; i < rCount; i++) {
			trips.add(tripArr[i]);
		}

	}

	// Saves all data currently in arrays back to CSV files[cite: 99, 118].

	public void saveAllData(String directory) throws IOException {
		new File(directory).mkdirs();

		// Convert lists to arrays
		ClientFileManager.saveClients(clients.toArray(new Client[0]), clients.size(), directory + "clients.csv");
		TransportationFileManager.saveTransports(transports.toArray(new Transportation[0]), transports.size(),
				directory + "transports.csv");
		AccommodationFileManager.saveAccommodations(accommodations.toArray(new Accommodation[0]), accommodations.size(),
				directory + "accommodations.csv");
		TripFileManager.saveTrips(trips.toArray(new Trip[0]), trips.size(), directory + "trips.csv");
	}

	// Verifies if the email is a duplicate or not
	public void checkDuplicateEmail(String email) throws DuplicateEmailException {
		for (Client c : clients) {
			if (c.getEmailAdress().equalsIgnoreCase(email))
				throw new DuplicateEmailException("Email already exists.");
		}
	}

}
