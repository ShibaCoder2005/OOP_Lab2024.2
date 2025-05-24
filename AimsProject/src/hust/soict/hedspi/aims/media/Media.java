package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media implements Comparable<Media> {

	public static final Comparator<Media> SORT_BY_TITLE_THEN_PRICE = new MediaComparatorByTitleCost();
	public static final Comparator<Media> SORT_BY_PRICE_THEN_TITLE = new MediaComparatorByCostTitle();

	private static int mediaCounter = 0;

	private final int mediaId;
	private String name;
	private String genre;
	private float price;

	public Media(String name) {
		this(name, "", 0f);
	}

	public Media(String name, String genre) {
		this(name, genre, 0f);
	}

	public Media(String name, String genre, float price) {
		this.mediaId = ++mediaCounter;
		this.name = name;
		this.genre = genre;
		this.price = price;
	}

	public int getId() {
		return mediaId;
	}

	public String getTitle() {
		return name;
	}

	public void setTitle(String newName) {
		this.name = newName;
	}

	public String getCategory() {
		return genre;
	}

	public float getCost() {
		return price;
	}

	public boolean isMatch(String keyword) {
		return name != null && name.toLowerCase().contains(keyword.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Media other)) return false;
		return name != null && name.equals(other.name);
	}

	@Override
	public int compareTo(Media other) {
		int nameComparison = name.compareTo(other.name);
		return nameComparison != 0 ? nameComparison : Float.compare(price, other.price);
	}

	@Override
	public String toString() {
		return String.format("[Media #%d] \"%s\" | Genre: %s | Price: %.2f $",
				mediaId, name, genre, price);
	}
}
