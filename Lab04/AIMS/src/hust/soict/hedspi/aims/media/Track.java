package hust.soict.hedspi.aims.media;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Track title cannot be null or empty.");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Track length must be positive.");
        }
        this.title = title;
        this.length = length;
    }

    public Track(String title) {
        this(title, 0);
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Track title cannot be null or empty.");
        }
        this.title = title;
    }

    public void setLength(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Track length must be positive.");
        }
        this.length = length;
    }

    public void play() {
        System.out.println("Playing track: " + title);
        if (length > 0) {
            System.out.println("Track length: " + length + " seconds");
        } else {
            System.out.println("Track length: Not specified");
        }
    }

    public String toString() {
        return "Track - " + title + " - " + (length > 0 ? length + " seconds" : "Length not specified");
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Track)) return false;
        Track other = (Track) obj;
        return this.title.equals(other.title) && this.length == other.length;
    }

    public int hashCode() {
        return 31 * title.hashCode() + length;
    }
}