
// Assignment 3
// Written by: Darwinsh Saint-Jean (40341644)
/*
 * RecentList<T> is a generic fixed-size "recency tracker" backed by a LinkedList.
 * It keeps up to 10 of the most recently added items, always inserting new entries
 * at the front and dropping the oldest from the back when full.
 * Used in SmartTravelService to track recently accessed/created Trips.
 */

package service;

import java.util.LinkedList;

public class RecentList<T> {

	// Internal storage
	private LinkedList<T> list = new LinkedList<>();

	// Maximum number of items kept
	private final int MAX_SIZE = 10;

	// Adds item to the front. Removes the oldest if over capacity.
	public void addRecent(T item) {
		// Remove the oldest entry if we are at capacity
		if (list.size() >= MAX_SIZE) {
			list.removeLast();
		}
		list.addFirst(item);
	}

	// Prints up to maxToShow items, most recent first.
	public void printRecent(int maxToShow) {
		if (list.isEmpty()) {
			System.out.println("  (no recent items)");
			return;
		}

		int limit = Math.min(maxToShow, list.size());
		System.out.println("Recent items (most recent first):");
		for (int i = 0; i < limit; i++) {
			System.out.println("  " + (i + 1) + ". " + list.get(i));
		}
	}

	// Returns the number of items in the list.
	public int size() {
		return list.size();
	}

	// Returns true if the list is empty.
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
