// -----------------------------------------------------------------------
// Assignment 1
// Question: 
// Written by: Darwinsh Saint-Jean (40341644)
// -----------------------------------------------------------------------
package driver;

import travel.*;

import java.util.Scanner;

import client.*;

public class Driver {

	// Arrays for the classes
	static Client[] clients = new Client[100];
	static Trip[] trips = new Trip[100];
	static Transportation[] transports = new Transportation[100];
	static Accommodation[] accommodations = new Accommodation[100];
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		// Scanner to prompt user input
		

		// Welcome message
		System.out.println("=====================================");
		System.out.println(" Welcome to SmartTravel System");
		System.out.println(" Developed by: Darwinsh and Nassim!");
		System.out.println("=====================================\n");

		// Prompting the user for their choice between the menu or the scenario tests
		System.out.println(" Please, choose an option:");
		System.out.println("1. Predefined testing scenario");
		System.out.println("2. Menu interface");
		System.out.print("Enter your choice: ");
		int choice = input.nextInt();

		if (choice == 1) {
			predefinedScenario();

		} else if (choice == 2) {

		} else {
			System.out.print("\nInvalid choice, the program will now close.");
		}

		System.out.println("\nThank you for using our SmartTravel system!");

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// This is the predefined scenario testing
	public static void predefinedScenario() {
		System.out.println("The predefined testing scenario is now running.");

		// Creating 3 clients
		Client c1 = new Client("Maygan", "Beauchamp", "mayg@gmail.com");
		Client c1Clone = new Client("Maygan", "Beauchamp", "mayg@gmail.com");
		Client c2 = new Client("Venta", "Raji", "venta@gmail.com");
		Client c3 = new Client("Veda", "Melky", "velky@gmail.com");

		int clientCount = 0;
		clients[clientCount++] = c1;
		clients[clientCount++] = c2;
		clients[clientCount++] = c3;

		// creating 2 types of each transportation
		Flight f1 = new Flight("AirCanada", "Montreal", "Paris", "Air Canada", 23.0, 650.0);
		Flight f2 = new Flight("Delta", "New York", "London", "Delta Air Lines", 20.0, 550.0);
		Train train1 = new Train("VIA Rail", "Montreal", "Toronto", "high-speed", "Business", 120.0);
		Train train2 = new Train("Amtrak", "Boston", "Washington", "regional", "Economy", 80.0);
		Bus bus1 = new Bus("Greyhound", "Montreal", "Quebec City", "Greyhound Canada", 3, 40.0);
		Bus bus2 = new Bus("FlixBus", "Paris", "Lyon", "FlixBus Europe", 2, 25.0);
		int transCount = 0;

		transports[transCount++] = f1;
		transports[transCount++] = f2;
		transports[transCount++] = train1;
		transports[transCount++] = train2;
		transports[transCount++] = bus1;
		transports[transCount++] = bus2;

		// Creating accommodations
		Hotel h1 = new Hotel("Paris Opera", "Paris", 250.0, 5);
		Hotel h2 = new Hotel("The Z hotel", "London", 150.0, 3);
		Hostel hos1 = new Hostel("Royal Oasis", "Haiti", 35.0, 8);
		Hostel hos2 = new Hostel("HI Toronto", "Toronto", 30.0, 6);
		int accomCount = 0;

		accommodations[accomCount++] = h1;
		accommodations[accomCount++] = h2;
		accommodations[accomCount++] = hos1;
		accommodations[accomCount++] = hos2;

		// Creating 3 trips
		Trip trip1 = new Trip("Paris", 7, 500.0, c1);
		trip1.setTransportation(f1);
		trip1.setAccommodation(h1);
		Trip trip2 = new Trip("Tel Aviv", 5, 400.0, c2);
		trip2.setTransportation(train1);
		trip2.setAccommodation(h2);
		Trip trip3 = new Trip("Haiti", 3, 300.0, c3);
		trip3.setTransportation(bus1);
		trip3.setAccommodation(hos1);

		int tripCount = 0;
		trips[tripCount++] = trip1;
		trips[tripCount++] = trip2;
		trips[tripCount++] = trip3;

		// Displaying everything
		System.out.println("\nAll clients: ");
		for (int i = 0; i < clientCount; i++) {
			System.out.println(clients[i]);
			System.out.println();
		}

		System.out.println("All transportation: ");
		for (int i = 0; i < transCount; i++) {
			System.out.println(transports[i]);
			System.out.println();
		}

		System.out.println("All accommodations: ");
		for (int i = 0; i < accomCount; i++) {
			System.out.println(accommodations[i]);
			System.out.println();
		}

		System.out.println("All trips: ");
		for (int i = 0; i < tripCount; i++) {
			System.out.println(trips[i]);
			System.out.println();
		}

		// testing the equals() method in various ways
		System.out.println("Comparing a client to a flight: " + c1.equals(f1));
		System.out.println("Comparing a client to another client: " + c1.equals(c2));
		System.out.println("Comparing a client to oneself: " + c1.equals(c1Clone));

		// Calculates and displays the cost of trips
		for (int i = 0; i < tripCount; i++) {
			System.out.println("Trip " + trips[i].getTripId() + " to " + trips[i].getDestination() + " for "
					+ trips[i].getDurationInDays() + " days" + "|Total price of: " + trips[i].calculateTotalCost());
		}

		// Displays the most expensive trip
		showMostExpensiveTrip(trips, tripCount);

		// Demonstration of the deep copy array

		Transportation[] copy = copyTransportationArray(transports, transCount);
		copy[0].setCompanyName("Modified name");
		System.out.println("Modified copy[0] company name to 'modified name'.");

		// Display both arrays
		System.out.println("\nOriginal array:");
		for (int i = 0; i < transCount; i++)
			System.out.println(transports[i]);

		System.out.println("\nCopied array:");
		for (int i = 0; i < transCount; i++)
			System.out.println(copy[i]);

	}
//------------------------------------------------------------------------------------------------------------------------------------

	// This is the menu
	public static void menuMode() {
		boolean running = true;

		while (running) {
			System.out.println("\n==================== MAIN MENU ====================");
			System.out.println("1. Client management");
			System.out.println("2. Trip management");
			System.out.println("3. Transportation management");
			System.out.println("4. Accommodation management");
			System.out.println("===================================================");
			System.out.println("Your choice: ");
			int menuChoice = input.nextInt();
		}

	}

// ------------------------------------------------------------------------------------------------------------------------------------

	// Finds the most expensive trip and displays it
	public static void showMostExpensiveTrip(Trip[] trips, int tripCount) {
		Trip mostExpensive = trips[0];
		for (int i = 1; i < tripCount; i++) {
			if (trips[i].calculateTotalCost() > mostExpensive.calculateTotalCost())
				mostExpensive = trips[i];
		}
		System.out.println("The most expensive trip is: " + mostExpensive);
	}

	// creates a copy of the transportation array
	public static Transportation[] copyTransportationArray(Transportation[] original, int count) {
		Transportation[] copy = new Transportation[count];

		for (int i = 0; i < count; i++) {
			if (original[i] instanceof Flight) {
				copy[i] = new Flight((Flight) original[i]);
			} else if (original[i] instanceof Train) {
				copy[i] = new Train((Train) original[i]);
			} else if (original[i] instanceof Bus) {
				copy[i] = new Bus((Bus) original[i]);
			}
		}
		return copy;
	}
}
