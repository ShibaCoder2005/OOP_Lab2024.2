package hust.soict.hedspi.aims.media;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public Track() {
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public void play() {
        if (this.length <= 0) {
            System.out.println("Track " + this.title + " cannot be played because its length is 0 or negative.");
        } else {
            System.out.println("Playing track: " + this.title);
            System.out.println("Track length: " + this.length);
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;
        Track other = (Track) o;
        return this.title.equals(other.title) && this.length == other.length;
    }
}