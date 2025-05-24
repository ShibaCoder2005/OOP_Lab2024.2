package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {
	@Override
	public int compare(Media a, Media b) {
		String titleA = a.getTitle();
		String titleB = b.getTitle();

		int result = titleA.compareTo(titleB);
		if (result != 0) {
			return result;
		}

		float priceA = a.getCost();
		float priceB = b.getCost();

		return Float.compare(priceB, priceA); // cost descending
	}
}
