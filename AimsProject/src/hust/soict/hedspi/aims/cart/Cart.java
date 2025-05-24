package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.naming.LimitExceededException;
import java.util.Collections;

public class Cart {
	public static final int MAX_ITEMS = 20;

	private int totalItems = 0;
	private final ObservableList<Media> mediaList = FXCollections.observableArrayList();

	public String addItem(Media item) throws LimitExceededException {
		if (mediaList.size() >= MAX_ITEMS) {
			throw new LimitExceededException("Maximum cart capacity reached.");
		}
		if (mediaList.contains(item)) {
			return item.getTitle() + " is already in your cart.";
		}
		mediaList.add(item);
		totalItems++;
		return item.getTitle() + " has been added to your cart.";
	}

	public void removeItem(Media item) {
		if (mediaList.isEmpty()) {
			System.out.println("Cart is currently empty.");
			return;
		}
		boolean removed = mediaList.remove(item);
		if (removed) {
			totalItems--;
			System.out.println("Removed: " + item.getTitle());
		} else {
			System.out.println("Item not found in cart.");
		}
	}

	public float getTotalCost() {
		float cost = 0;
		for (Media m : mediaList) {
			cost += m.getCost();
		}
		return cost;
	}

	public void showCart() {
		System.out.println("============= Your Cart =============");
		System.out.println("Items in Cart:");
		for (Media m : mediaList) {
			System.out.println("- " + m.toString());
		}
		System.out.println("Total Items: " + totalItems);
		System.out.println("Total Cost: " + getTotalCost() + " $");
		System.out.println("=====================================");
	}

	public void findItemById(int id) {
		for (Media item : mediaList) {
			if (item.getId() == id) {
				System.out.println("Found: " + item);
				return;
			}
		}
		System.out.println("No item found with ID = " + id);
	}

	public void findItemByTitle(String title) {
		boolean found = false;
		for (Media item : mediaList) {
			if (item.isMatch(title)) {
				System.out.println("Found: " + item);
				found = true;
			}
		}
		if (!found) {
			System.out.println("No match found for title: " + title);
		}
	}

	public void sortByTitleThenCost() {
		Collections.sort(mediaList, Media.SORT_BY_TITLE_THEN_PRICE);
		for (Media m : mediaList) {
			System.out.println(m);
		}
	}

	public void sortByCostThenTitle() {
		Collections.sort(mediaList, Media.SORT_BY_PRICE_THEN_TITLE);
		for (Media m : mediaList) {
			System.out.println(m);
		}
	}

	public Media findExactTitle(String title) {
		for (int i = 0; i < mediaList.size(); i++) {
			if (mediaList.get(i).getTitle().equals(title)) {
				return mediaList.get(i);
			}
		}
		return null;
	}

	public void clearCart() {
		if (!mediaList.isEmpty()) {
			mediaList.clear();
			totalItems = 0;
			System.out.println("Cart has been cleared. Order placed.");
		} else {
			System.out.println("Cart is already empty.");
		}
	}

	public ObservableList<Media> getCartItems() {
		return mediaList;
	}
}
