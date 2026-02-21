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
	static int clientCount = 0;
	static int tripCount = 0;

	public static void main(String[] args) {

		// Scanner to prompt user input

		// Welcome message
		System.out.println("=====================================");
		System.out.println(" Welcome to SmartTravel System");
		System.out.println(" Developed by: Darwinsh and Nassim!");
		System.out.println("=====================================\n");

		// Prompting the user for their choice between the menu or the scenario tests
		System.out.println("Please, choose an option:");
		System.out.println("1. Predefined testing scenario");
		System.out.println("2. Menu interface");
		System.out.print("Enter your choice: ");
		int choice = input.nextInt();

		if (choice == 1) {
			predefinedScenario();

		} else if (choice == 2) {
			menuMode();

		} else {
			System.out.print("\nInvalid choice, the program will now close.");
		}

		System.out.println("\nThank you for using our SmartTravel system!");

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// This is the predefined scenario testing
	public static void predefinedScenario() {
		System.out.println("\nThe predefined testing scenario is now running.");

		// Creating 3 clients
		Client c1 = new Client("Maygan", "Beauchamp", "mayg@gmail.com");
		Client c1Clone = new Client("Maygan", "Beauchamp", "mayg@gmail.com");
		Client c2 = new Client("Venta", "Raji", "venta@gmail.com");
		Client c3 = new Client("Veda", "Melky", "velky@gmail.com");

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
			System.out.println();
			System.out.println("Trip " + trips[i].getTripId() + " to " + trips[i].getDestination() + " for "
					+ trips[i].getDurationInDays() + " days" + "|Total price of: " + trips[i].calculateTotalCost());

		}

		// Displays the most expensive trip
		System.out.println();
		showMostExpensiveTrip(trips, tripCount);

		// Demonstration of the deep copy array
		System.out.println();
		Transportation[] copy = copyTransportationArray(transports, transCount);
		copy[0].setCompanyName("Modified name");
		System.out.println("Modified copy[0] company name to 'modified name'.");

		// Display both arrays
		System.out.println("\nOriginal array:");
		for (int i = 0; i < transCount; i++)
			System.out.println(transports[i]);
		System.out.println();

		System.out.println("\nCopied array:");
		for (int i = 0; i < transCount; i++)
			System.out.println(copy[i]);
		System.out.println();

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
			System.out.println("0. Exit");
			System.out.println("===================================================");
			System.out.print("Your choice: ");
			int menuChoice = input.nextInt();

			switch (menuChoice) {
			case 1:
				clientManagement();
				break;
			case 2:
				tripManagement();
				break;
			case 3:
				transportManagement();
				break;
			case 4:
				accomManagement();
				break;

			}
		}

	}

	public static void clientManagement() {
		boolean menuReturn = false;
		while (!menuReturn) {
			System.out.println("--Client Management--");
			System.out.println("1. Add a client");
			System.out.println("2. Edit a client");
			System.out.println("3. Delete a client");
			System.out.println("4. List all clients");
			System.out.println("0. Go back to main menu");
			System.out.print("Your choice: ");

			int clientChoice = input.nextInt();
			input.nextLine();

			switch (clientChoice) {
			case 1:
				if (clientCount >= clients.length) {
					System.out.println("the array is full :(");
					break;

				}
				System.out.print("\nEnter the first name: ");
				String firstn = input.nextLine().trim();
				System.out.print("\nEnter the last name: ");
				String lastn = input.nextLine().trim();
				System.out.print("\nEnter the email address: ");
				String email = input.nextLine().trim();

				Client newClient = new Client(firstn, lastn, email);
				clients[clientCount] = newClient;
				clientCount++;

				System.out.println("\nClient added successfully: ");
				System.out.println(newClient);
				System.out.println();

				break;
			case 2:
				if (clientCount == 0) {
					System.out.println("There are no clients to edit.");
					System.out.println();
					break;
				}

				System.out.print("Enter client ID to edit: ");
				String idEdit = input.nextLine();
				int index = -1;

				for (int i = 0; i < clientCount; i++) {
					if (clients[i].getClientId().equalsIgnoreCase(idEdit)) {
						index = i;
						break;
					}

				}

				if (index == -1) {
					System.out.println("Client not found");
					break;
				}

				else {
					System.out.println("\nEditing client: ");
					System.out.println(clients[index]);

					System.out.print("New first name: ");
					String newFirst = input.nextLine();

					System.out.print("New last name: ");
					String newLast = input.nextLine();

					System.out.print("New email: ");
					String newEmail = input.nextLine();

					clients[index].setEmailAdress(newEmail);
					clients[index].setFirstName(newFirst);
					clients[index].setLastName(newLast);

					System.out.println("The client has been updated: ");
					System.out.println(clients[index]);

				}

				break;

			case 3:
				if (clientCount == 0) {
					System.out.println("No clients available to delete.");
					break;
				}
				input.nextLine();
				System.out.print("Enter client ID to delete: ");
				String idDelete = input.nextLine().trim();
				int deleteIndex = -1;
				for (int i = 0; i < clientCount; i++) {
					if (clients[i].getClientId().equals(idDelete)) {
						deleteIndex = i;
						break;
					}
				}
				if (deleteIndex == -1) {
					System.out.println("Client not found.");
				} else {
					for (int i = deleteIndex; i < clientCount - 1; i++) {
						clients[i] = clients[i + 1];

					}

					clients[clientCount - 1] = null;
					clientCount--;
					System.out.println("Client deleted successfully.");

				}

				break;
			case 4:
				if (clientCount == 0) {
					System.out.println("There are no clients to display");
					break;
				}
				for (int i = 0; i < clientCount; i++) {
					System.out.println(clients[i]);
					System.out.println();
				}
				break;
			case 0:
				menuReturn = true;
				break;
			default:
				System.out.println("Invalid option.");
			}
		}
	}

	public static void tripManagement() {
		boolean menuReturn = false;
		while (!menuReturn) {
			System.out.println("--Client Management--");
			System.out.println("	1. Create a trip");
			System.out.println("	2. Edit a trip's information");
			System.out.println("	3. Cancel a trip");
			System.out.println("	4. List all trips");
			System.out.println("	5. List all trips of a specific client");
			System.out.println("	0. Go back to main menu");
			System.out.print("Your choice: ");

			int tripChoice = input.nextInt();

			switch (tripChoice) {
			case 1:
				
				if(tripCount>=trips.length) {
					System.out.println("There's no space left to add a trip.");
					break;
				}
				
				if (clientCount == 0) {
			        System.out.println("No clients available. Create a client first.");
			        break;
			    }
				
				System.out.print("Destination: ");
				String destin = input.nextLine();

				System.out.print("Duration of the trip: ");
				int dur = input.nextInt();
				input.nextLine();

				System.out.print("Price of the trip: ");
				double price = input.nextDouble();
				input.nextLine();

				System.out.print("Client's ID: ");
				String clientId = input.nextLine();
				
				Client clientFound = null;

				for (int i = 0; i < clientCount; i++) {
					if (clients[i].getClientId().equalsIgnoreCase(clientId)) {
						clientFound = clients[i];
						break;
					}
				}
				if(clientFound ==null) {
					System.out.println("Client no found.");
					break;
				}
				
				Trip newTrip = new Trip(destin,dur,price,clientFound);
				trips[tripCount++] = newTrip;
				System.out.println("Trip added successfully: ");
				 System.out.println(newTrip);
				break;

			case 2:
				if(tripCount==0) {
					System.out.println("There are no trips to edit");
					break;
				}
				
				
				System.out.println("Enter the trip ID to edit: ");
				String tripId = input.nextLine();
				
				int index = -1;
				
				for(int i = 0; i<tripCount;i++) {
					if(trips[i].getTripId().equalsIgnoreCase(tripId)) {
						index = i;
					}
				}
				
				if(index == -1) {
					System.out.println("Trip not found");
				}
				
				
				break;

			case 3:
				break;
			case 4:
				break;
			case 5:
				break;

			case 0:
				menuReturn = true;
				break;
			default:
				System.out.println("Invalid option.");
			}
		}
	}

	public static void transportManagement() {

	}

	public static void accomManagement() {

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
