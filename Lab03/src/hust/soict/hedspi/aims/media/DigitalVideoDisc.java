package hust.soict.hedspi.aims.media;

import java.io.Serializable;

public class DigitalVideoDisc extends Media implements Serializable, Playable {
    private static final long serialVersionUID = 1L;
    private String director;
    private int length;

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
    }

    public DigitalVideoDisc(String title) {
        super(title, "", 0.0f);
        this.director = "";
        this.length = 0;
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
        this.director = "";
        this.length = 0;
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(title, category, cost);
        this.director = director;
        this.length = 0;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void play() {
        System.out.println("Playing DVD: " + getTitle());
        System.out.println("Director: " + director);
        System.out.println("Length: " + length + " minutes");
    }

    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + director + " - " + length + " minutes - " + getCost() + "$";
    }
}