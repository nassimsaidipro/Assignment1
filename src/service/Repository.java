package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import exceptions.EntityNotFoundException;
import interfaces.Identifiable;

public class Repository<T extends Identifiable & Comparable<? super T>> {
	private List<T> items = new ArrayList<>();

	// Adds item to the repository
	public void add(T item) {
		items.add(item);
	}

	// Finds item by ID, throws EntityNotFoundException if not found
	public T findById(String id) throws EntityNotFoundException {
		for (T item : items) {
			if (item.getId().equalsIgnoreCase(id)) {
				return item;
			}
		}
		throw new EntityNotFoundException();
	}
	
	// Removes and item by ID
	public void removeById(String id) throws EntityNotFoundException {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId().equalsIgnoreCase(id)) {
				items.remove(i);
				return;
			}
		}
		throw new EntityNotFoundException("Entity with ID " + id + " not found.");
	}

	// Returns all items matching the predicate
	public List<T> filter(Predicate<T> predicate) {
		List<T> result = new ArrayList<>();
		for (T item : items) {
			if (predicate.test(item)) {
				result.add(item);
			}
		}
		return result;
	}

	// Returns a sorted copy using natural order
	public List<T> getSorted() {
		List<T> sorted = new ArrayList<>(items);
		Collections.sort(sorted);
		return sorted;
	}

}
