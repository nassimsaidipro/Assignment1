// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644)
// -----------------------------------------------------------------------

// -----------------------------------------------------------------------
// This SmartTravel program is a travel agency management system,
// allowing employees to manage clients, trips, transportation options, and accommodations.
// The program provides two modes:
// 1. A predefined testing scenario that demonstrates the functionality:
//    - Object creation and display using toString()
//    - The equals() method tested across different cases
//    - Polymorphic cost calculation for trips
//    - A method to find the most expensive trip
//    - Deep copy methods for transportation and accommodation arrays
// 2. A menu interface that allows the user to add, edit, remove,
//    and display information for all elements.
//
// The program uses inheritance and polymorphism for different types of
// transportation (Flight, Train, Bus) and accommodations (Hotel, Hostel).
// -----------------------------------------------------------------------
package driver;

import travel.*;
import visualization.DashboardGenerator;

import java.util.Scanner;

import client.*;
import exceptions.InvalidClientDataException;
import service.SmartTravelService;

import java.io.*;
import exceptions.DuplicateEmailException;
import exceptions.InvalidTripDataException;
import exceptions.EntityNotFoundException;
import exceptions.InvalidTransportDataException;
import exceptions.InvalidAccommodationDataException;

public class SmartTravelDriver {

	// Service layer owns all arrays
	static SmartTravelService service = new SmartTravelService();
	// Scanner to prompt user input
	static Scanner input = new Scanner(System.in);

	// Counters to track how many elements are in each array

	public static void main(String[] args) {

		// Displays a welcome message
		System.out.println("=====================================");
		System.out.println(" Welcome to SmartTravel System");
		System.out.println(" Developed by: Darwinsh and Nassim!");
		System.out.println("=====================================\n");

		// Prompts the user for the execution mode
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

		// Display closing message
		System.out.println("\nThank you for using our SmartTravel system! The program will now close.");

	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// This is the predefined scenario testing
	public static void predefinedScenario() {
		System.out.println("\nThe predefined testing scenario is now running.");

		// Creating 3 clients to sample
		try {
			service.addClient(new Client("Maygan", "Beauchamp", "mayg@gmail.com"));

			service.addClient(new Client("Venta", "Raji", "venta@gmail.com"));
			service.addClient(new Client("Veda", "Melky", "velky@gmail.com"));
			System.out.println("Clients created successfully.");
		} catch (InvalidClientDataException | DuplicateEmailException e) {
			System.out.println(e.getMessage());
		}

		// creating 2 types of each transportation
		try {
			Flight f1 = new Flight("AirCanada", "Montreal", "Paris", "Air Canada", 23.0, 650.0);
			service.addTransportation(f1);
			Flight f2 = new Flight("Delta", "New York", "London", "Delta Air Lines", 20.0, 550.0);
			service.addTransportation(f2);
			Train train1 = new Train("VIA Rail", "Montreal", "Toronto", "high-speed", "Business", 120.0);
			service.addTransportation(train1);
			Train train2 = new Train("Amtrak", "Boston", "Washington", "regional", "Economy", 80.0);
			service.addTransportation(train2);
			Bus bus1 = new Bus("Greyhound", "Montreal", "Quebec City", "Greyhound Canada", 3, 40.0);
			service.addTransportation(bus1);
			Bus bus2 = new Bus("FlixBus", "Paris", "Lyon", "FlixBus Europe", 2, 25.0);
			service.addTransportation(bus2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Creating accommodations
		try {
			service.addAccommodation(new Hotel("Paris Opera", "Paris", 250.0, 5));
			Hotel h2 = new Hotel("The Z hotel", "London", 150.0, 3);
			service.addAccommodation(h2);
			Hostel hos1 = new Hostel("Royal Oasis", "Haiti", 35.0, 8);
			service.addAccommodation(hos1);
			Hostel hos2 = new Hostel("HI Toronto", "Toronto", 30.0, 6);
			service.addAccommodation(hos2);
		} catch (InvalidAccommodationDataException e) {

			System.out.println(e.getMessage());
		}

		// Creating 3 trips

		try {
			Trip trip1 = service.createTrip("Paris", 7, 500.0, service.getClient(0).getClientId());

			trip1.setTransportation(service.getTransports(0));
			trip1.setAccommodation(service.getAccoms(0));
			service.getClient(0).setAmountSpent(service.getClient(0).getTotalSpent() + trip1.calculateTotalCost());
			System.out.println("Trip created: " + trip1);
			Trip trip2 = service.createTrip("Tel Aviv", 5, 400.0, service.getClient(1).getClientId());
			trip2.setTransportation(service.getTransports(1));
			trip2.setAccommodation(service.getAccoms(1));
			service.getClient(1).setAmountSpent(service.getClient(1).getTotalSpent() + trip2.calculateTotalCost());
			Trip trip3 = service.createTrip("Haiti", 3, 300.0, service.getClient(2).getClientId());
			trip3.setTransportation(service.getTransports(2));
			trip3.setAccommodation(service.getAccoms(2));
			service.getClient(2).setAmountSpent(service.getClient(2).getTotalSpent() + trip3.calculateTotalCost());
		} catch (InvalidTripDataException | EntityNotFoundException e) {
			System.out.println(e.getMessage());
		}

		// 5. Triggers InvalidClientDataException
		System.out.println("\n ---- Testing InvalidClientDataException (bad email)...");
		try {
			service.addClient(new Client("Bad", "Email", "notanemail"));
		} catch (InvalidClientDataException e) {
			System.out.println(e.getMessage());
		} catch (DuplicateEmailException e) {
			System.out.println(e.getMessage());
		}

		// 6. Triggers DuplicateEmailException
		System.out.println("\n ---- Testing DuplicateEmailException (duplicate email)...");
		try {
			service.addClient(new Client("Maygan", "Duplicate", "mayg@gmail.com"));
		} catch (InvalidClientDataException e) {
			System.out.println(e.getMessage());
		} catch (DuplicateEmailException e) {
			System.out.println(e.getMessage());
		}

		// 7. Triggers InvalidTripDataException
		System.out.println("\n ---- Testing InvalidTripDataException (price < $100)...");
		try {
			service.createTrip("Nowhere", 2, 50.0, service.getClient(0).getClientId());
		} catch (InvalidTripDataException e) {
			System.out.println(e.getMessage());
		} catch (EntityNotFoundException e) {
			System.out.println(e.getMessage());
		}

		// 8. Triggers EntityNotFoundException
		System.out.println("\n ---- Testing EntityNotFoundException (bad client ID)...");
		try {
			service.findClientById("C9999");
		} catch (EntityNotFoundException e) {
			System.out.println(e.getMessage());
		}

		// 9. Triggers InvalidTransportDataException (bus with 0 stops)
		System.out.println("\n ----Testing InvalidTransportDataException (bus 0 stops)...");
		try {
			new Bus("BadBus", "A", "B", "BadCo", 0, 30.0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// 10. Triggers InvalidAccommodationDataException (hostel > $150)
		System.out.println("\n ---- Testing InvalidAccommodationDataException (hostel > $150)...");
		try {
			new Hostel("Fancy", "Paris", 200.0, 4);
		} catch (InvalidAccommodationDataException e) {
			System.out.println(e.getMessage());
		}

		// Displaying all created objects by using toString()
		System.out.println("\nAll clients: ");
		for (int i = 0; i < service.getClientCount(); i++) {
			System.out.println(service.getClient(i));
			System.out.println();
		}
		System.out.println("All transportation: ");
		for (int i = 0; i < service.getTransCount(); i++) {
			System.out.println(service.getTransports(i));
			System.out.println();
		}
		System.out.println("All accommodations: ");
		for (int i = 0; i < service.getAccomCount(); i++) {
			System.out.println(service.getAccoms(i));
			System.out.println();
		}
		System.out.println("All trips: ");
		for (int i = 0; i < service.getTripCount(); i++) {
			System.out.println(service.getTrip(i));
			System.out.println();
		}

		// testing the equals() method in various ways
		// Comparing objects of different classes
		System.out.println("Comparing a client to a flight: " + service.getClient(0).equals(service.getTransports(0)));
		// Comparing two clients with different attributes
		System.out
				.println("Comparing a client to another client: " + service.getClient(0).equals(service.getClient(1)));
		// Comparing two clients with the same attributes
		Client c1Clone;
		try {
			c1Clone = new Client("Maygan", "Beauchamp", "mayg@gmail.com");
			System.out.println("Comparing a client to oneself: " + service.getClient(0).equals(c1Clone));
		} catch (InvalidClientDataException e) {

			System.out.println(e.getMessage());
			;
		}

		// Calculates and displays the cost of trips
		for (int i = 0; i < service.getTripCount(); i++) {
			System.out.println();
			System.out.println("Trip " + service.getTrip(i).getTripId() + " to " + service.getTrip(i).getDestination()
					+ " for " + service.getTrip(i).getDurationInDays() + " days" + "|Total price of: "
					+ service.getTrip(i).calculateTotalCost());

		}

		// Displays the most expensive trip
		System.out.println();
		showMostExpensiveTrip();

		// Create copy of transportation array, modify an element, then display both
		// arrays
		System.out.println();
		Transportation[] copy = copyTransportationArray();
		copy[0].setCompanyName("Modified name");
		System.out.println("Modified copy[0] company name to 'modified name'.");

		// Display both arrays
		System.out.println("\nOriginal array:");
		for (int i = 0; i < service.getTransCount(); i++)
			System.out.println(service.getTransports(i));
		System.out.println();

		System.out.println("\nCopied array:");
		for (int i = 0; i < service.getTransCount(); i++)
			System.out.println(copy[i]);
		System.out.println();

		// Save all data
		System.out.println("\n ---- Saving all data to output/data/...");
		try {
			service.saveAllData("output/data/");
			System.out.println("Save successful.");
		} catch (IOException e) {
			System.out.println("Save failed: " + e.getMessage());
		}

		// Load all data back
		System.out.println("\n ---- Loading all data from output/data/...");
		try {
			service.loadAllData("output/data/");
			System.out.println("Load successful.");
		} catch (IOException e) {
			System.out.println("Load failed: " + e.getMessage());
		}

	}
//------------------------------------------------------------------------------------------------------------------------------------

	// Displays the menu until the user chooses to exit.
	public static void menuMode() {
		boolean running = true;

		// Prompting the user for the management operation and additional operations.
		while (running) {
			System.out.println("\n==================== MAIN MENU ====================");
			System.out.println("1. Client management");
			System.out.println("2. Trip management");
			System.out.println("3. Transportation management");
			System.out.println("4. Accommodation management");
			System.out.println("5. Show the most expensive trip");
			System.out.println("6. Deep copy of the transportation array");
			System.out.println("7. Deep copy of the accommodation array");
			System.out.println("8. Total cost of a trip");
			System.out.println("9.  List All Data Summary");
			System.out.println("10. Load All Data (output/data/*.csv)");
			System.out.println("11. Save All Data (output/data/*.csv)");
			System.out.println("12. Run Predefined Scenario");
			System.out.println("13. Generate Dashboard");
			System.out.println("0. Exit");
			System.out.println("===================================================");
			System.out.print("Your choice: ");
			int menuChoice = input.nextInt();
			input.nextLine();

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

			// Find and display the most expensive trip
			case 5:
				if (service.getTripCount() == 0) {
					System.out.println("There are no trips available");

				} else {
					showMostExpensiveTrip();
				}

				break;

			// Create and display a deep copy of the transportation array
			case 6:
				if (service.getTransCount() == 0) {
					System.out.println("No transportation options available.");
				} else {
					Transportation[] copyT = copyTransportationArray();
					copyT[0].setCompanyName("Modified name");
					System.out.println("Modified copy[0] company name to 'Modified name'.");
					System.out.println("\nOriginal array:");
					for (int i = 0; i < service.getTransCount(); i++)
						System.out.println(service.getTransports(i));
					System.out.println("\nCopied array:");
					for (int i = 0; i < service.getTransCount(); i++)
						System.out.println(copyT[i]);
				}
				break;

			// Create and display a deep copy of the accommodation array
			case 7:
				if (service.getAccomCount() == 0) {
					System.out.println("No accommodations available.");
				} else {
					Accommodation[] copyA = copyAccommodationArray();
					copyA[0].setName("Modified name");
					System.out.println("\nOriginal accommodation array:");
					for (int i = 0; i < service.getAccomCount(); i++)
						System.out.println(service.getAccoms(i));
					System.out.println("\nCopied accommodation array:");
					for (int i = 0; i < service.getAccomCount(); i++)
						System.out.println(copyA[i]);
				}
				break;
			case 8:
				if (service.getTripCount() == 0) {
					System.out.println("No trips available.");
					break;
				}
				System.out.print("Enter the trip ID: ");
				String costTripId = input.nextLine().trim();
				boolean tripFound = false;
				for (int i = 0; i < service.getTripCount(); i++) {
					if (service.getTrip(i).getTripId().equalsIgnoreCase(costTripId)) {
						System.out.println(
								"Trip: " + service.getTrip(i).getTripId() + " to " + service.getTrip(i).getDestination()
										+ " for " + service.getTrip(i).getDurationInDays() + " days");
						System.out.println("Total cost: $" + service.getTrip(i).calculateTotalCost());
						tripFound = true;
						break;
					}
				}
				if (!tripFound) {
					System.out.println("Trip not found.");
				}
				break;

			case 9:
				if (service.getTripCount() == 0) {
					System.out.println("There are no trips.");
				} else {
					for (int i = 0; i < service.getTripCount(); i++) {
						System.out.println(service.getTrip(i));
						System.out.println();
					}
				}

				break;

			case 10:

				try {
					service.loadAllData("output/data/");
					System.out.println("All data loaded successfully.");
				} catch (IOException e) {
					System.out.println("Error saving data: " + e.getMessage());
					// ERROR LOGGER
				}

				break;

			case 11:
				try {
					service.saveAllData("output/data/");
					System.out.println("All data saved successfully.");
				} catch (IOException e) {
					System.out.println("Error saving data: " + e.getMessage());
					// ERROR LOGGER
				}

				break;

			case 12:
				predefinedScenario();
				break;

			case 13:
				try {
					DashboardGenerator.generateDashboard(service);
				} catch (IOException e) {
					System.out.println("Dashboard generation failed: " + e.getMessage());
				}
				break;
			case 0:
				running = false;
				break;

			}
		}

	}

	// Handles all operations related to clients (add, edit, delete, list)
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
			// Add a new client
			case 1:

				System.out.print("\nEnter the first name: ");
				String firstn = input.nextLine().trim();
				System.out.print("\nEnter the last name: ");
				String lastn = input.nextLine().trim();
				System.out.print("\nEnter the email address: ");
				String email = input.nextLine().trim();

				// Create a client and store in array
				try {
					Client newClient = new Client(firstn, lastn, email);
					service.addClient(newClient);

					System.out.println("\nClient added successfully: ");
					System.out.println(newClient);
					System.out.println();
				} catch (InvalidClientDataException | DuplicateEmailException e) {
					System.out.println("An error has occured");
				}

				break;

			// Edit an existing client by ID
			case 2:
				if (service.getClientCount() == 0) {
					System.out.println("There are no clients to edit.");
					System.out.println();
					break;
				}
				// Search for client by ID
				System.out.print("Enter client ID to edit: ");
				String idEdit = input.nextLine().trim();

				try {
					Client c = service.findClientById(idEdit);
					System.out.println("\nEditing client: ");
					System.out.println(c);
					System.out.println();
					System.out.print("New first name: ");
					String newFirst = input.nextLine();

					System.out.print("New last name: ");
					String newLast = input.nextLine();

					System.out.print("New email: ");
					String newEmail = input.nextLine();

					service.checkDuplicateEmail(newEmail);
					c.setFirstName(newFirst);
					c.setLastName(newLast);
					c.setEmailAdress(newEmail);
					System.out.println("The client has been updated: ");
					System.out.println(c);
					System.out.println();

				} catch (EntityNotFoundException e) {
					System.out.println("Client not found.");
				} catch (DuplicateEmailException e) {
					System.out.println("Email already exists: " + e.getMessage());
				} catch (InvalidClientDataException e) {
					System.out.println("Invalid data: " + e.getMessage());
				}

				break;

			// Delete a client by ID
			case 3:
				if (service.getClientCount() == 0) {
					System.out.println("No clients available to delete.");
					break;
				}

				// Search for client by ID
				System.out.print("Enter client ID to delete: ");
				String idDelete = input.nextLine().trim();
				int deleteIndex = -1;
				int clientCount = service.getClientCount();

				for (int i = 0; i < clientCount; i++) {
					if (service.getClient(i).getClientId().equalsIgnoreCase(idDelete)) {
						deleteIndex = i;
						break;
					}
				}
				if (deleteIndex == -1) {
					System.out.println("Client not found.");
				} else {
					Client[] clientsArray = service.getClients();
					int currentCount = service.getClientCount();
					for (int i = deleteIndex; i < currentCount - 1; i++) {
						clientsArray[i] = clientsArray[i + 1];

					}

					clientsArray[currentCount - 1] = null;
					service.setClientCount(currentCount - 1);
					System.out.println("Client deleted successfully.");

				}

				break;

			// List all clients
			case 4:
				if (service.getClientCount() == 0) {
					System.out.println("There are no clients to display");
					break;
				}
				for (int i = 0; i < service.getClientCount(); i++) {
					System.out.println(service.getClient(i));
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

	// Handles all operations related to trips (create, edit, cancel, list)
	public static void tripManagement() {
		boolean menuReturn = false;
		while (!menuReturn) {
			System.out.println("--Trip Management--");
			System.out.println("1. Create a trip");
			System.out.println("2. Edit a trip's information");
			System.out.println("3. Cancel a trip");
			System.out.println("4. List all trips");
			System.out.println("5. List all trips of a specific client");
			System.out.println("0. Go back to main menu");
			System.out.print("Your choice: ");

			int tripChoice = input.nextInt();
			input.nextLine();

			switch (tripChoice) {

			// Create a new trip and associate it with a client
			case 1:
				int clientCount = service.getClientCount();

				if (clientCount == 0) {
					System.out.println("No clients available. Create a client first.");
					break;
				}
				// Ask the user for trip details
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

				// Trip newTrip = null;

				try {
					Trip newTrip = service.createTrip(destin, dur, price, clientId);

					if (newTrip != null) {
						newTrip = attachTransportation(newTrip);
						newTrip = attachAccommodation(newTrip);
						System.out.println("Trip added successfully: ");
						System.out.println(newTrip);
					}

				} catch (InvalidTripDataException e) {
					System.out.println(e.getMessage());
				} catch (EntityNotFoundException e) {
					System.out.println("Client not found.");
				}

				break;

			// Edit an existing trip by ID
			case 2:
				if (service.getTripCount() == 0) {
					System.out.println("There are no trips to edit");
					break;
				}

				// Search for trip by ID
				System.out.print("Enter the trip ID to edit: ");
				String tripId = input.nextLine();

				int index = -1;

				for (int i = 0; i < service.getTripCount(); i++) {
					if (service.getTrip(i).getTripId().equalsIgnoreCase(tripId)) {
						index = i;
						break;
					}
				}

				if (index == -1) {
					System.out.println("Trip not found");
					break;
				} else {
					// Update the trip with new values
					System.out.print("New destination: ");
					service.getTrips()[index].setDestination(input.nextLine());

					try {
						System.out.print("New duration: ");
						service.getTrips()[index].setDurationInDays(input.nextInt());
						input.nextLine();

						System.out.print("New price: ");
						service.getTrips()[index].setBasePrice(input.nextDouble());
						input.nextLine();
					} catch (InvalidTripDataException e) {
						System.out.println("Invalid data: " + e.getMessage());
						break;
					}
					// Edit trannsportation
					if (service.getTransCount() > 0) {
						System.out.print("\nWould you like to change the transportation? (yes/no): ");
						String transChoice = input.nextLine().trim();

						if (transChoice.equalsIgnoreCase("yes")) {
							System.out.println("\nSelect transportation type:");
							System.out.println("1. Flight");
							System.out.println("2. Train");
							System.out.println("3. Bus");
							System.out.print("Your choice: ");
							int transType = input.nextInt();
							input.nextLine();

							System.out.println("\nAvailable options:");
							boolean anyTransFound = false;

							for (int i = 0; i < service.getTransCount(); i++) {
								Transportation t = service.getTransports()[i];
								if (transType == 1 && t instanceof Flight) {
									System.out.println(t);
									System.out.println();
									anyTransFound = true;
								} else if (transType == 2 && t instanceof Train) {
									System.out.println(t);
									System.out.println();
									anyTransFound = true;
								} else if (transType == 3 && t instanceof Bus) {
									System.out.println(t);
									System.out.println();
									anyTransFound = true;
								}
							}

							if (!anyTransFound) {
								System.out.println("No transportation options of that type are available.");
							} else {
								System.out.print("Enter the transportation ID to attach: ");
								String transId = input.nextLine().trim();
								boolean transFound = false;
								for (int i = 0; i < service.getTransCount(); i++) {
									if (service.getTransports(i).getTransportId().equalsIgnoreCase(transId)) {
										service.getTrips()[index].setTransportation(service.getTransports(i));
										System.out.println("Transportation updated successfully.");
										transFound = true;
										break;
									}
								}
								if (!transFound) {
									System.out.println("Transportation ID not found. Transportation was not changed.");
								}
							}
						}
					} else {
						System.out.println("No transportation options available to attach.");
					}

					// Edit accommodation
					if (service.getAccomCount() > 0) {
						System.out.print("\nWould you like to change the accommodation? (yes/no): ");
						String accomChoice = input.nextLine().trim();

						if (accomChoice.equalsIgnoreCase("yes")) {
							System.out.println("\nSelect accommodation type:");
							System.out.println("1. Hotel");
							System.out.println("2. Hostel");
							System.out.print("Your choice: ");
							int accomType = input.nextInt();
							input.nextLine();

							System.out.println("\nAvailable options:");
							boolean anyAccomFound = false;

							for (int i = 0; i < service.getAccomCount(); i++) {
								Accommodation a = service.getAccoms()[i];
								if (accomType == 1 && a instanceof Hotel) {
									System.out.println(a);
									System.out.println();
									anyAccomFound = true;
								} else if (accomType == 2 && a instanceof Hostel) {
									System.out.println(a);
									System.out.println();
									anyAccomFound = true;
								}
							}

							if (!anyAccomFound) {
								System.out.println("No accommodations of that type are available.");
							} else {
								System.out.print("Enter the accommodation ID to attach: ");
								String accomId = input.nextLine().trim();
								boolean accomFound = false;
								for (int i = 0; i < service.getAccomCount(); i++) {
									if (service.getAccoms(i).getAccommodationId().equalsIgnoreCase(accomId)) {
										service.getTrips()[index].setAccommodation(service.getAccoms(i));
										System.out.println("Accommodation updated successfully.");
										accomFound = true;
										break;
									}
								}
								if (!accomFound) {
									System.out.println("Accommodation ID not found. Accommodation was not changed.");
								}
							}
						}
					} else {
						System.out.println("No accommodations available to attach.");
					}

					System.out.println("Trip editted successfully:");
					System.out.println(service.getTrip(index));

				}

				break;

			// Cancel a trip by ID
			case 3:
				if (service.getTripCount() == 0) {
					System.out.println("There are no trips to delete.");
					break;
				}

				// Search for trip by ID
				System.out.print("Enter the trip ID to delete: ");
				String tripDelete = input.nextLine().trim();
				int indexTripDelete = -1;
				Trip[] tripsArr = service.getTrips();

				for (int i = 0; i < service.getTripCount(); i++) {
					if (tripsArr[i].getTripId().equalsIgnoreCase(tripDelete)) {
						indexTripDelete = i;
						break;
					}
				}
				if (indexTripDelete == -1) {
					System.out.println("Trip not found");
				} else {

					// Shift elements to the left and null out the last slot
					for (int i = indexTripDelete; i < service.getTripCount() - 1; i++) {
						tripsArr[i] = tripsArr[i + 1];
					}

					tripsArr[service.getTripCount() - 1] = null;
					service.setTripCount(service.getTripCount() - 1);
					System.out.println("Trip deleted Successfully.");
				}

				break;

			// List all trips
			case 4:
				if (service.getTripCount() == 0) {
					System.out.println("There are no trips available.");
					break;
				}
				for (int i = 0; i < service.getTripCount(); i++) {
					System.out.println(service.getTrip(i));
					System.out.println();
				}

				break;

			// List all the trips of a specific client
			case 5:

				System.out.print("Enter the client's ID: ");
				String searchId = input.nextLine().trim();
				boolean found = false;
				for (int i = 0; i < service.getTripCount(); i++) {
					if (service.getTrip(i).getTravelingClient().getClientId().equalsIgnoreCase(searchId)) {
						System.out.println(service.getTrip(i));
						System.out.println();
						found = true;
					}
				}
				if (found == false) {
					System.out.println("No trips found for that client.");
					break;
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

	// Handles all operations related to transportations (add, remove, list)
	public static void transportManagement() {
		boolean menuReturn = false;
		while (!menuReturn) {
			System.out.println("--Transportation Management--");
			System.out.println("1. Add a transportation option");
			System.out.println("2. Remove a transportation option");
			System.out.println("3. List all transportation options by type (Bus, flight or train)");
			System.out.println("0. Go back to main menu");
			System.out.print("Your choice: ");

			int tMenuChoice = input.nextInt();
			input.nextLine();

			switch (tMenuChoice) {

			// Add a new transportation option (Flight, Train, or Bus)
			case 1:

				// Ask user which type to add
				System.out.println();
				System.out.println("1. Flight");
				System.out.println("2. Train");
				System.out.println("3. Bus");
				System.out.print("Your choice: ");
				int type = input.nextInt();
				input.nextLine();

				// Collect the shared attributes
				System.out.print("Company name: ");
				String comp = input.nextLine();

				System.out.print("Departure city: ");
				String dep = input.nextLine();

				System.out.print("Arrival city: ");
				String arrival = input.nextLine();
				Transportation transpo = null;

				try {
					// Collect type-specific attributes and create the object
					if (type == 1) {

						System.out.print("Airline name: ");
						String airline = input.nextLine();

						System.out.print("Luggage allowance (in kg): ");
						double lugg = input.nextDouble();
						input.nextLine();

						System.out.print("Price: ");
						double price = input.nextDouble();
						input.nextLine();

						transpo = new Flight(comp, dep, arrival, airline, lugg, price);

					} else if (type == 2) {

						System.out.print("Train type: ");
						String tt = input.nextLine();

						System.out.print("Seat class: ");
						String sClass = input.nextLine();

						System.out.print("Train fare: ");
						double tFare = input.nextDouble();
						input.nextLine();
						transpo = new Train(comp, dep, arrival, tt, sClass, tFare);

					} else if (type == 3) {
						System.out.print("Bus company: ");
						String bc = input.nextLine();

						System.out.print("Number of stops: ");
						int stops = input.nextInt();
						input.nextLine();

						System.out.print("Bus fare: ");
						double bFare = input.nextDouble();
						input.nextLine();

						transpo = new Bus(comp, dep, arrival, bc, stops, bFare);
					} else {
						System.out.println("Invalid option");
						break;
					}

					// Store the new transportation option
					service.addTransportation(transpo);
					System.out.println("Transportation option successfully added.");
					System.out.println();
				} catch (InvalidTransportDataException e) {
					System.out.println("Invalid transport data: " + e.getMessage());
				}

				break;

			// Remove a transportation option by ID
			case 2:
				if (service.getTransCount() == 0) {
					System.out.println("No transportations. add one first.");
					break;
				}
				System.out.print("Enter the transportation ID to remove: ");
				String trId = input.nextLine();
				int index = -1;
				for (int i = 0; i < service.getTransCount(); i++) {
					if (service.getTransports(i).getTransportId().equalsIgnoreCase(trId)) {
						index = i;
						break;

					}
				}

				if (index == -1) {
					System.out.println("Not found.\n");
					break;
				}
				// Shift elements left and null out the last slot
				else {
					for (int i = index; i < service.getTransCount() - 1; i++) {
						service.getTransports()[i] = service.getTransports()[i + 1];

					}

					service.getTransports()[service.getTransCount() - 1] = null;
					service.setTransCount(service.getTransCount() - 1);
					System.out.println("Deletion complete.");
				}

				break;

			// List all transportation options by type
			case 3:

				if (service.getTransCount() == 0) {
					System.out.println("No transportation options available.");
					break;
				}
				System.out.println("\n1. Flight");
				System.out.println("2. Train");
				System.out.println("3. Bus");
				System.out.print("Your choice: ");

				int typeChoice = input.nextInt();
				boolean found = false;
				for (int i = 0; i < service.getTransCount(); i++) {
					Transportation t = service.getTransports(i);

					if (typeChoice == 1 && t instanceof Flight) {
						System.out.println(t);
						System.out.println();
						found = true;
					} else if (typeChoice == 2 && t instanceof Train) {
						System.out.println(t);
						found = true;
						System.out.println();
					}

					else if (typeChoice == 3 && t instanceof Bus) {
						System.out.println(t);
						System.out.println();
						found = true;
					}
				}
				if (!found) {
					System.out.println("None found.");
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

	// Handles all operations related to accommodations (add, remove, list)
	public static void accomManagement() {
		boolean menuReturn = false;
		while (!menuReturn) {
			System.out.println("--Accommodation Management--");
			System.out.println("1. Add an accommodation");
			System.out.println("2. Remove an accommodation");
			System.out.println("3. List all accommodations by type (Hotel or hostel)");
			System.out.println("0. Go back to main menu");
			System.out.print("Your choice: ");

			int acMenuChoice = input.nextInt();
			input.nextLine();

			switch (acMenuChoice) {

			// Add a new accommodation (Hotel or Hostel)
			case 1:

				if (service.getAccomCount() >= 50) {
					System.out.println("Storage is full.");
					break;
				}

				// Ask user which type to add
				Accommodation acc = null;
				System.out.println();
				System.out.println("Choose the type of accommodation to add: ");
				System.out.println("1. Hotel");
				System.out.println("2. Hostel");
				System.out.print("Your choice: ");
				int type = input.nextInt();
				input.nextLine();

				// Collect shared attributes of the accommodation
				System.out.print("Name of the place: ");
				String hName = input.nextLine();

				System.out.print("Location: ");
				String city = input.nextLine();

				System.out.print("Price per night: ");
				double pNight = input.nextDouble();
				input.nextLine();

				try {
					// Collect type-specific attributes and create the object
					if (type == 1) {
						System.out.println();
						System.out.print("How many stars: ");
						int stars = input.nextInt();
						input.nextLine();

						acc = new Hotel(hName, city, pNight, stars);
					}

					else if (type == 2) {
						System.out.println();
						System.out.print("How many shared beds per room: ");
						int beds = input.nextInt();
						input.nextLine();

						acc = new Hostel(hName, city, pNight, beds);
					} else {
						System.out.println("invalid option.");
						break;
					}

					// Store the new accommodation
					service.addAccommodation(acc);
					System.out.println("Accommodation added.");
				} catch (InvalidAccommodationDataException e) {
					System.out.println("Invalid accommodation data: " + e.getMessage());
				}
				break;

			// Remove an accommodation by ID
			case 2:
				if (service.getAccomCount() == 0) {
					System.out.println("No accommodations available.");
					break;
				}

				// Search for accommodation by ID
				System.out.print("Enter accommodation ID to delete: ");
				String accDelId = input.nextLine();
				;

				int index = -1;
				Accommodation[] accArr = service.getAccoms();
				for (int i = 0; i < service.getAccomCount(); i++) {
					if (accArr[i].getAccommodationId().equalsIgnoreCase(accDelId)) {
						index = i;
						break;
					}
				}
				if (index == -1) {
					System.out.println("Not found.");
					break;
				}
				// Shift elements left and null out the last slot
				else {
					for (int i = index; i < service.getAccomCount() - 1; i++) {
						accArr[i] = accArr[i + 1];
					}
					accArr[service.getAccomCount() - 1] = null;
					service.setAccomCount(service.getAccomCount() - 1);
					System.out.println("Deletion complete.");
				}

				break;
			// List all accommodations by type
			case 3:
				if (service.getAccomCount() == 0) {
					System.out.println("No accommodations available.");
					break;
				}
				System.out.println("\n1. Hotel");
				System.out.println("2. Hostel");
				System.out.print("Your choice: ");

				int typeChoice = input.nextInt();
				input.nextLine();
				boolean found = false;

				for (int i = 0; i < service.getAccomCount(); i++) {
					Accommodation a = service.getAccoms(i);
					if (typeChoice == 1 && a instanceof Hotel) {
						System.out.println(a);
						found = true;

					} else if (typeChoice == 2 && a instanceof Hostel) {
						System.out.println(a);
						found = true;

					}

				}

				if (!found) {
					System.out.println("No accommodations of that type were found.");
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
// ------------------------------------------------------------------------------------------------------------------------------------

	// Finds the most expensive trip and displays it
	public static void showMostExpensiveTrip() {
		Trip mostExpensive = service.getTrip(0);
		for (int i = 1; i < service.getTripCount(); i++) {
			if (service.getTrip(i).calculateTotalCost() > mostExpensive.calculateTotalCost())
				mostExpensive = service.getTrip(i);
		}
		System.out.println("The most expensive trip is: \n" + mostExpensive);
	}

	// creates a deep copy of the transportation array using copy constructors
	// Changes to the copied array will not affect the original
	public static Transportation[] copyTransportationArray() {

		int transCount = service.getTransCount();
		Transportation[] original = service.getTransports();
		Transportation[] copy = new Transportation[transCount];

		try {
			for (int i = 0; i < transCount; i++) {
				if (original[i] instanceof Flight)
					copy[i] = new Flight((Flight) original[i]);
				else if (original[i] instanceof Train)
					copy[i] = new Train((Train) original[i]);
				else if (original[i] instanceof Bus)
					copy[i] = new Bus((Bus) original[i]);
			}
		} catch (Exception e) {
			System.out.println("Error copying transportation: " + e.getMessage());
		}
		return copy;
	}

	// creates a deep copy of the accommodation array using copy constructors
	// Changes to the copied array will not affect the original
	public static Accommodation[] copyAccommodationArray() {
		int count = service.getAccomCount();
		Accommodation[] copy = new Accommodation[count];
		Accommodation[] original = service.getAccoms();

		try {
			for (int i = 0; i < count; i++) {
				if (original[i] instanceof Hotel) {
					copy[i] = new Hotel((Hotel) original[i]);
				} else if (original[i] instanceof Hostel) {
					copy[i] = new Hostel((Hostel) original[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("Error copying accommodation: " + e.getMessage());
		}
		return copy;
	}

	// Attaches an accommodation to an existing trip
	public static Trip attachAccommodation(Trip trip) {
		if (service.getAccomCount() == 0)
			return trip;
		System.out.print("\nWould you like to attach an accommodation? (yes/no): ");
		if (!input.nextLine().trim().equalsIgnoreCase("yes"))
			return trip;

		System.out.println("\nSelect accommodation type:");
		System.out.println("1. Hotel\n2. Hostel");
		System.out.print("Your choice: ");
		int aType = input.nextInt();
		input.nextLine();

		System.out.println("\nAvailable options:");
		boolean anyFound = false;
		for (int i = 0; i < service.getAccomCount(); i++) {
			Accommodation a = service.getAccoms()[i];
			if ((aType == 1 && a instanceof Hotel) || (aType == 2 && a instanceof Hostel)) {
				System.out.println(a);
				System.out.println();
				anyFound = true;
			}
		}
		if (!anyFound) {
			System.out.println("No accommodations of that type are available.");
			return trip;
		}
		System.out.print("Enter the accommodation ID to attach: ");
		String aId = input.nextLine().trim();
		for (int i = 0; i < service.getAccomCount(); i++) {
			if (service.getAccoms()[i].getAccommodationId().equalsIgnoreCase(aId)) {
				trip.setAccommodation(service.getAccoms()[i]);
				System.out.println("Accommodation attached successfully.");
				return trip;
			}
		}
		System.out.println("Accommodation ID not found. No accommodation attached.");
		return trip;
	}

	// Attaches a transportation to an existing trip
	public static Trip attachTransportation(Trip trip) {
		if (service.getTransCount() == 0)
			return trip;
		System.out.print("\nWould you like to attach a transportation option? (yes/no): ");
		if (!input.nextLine().trim().equalsIgnoreCase("yes"))
			return trip;

		System.out.println("\nSelect transportation type:");
		System.out.println("1. Flight\n2. Train\n3. Bus");
		System.out.print("Your choice: ");
		int tType = input.nextInt();
		input.nextLine();

		System.out.println("\nAvailable options:");
		boolean anyFound = false;
		for (int i = 0; i < service.getTransCount(); i++) {
			Transportation t = service.getTransports()[i];
			if ((tType == 1 && t instanceof Flight) || (tType == 2 && t instanceof Train)
					|| (tType == 3 && t instanceof Bus)) {
				System.out.println(t);
				System.out.println();
				anyFound = true;
			}
		}
		if (!anyFound) {
			System.out.println("No transportation options of that type are available.");
			return trip;
		}
		System.out.print("Enter the transportation ID to attach: ");
		String tId = input.nextLine().trim();
		for (int i = 0; i < service.getTransCount(); i++) {
			if (service.getTransports()[i].getTransportId().equalsIgnoreCase(tId)) {
				trip.setTransportation(service.getTransports()[i]);
				System.out.println("Transportation attached successfully.");
				return trip;
			}
		}
		System.out.println("Transportation ID not found. No transportation attached.");
		return trip;
	}
}
