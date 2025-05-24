package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
	@Override
	public int compare(Media a, Media b) {
		float costA = a.getCost();
		float costB = b.getCost();

		int costOrder = Float.compare(costB, costA); // sort by cost descending
		if (costOrder != 0) {
			return costOrder;
		}

		return a.getTitle().compareTo(b.getTitle()); // then by title ascending
	}
}
