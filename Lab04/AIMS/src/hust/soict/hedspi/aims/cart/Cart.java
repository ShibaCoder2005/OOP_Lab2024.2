package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.notification.NotificationManager;
import java.util.Arrays;
import java.util.Comparator;
import java.io.*;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int MAX_NUMBER_ORDERED = 20;
    private Media[] itemsOrdered = new Media[MAX_NUMBER_ORDERED];
    private int qtyOrdered = 0;

    public void addDigitalVideoDisc(Media media, NotificationManager notificationManager) {
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].matchesTitle(media.getTitle())) {
                System.out.println("The item \"" + media.getTitle() + "\" already exists in the cart!");
                notificationManager.addNotification("Failed to add \"" + media.getTitle() + "\" to cart: Item already exists.");
                return;
            }
        }
        if (qtyOrdered + 1 > MAX_NUMBER_ORDERED) {
            System.out.println("The cart is almost full");
            notificationManager.addNotification("Failed to add \"" + media.getTitle() + "\" to cart: Cart is full.");
        } else {
            itemsOrdered[qtyOrdered++] = media;
            System.out.println("The item has been added");
            notificationManager.addNotification("Added \"" + media.getTitle() + "\" to cart.");
        }
    }

    public void removeDigitalVideoDisc(Media media, NotificationManager notificationManager) {
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].matchesTitle(media.getTitle())) {
                found = true;
                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                itemsOrdered[qtyOrdered - 1] = null;
                qtyOrdered--;
                System.out.println("The item has been removed");
                notificationManager.addNotification("Removed \"" + media.getTitle() + "\" from cart.");
                break;
            }
        }
        if (!found) {
            System.out.println("The item is not in the cart");
            notificationManager.addNotification("Failed to remove \"" + media.getTitle() + "\" from cart: Item not found.");
        }
    }

    public float totalCost() {
        float total = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            total += itemsOrdered[i].getCost();
        }
        return total;
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].isMatch(title)) {
                System.out.println("Found: " + itemsOrdered[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No item found with title: " + title);
        }
    }

    public void searchById(int id) {
        sortById();
        int index = Arrays.binarySearch(itemsOrdered, 0, qtyOrdered, new Media("", "", 0) {
            public int getId() {
                return id;
            }
            public String toString() {
                return "";
            }
        }, Comparator.comparingInt(Media::getId));

        if (index >= 0) {
            System.out.println("Found: " + itemsOrdered[index].toString());
        } else {
            System.out.println("No item found with ID: " + id);
        }
    }

    public void sortByTitle() {
        Arrays.sort(itemsOrdered, 0, qtyOrdered, Comparator.comparing(Media::getTitle));
        System.out.println("Cart sorted by title.");
    }

    public void sortByCost() {
        Arrays.sort(itemsOrdered, 0, qtyOrdered, Comparator.comparing(Media::getCost));
        System.out.println("Cart sorted by cost.");
    }

    public void sortById() {
        Arrays.sort(itemsOrdered, 0, qtyOrdered, Comparator.comparingInt(Media::getId));
        System.out.println("Cart sorted by ID.");
    }

    public int getQtyOrdered() {
        return qtyOrdered;
    }

    public Media[] getItemsOrdered() {
        return itemsOrdered;
    }
    
    public static Cart loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cart.dat"))) {
            return (Cart) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous cart data found. Starting with an empty cart.");
            return new Cart();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading cart data: " + e.getMessage());
            return new Cart();
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cart.dat"))) {
            oos.writeObject(this);
            System.out.println("Cart data saved to cart.dat.");
        } catch (IOException e) {
            System.out.println("Error saving cart data: " + e.getMessage());
        }
    }

    public void print() {
        System.out.println("\nOrdered Items:");
        if (qtyOrdered == 0) {
            System.out.println("The cart is empty.");
            return;
        }
        System.out.printf("%-5s %-25s %-15s %-15s %-10s%n", "ID", "Title", "Category", "Type", "Cost");
        System.out.println("------------------------------------------------------------");
        float totalCost = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            Media media = itemsOrdered[i];
            String type = media instanceof DigitalVideoDisc ? "DVD" : media instanceof CompactDisc ? "CD" : "Book";
            System.out.printf("%-5d %-25s %-15s %-15s %-10.2f%n", 
                media.getId(), media.getTitle(), media.getCategory(), type, media.getCost());
            totalCost += media.getCost();
        }
        System.out.println("------------------------------------------------------------");
        System.out.printf("Total cost: %.2f%n", totalCost);
    }

    public void filterByCategory(String category) {
        System.out.println("Items in Cart (Category: " + category + "):");
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].getCategory().equalsIgnoreCase(category)) {
                System.out.println((i + 1) + ". " + itemsOrdered[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found in category: " + category);
        }
    }
    
    public void playMedia(String title) {
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].matchesTitle(title)) {
                if (itemsOrdered[i] instanceof Playable) {
                    ((Playable) itemsOrdered[i]).play();
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
        System.out.println("\nItems in Cart (Cost between " + minCost + " and " + maxCost + "):");
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            float cost = itemsOrdered[i].getCost();
            if (cost >= minCost && cost <= maxCost) {
                System.out.println((i + 1) + ". " + itemsOrdered[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found in the cost range " + minCost + " to " + maxCost + ".");
        }
    }
    
    public void printStatisticsByCategory() {
        if (qtyOrdered == 0) {
            System.out.println("The cart is empty. No statistics to display.");
            return;
        }
        System.out.println("\nStatistics by Category in Cart:");
        java.util.HashMap<String, Integer> categoryCount = new java.util.HashMap<>();
        for (int i = 0; i < qtyOrdered; i++) {
            String category = itemsOrdered[i].getCategory();
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
        }
        System.out.printf("%-20s %-10s%n", "Category", "Count");
        System.out.println("--------------------------------");
        for (java.util.Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            System.out.printf("%-20s %-10d%n", entry.getKey(), entry.getValue());
        }
    }
}