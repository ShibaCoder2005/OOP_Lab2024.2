package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable {

	private String label;
	private int timeInSeconds;

	public Track(String label, int timeInSeconds) {
		this.label = label;
		this.timeInSeconds = timeInSeconds;
	}

	public String getTitle() {
		return label;
	}

	public int getLength() {
		return timeInSeconds;
	}

	@Override
	public String play() throws PlayerException {
		if (timeInSeconds <= 0) {
			throw new PlayerException("❌ Error: invalid track length. Playback aborted.");
		}

		System.out.println("🎧 Playing track: \"" + label + "\"");
		System.out.println("⏳ Length: " + timeInSeconds + " seconds");

		return "Playback complete.";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Track other)) return false;
		return this.timeInSeconds == other.timeInSeconds && label.equals(other.label);
	}
}
