package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {

	private final List<String> writerList = new ArrayList<>();

	public Book(String name) {
		super(name);
	}

	public Book(String name, String genre) {
		super(name, genre);
	}

	public Book(String name, String genre, float price) {
		super(name, genre, price);
	}

	public void addWriter(String name) {
		if (!writerList.contains(name)) {
			writerList.add(name);
			System.out.println("Added writer: \"" + name + "\" to \"" + getTitle() + "\".");
		} else {
			System.out.println("Writer \"" + name + "\" is already listed.");
		}
	}

	public void removeWriter(String name) {
		if (writerList.contains(name)) {
			writerList.remove(name);
			System.out.println("Removed writer: \"" + name + "\" from \"" + getTitle() + "\".");
		} else {
			System.out.println("Writer \"" + name + "\" not found.");
		}
	}

	public List<String> getWriters() {
		return writerList;
	}

	@Override
	public String toString() {
		return String.format("#%d - BOOK | Title: %s | Genre: %s | Price: %.2f$",
				getId(), getTitle(), getCategory(), getCost());
	}
}
