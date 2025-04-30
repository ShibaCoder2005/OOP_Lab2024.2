package hust.soict.hedspi.aims.media;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Media implements Serializable, Playable {
    private static final long serialVersionUID = 1L;
    private String artist;
    private List<Track> tracks;

    public CompactDisc(String title, String category, String artist, float cost) {
        super(title, category, cost);
        this.artist = artist;
        this.tracks = new ArrayList<>();
    }

    public String getArtist() {
        return artist;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("Track \"" + track.getTitle() + "\" already exists in the CD.");
        } else {
            tracks.add(track);
            System.out.println("Track \"" + track.getTitle() + "\" has been added to the CD.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.remove(track)) {
            System.out.println("Track \"" + track.getTitle() + "\" has been removed from the CD.");
        } else {
            System.out.println("Track \"" + track.getTitle() + "\" not found in the CD.");
        }
    }

    public int getTotalLength() {
        int total = 0;
        for (Track track : tracks) {
            total += track.getLength();
        }
        return total;
    }

    public void play() {
        System.out.println("Playing CD: " + getTitle());
        System.out.println("Artist: " + artist);
        System.out.println("Total length: " + getTotalLength() + " seconds");
        System.out.println("Tracks:");
        for (Track track : tracks) {
            track.play();
        }
    }

    public String toString() {
        return "CD - " + getTitle() + " - " + getCategory() + " - " + artist + " - " + getTotalLength() + " seconds - " + getCost() + "$";
    }
}