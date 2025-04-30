package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.*;
import java.util.Arrays;
import java.util.Comparator;
import java.io.*;

public class Store implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int MAX_ITEMS_IN_STORE = 100;
    private Media[] itemsInStore = new Media[MAX_ITEMS_IN_STORE];
    private int qtyInStore = 0;

    public void addDigitalVideoDisc(Media media) {
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i].matchesTitle(media.getTitle())) {
                System.out.println("The item \"" + media.getTitle() + "\" already exists in the store!");
                return;
            }
        }
        if (qtyInStore >= MAX_ITEMS_IN_STORE) {
            System.out.println("The store is full! Cannot add more items.");
            return;
        }
        itemsInStore[qtyInStore++] = media;
        System.out.println("The item \"" + media.getTitle() + "\" has been added to the store.");
    }

    public void removeDigitalVideoDisc(Media media) {
        boolean found = false;
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i].matchesTitle(media.getTitle())) {
                found = true;
                for (int j = i; j < qtyInStore - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                itemsInStore[qtyInStore - 1] = null;
                qtyInStore--;
                System.out.println("The item \"" + media.getTitle() + "\" has been removed from the store.");
                break;
            }
        }
        if (!found) {
            System.out.println("The item \"" + media.getTitle() + "\" is not in the store.");
        }
    }

    public void print() {
        System.out.println("\nItems in Store:");
        if (qtyInStore == 0) {
            System.out.println("The store is empty.");
            return;
        }
        System.out.printf("%-5s %-25s %-15s %-15s %-10s%n", "ID", "Title", "Category", "Type", "Cost");
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < qtyInStore; i++) {
            Media media = itemsInStore[i];
            String type = media instanceof DigitalVideoDisc ? "DVD" : media instanceof CompactDisc ? "CD" : "Book";
            System.out.printf("%-5d %-25s %-15s %-15s %-10.2f%n", 
                media.getId(), media.getTitle(), media.getCategory(), type, media.getCost());
        }
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i].isMatch(title)) {
                System.out.println("Found: " + itemsInStore[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No item found with title: " + title);
        }
    }

    public void searchById(int id) {
        sortById();
        int index = Arrays.binarySearch(itemsInStore, 0, qtyInStore, new Media("", "", 0) {
            public int getId() {
                return id;
            }
            public String toString() {
                return "";
            }
        }, Comparator.comparingInt(Media::getId));

        if (index >= 0) {
            System.out.println("Found: " + itemsInStore[index].toString());
        } else {
            System.out.println("No item found with ID: " + id);
        }
    }

    public void sortByTitle() {
        Arrays.sort(itemsInStore, 0, qtyInStore, Comparator.comparing(Media::getTitle));
        System.out.println("Items sorted by title.");
    }

    public void sortByCost() {
        Arrays.sort(itemsInStore, 0, qtyInStore, Comparator.comparing(Media::getCost));
        System.out.println("Items sorted by cost.");
    }

    public void sortById() {
        Arrays.sort(itemsInStore, 0, qtyInStore, Comparator.comparingInt(Media::getId));
        System.out.println("Items sorted by ID.");
    }

    public int getQtyInStore() {
        return qtyInStore;
    }

    public Media[] getItemsInStore() {
        return itemsInStore;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("store.dat"))) {
            oos.writeObject(this);
            System.out.println("Store data saved to store.dat.");
        } catch (IOException e) {
            System.out.println("Error saving store data: " + e.getMessage());
        }
    }

    public static Store loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("store.dat"))) {
            return (Store) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous store data found. Starting with an empty store.");
            return new Store();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading store data: " + e.getMessage());
            return new Store();
        }
    }

    public void filterByCategory(String category) {
        System.out.println("Items in Store (Category: " + category + "):");
        boolean found = false;
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i].getCategory().equalsIgnoreCase(category)) {
                System.out.println((i + 1) + ". " + itemsInStore[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found in category: " + category);
        }
    }
    
    public void playMedia(String title) {
        boolean found = false;
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i].matchesTitle(title)) {
                if (itemsInStore[i] instanceof Playable) {
                    ((Playable) itemsInStore[i]).play();
                    found = true;
                } else {
                    System.out.println("The item \"" + title + "\" is not playable.");
                    return;
                }
                break;
            }
        }
        if (!found) {
            System.out.println("No item found with title: " + title);
        }
    }
    
    public void searchByCostRange(float minCost, float maxCost) {
        if (minCost > maxCost) {
            System.out.println("Invalid range: Minimum cost must be less than or equal to maximum cost.");
            return;
        }
        System.out.println("\nItems in Store (Cost between " + minCost + " and " + maxCost + "):");
        boolean found = false;
        for (int i = 0; i < qtyInStore; i++) {
            float cost = itemsInStore[i].getCost();
            if (cost >= minCost && cost <= maxCost) {
                System.out.println((i + 1) + ". " + itemsInStore[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found in the cost range " + minCost + " to " + maxCost + ".");
        }
    }
    
    public void printStatisticsByCategory() {
        if (qtyInStore == 0) {
            System.out.println("The store is empty. No statistics to display.");
            return;
        }
        System.out.println("\nStatistics by Category in Store:");
        java.util.HashMap<String, Integer> categoryCount = new java.util.HashMap<>();
        for (int i = 0; i < qtyInStore; i++) {
            String category = itemsInStore[i].getCategory();
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
        }
        System.out.printf("%-20s %-10s%n", "Category", "Count");
        System.out.println("--------------------------------");
        for (java.util.Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            System.out.printf("%-20s %-10d%n", entry.getKey(), entry.getValue());
        }
    }
}