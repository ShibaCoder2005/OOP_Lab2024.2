package hust.soict.hedspi.aims.store;

import java.util.ArrayList;

import hust.soict.hedspi.aims.media.Media;

public class Store {

	private final ArrayList<Media> itemsInStore = new ArrayList<>();

	public ArrayList<Media> getItemsInStore() {
		return itemsInStore;
	}

	private boolean containsMedia(Media media) {
		return itemsInStore.contains(media);
	}

	public void addMedia(Media media) {
		if (!containsMedia(media)) {
			itemsInStore.add(media);
			System.out.println("The \"" + media.getTitle() + "\" has been added to the store.");
		} else {
			System.out.println("The \"" + media.getTitle() + "\" is already in the store.");
		}
	}

	public void removeMedia(Media media) {
		if (containsMedia(media)) {
			itemsInStore.remove(media);
			System.out.println("The \"" + media.getTitle() + "\" has been removed from the store.");
		} else {
			System.out.println("There is no \"" + media.getTitle() + "\" in the store.");
		}
	}

	public void print() {
		if (itemsInStore.isEmpty()) {
			System.out.println("The store is empty!");
		} else {
			System.out.println("************************INVENTORY***********************");
			for (Media media : itemsInStore) {
				System.out.println(media);
			}
			System.out.println("********************************************************");
		}
	}

	public Media search(String title) {
		for (Media media : itemsInStore) {
			if (media.getTitle().equalsIgnoreCase(title)) {
				return media;
			}
		}
		return null;
	}
}
