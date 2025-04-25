package hust.soict.hedspi.aims.media;

import java.io.Serializable;

public class CompactDisc extends Media implements Serializable, Playable {
    private static final long serialVersionUID = 1L;
    private String artist;

    public CompactDisc(String title, String category, String artist, float cost) {
        super(title, category, cost);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public String toString() {
        return "CD - " + getTitle() + " - " + getCategory() + " - " + artist + ": " + getCost() + " $";
    }
    
    public void play() {
        System.out.println("Playing CD: " + getTitle());
        System.out.println("Artist: " + artist);
    }
}