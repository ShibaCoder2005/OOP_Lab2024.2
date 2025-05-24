package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable {

	private String performer;
	private final List<Track> trackList = new ArrayList<>();

	public String getPerformer() {
		return performer;
	}

	public List<Track> getTrackList() {
		return trackList;
	}

	public CompactDisc(String name) {
		super(name);
	}

	public CompactDisc(String name, String type, float price) {
		super(name, type, price);
	}

	public CompactDisc(String name, String type, String performer, float price) {
		super(name, type, price);
		this.performer = performer;
	}

	public void appendTrack(Track t) {
		if (!trackList.contains(t)) {
			trackList.add(t);
			System.out.println("Track '" + t.getTitle() + "' appended to \"" + getTitle() + "\".");
		} else {
			System.out.println("Track already exists in this CD.");
		}
	}

	public void discardTrack(Track t) {
		if (trackList.remove(t)) {
			System.out.println("Track '" + t.getTitle() + "' removed from \"" + getTitle() + "\".");
		} else {
			System.out.println("Track not found in CD.");
		}
	}

	public int getTotalLength() {
		int total = 0;
		for (Track t : trackList) {
			total += t.getLength();
		}
		return total;
	}

	@Override
	public String play() throws PlayerException {
		int duration = getTotalLength();
		if (duration <= 0) {
			throw new PlayerException("Invalid CD length: must be positive.");
		}

		System.out.println("Now playing: " + getTitle());
		System.out.println("Duration: " + duration + " seconds");

		for (Track t : trackList) {
			t.play();  // PlayerException propagated if thrown
		}

		return "Playback complete.";
	}

	@Override
	public String toString() {
		return String.format("#%d - COMPACT DISC | Title: %s | Category: %s | Artist: %s | Duration: %ds | Price: %.2f$",
				getId(), getTitle(), getCategory(), getPerformer(), getTotalLength(), getCost());
	}
}
