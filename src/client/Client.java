// -----------------------------------------------------------------------
// Assignment 1
// Written by: Darwinsh Saint-Jean(40341644) and Nassim Saidi(40345885)
// -----------------------------------------------------------------------

package client;

import exceptions.InvalidClientDataException;

//This class represents a client of the travel agency.
//It stores personal information about the client such as their first name,
//last name, and email address.
//Each client is automatically assigned a unique ID using a static counter,
//ensuring every client can be individually identified within the system.
public class Client {

	// Static counter to generate unique client IDs starting from 1001
	private static int numId = 1001;
	private String clientId;
	private String firstName;
	private String lastName;
	private String emailAddress;

	// Default constructor - initializes all fields to empty strings
	// and assigns a unique client ID using the static counter
	public Client() {
		this.firstName = "Unknown";
	    this.lastName = "Unknown";
	    this.emailAddress = "unknown@default.com";
	    clientIdGenerator();
	}

	// Parameterized constructor - initializes fields with provided values
	// and assigns a unique client ID using the static counter
	public Client(String firstName, String lastName, String emailAddress) throws InvalidClientDataException {

		// Validate First Name
		if (firstName == null || firstName.trim().isEmpty() || firstName.length() > 50) {
			throw new InvalidClientDataException("First name must be non-empty and 50 characters or less.");
		}

		// Validate Last Name
		if (lastName == null || lastName.trim().isEmpty() || lastName.length() > 50) {
			throw new InvalidClientDataException("Last name must be non-empty and 50 characters or less.");
		}

		// Validate Email
		if (emailAddress == null || emailAddress.length() > 100 || !emailAddress.contains("@") || !emailAddress.contains(".") || emailAddress.contains(" ")) {
			throw new InvalidClientDataException("Email must contain '@' and '.', have no spaces, and be <= 100 chars.");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		clientIdGenerator();
	}

	// Copy constructor - copies all fields from another Client object
	// and assigns a new unique client ID using the static counter
	public Client(Client other) {
		firstName = other.firstName;
		lastName = other.lastName;
		emailAddress = other.emailAddress;
		clientIdGenerator();
	}

	// Public constructor used by the copy() method and file managers.
	// It manually assigns all fields, including the exact clientId, to create a perfect clone.
	// This deliberately bypasses the static counter so numId does not falsely increment.
	public Client(String clientId, String firstName, String lastName, String emailAddress) {
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	// Returns a deep copy of this Client object.
	// It relies on the private backdoor constructor to ensure the exact ID is retained
	// without accidentally inflating the static numId sequence.
	public Client copy() {
		return new Client(this.clientId, this.firstName, this.lastName, this.emailAddress);
	}

	// Returns a formatted string displaying all relevant Client details
	@Override
	public String toString() {
		return "Client ID: " + clientId + "\n" +
				"First Name: " + firstName + "\n" +
				"Last Name: " + lastName + "\n" +
				"Email: " + emailAddress;
	}

	// Checks equality based on first name, last name, and email address (excludes ID)
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Client other = (Client) obj;

		return (this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName) && this.emailAddress.equals(other.emailAddress));

	}

	// Generates Client ID and increments ID number for next client
	private void clientIdGenerator() {
		clientId = "C" + numId;
		numId++;
	}

	// --- Getters and Setters ---

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmailAdress() {
		return emailAddress;
	}
	public String getClientId() {
		return clientId;
	}
	public void setFirstName(String firstName) throws InvalidClientDataException {
		if (firstName == null || firstName.trim().isEmpty() || firstName.length() > 50) {
			throw new InvalidClientDataException("First name must be non-empty and 50 characters or less.");
		}
		this.firstName = firstName;
	}

	public void setLastName(String lastName) throws InvalidClientDataException {
		if (lastName == null || lastName.trim().isEmpty() || lastName.length() > 50) {
			throw new InvalidClientDataException("Last name must be non-empty and 50 characters or less.");
		}
		this.lastName = lastName;
	}

	public void setEmailAdress(String emailAdress) throws InvalidClientDataException {
		if (emailAdress == null || emailAdress.length() > 100 || !emailAdress.contains("@") || !emailAdress.contains(".") || emailAdress.contains(" ")) {
			throw new InvalidClientDataException("Email must contain '@' and '.', have no spaces, and be <= 100 chars.");
		}
		this.emailAddress = emailAdress;
	}

}