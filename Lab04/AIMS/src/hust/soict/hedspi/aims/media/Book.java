package hust.soict.hedspi.aims.media;

import java.io.Serializable;

public class Book extends Media implements Serializable {
    private static final long serialVersionUID = 1L;

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

    public String toString() {
        return "Book - " + getTitle() + " - " + getCategory() + ": " + getCost() + " $";
    }
}