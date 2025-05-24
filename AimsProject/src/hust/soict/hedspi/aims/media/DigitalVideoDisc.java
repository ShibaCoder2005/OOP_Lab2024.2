package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {

	private String filmDirector;
	private int duration;

	public DigitalVideoDisc(String name) {
		super(name);
	}

	public DigitalVideoDisc(String name, String genre, float price) {
		super(name, genre, price);
	}

	public DigitalVideoDisc(String name, String genre, String director, float price) {
		super(name, genre, price);
		this.filmDirector = director;
	}

	public DigitalVideoDisc(String name, String genre, String director, int duration, float price) {
		super(name, genre, price);
		this.filmDirector = director;
		this.duration = duration;
	}

	public String getFilmDirector() {
		return filmDirector;
	}

	public int getDuration() {
		return duration;
	}

	@Override
	public boolean isMatch(String titleQuery) {
		return getTitle() != null && getTitle().toLowerCase().contains(titleQuery.toLowerCase());
	}

	@Override
	public String play() throws PlayerException {
		if (duration <= 0) {
			throw new PlayerException("Cannot play: DVD duration is non-positive.");
		}

		System.out.println("▶ Now playing: " + getTitle());
		System.out.println("Duration: " + duration + " minutes");

		return "DVD playback finished.";
	}

	@Override
	public String toString() {
		return String.format("#%d - DIGITAL VIDEO DISC | Title: %s | Genre: %s | Director: %s | Duration: %d min | Price: %.2f$",
				getId(), getTitle(), getCategory(), filmDirector, duration, getCost());
	}
}
