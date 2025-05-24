package hust.soict.hedspi.aims.media;

public class Disc extends Media {

	private String filmDirector;
	private int runtime;

	public String getFilmDirector() {
		return filmDirector;
	}

	public int getRuntime() {
		return runtime;
	}

	public Disc(String name) {
		super(name);
	}

	public Disc(String name, String genre, float price) {
		super(name, genre, price);
	}

	public Disc(String name, String genre, String director, float price) {
		super(name, genre, price);
		this.filmDirector = director;
	}

	public Disc(String name, String genre, String director, int runtime, float price) {
		super(name, genre, price);
		this.filmDirector = director;
		this.runtime = runtime;
	}

	@Override
	public int compareTo(Media other) {
		if (other instanceof Disc thatDisc) {
			int nameCmp = getTitle().compareTo(thatDisc.getTitle());
			if (nameCmp != 0) return nameCmp;

			int durationCmp = Integer.compare(thatDisc.getRuntime(), this.runtime);
			if (durationCmp != 0) return durationCmp;

			return Float.compare(this.getCost(), thatDisc.getCost());
		}
		return super.compareTo(other);
	}
}
