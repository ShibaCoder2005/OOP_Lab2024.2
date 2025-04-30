package hust.soict.hedspi.aims.media;

import java.io.Serializable;

public abstract class Media implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String category;
    private float cost;
    private static int nextId = 1;

    public Media(String title, String category, float cost) {
        this.id = nextId++;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }

    public abstract String toString();

    public boolean isMatch(String title) {
        return this.title.toLowerCase().contains(title.toLowerCase());
    }

    public boolean matchesTitle(String title) {
        return this.title.equalsIgnoreCase(title);
    }
}