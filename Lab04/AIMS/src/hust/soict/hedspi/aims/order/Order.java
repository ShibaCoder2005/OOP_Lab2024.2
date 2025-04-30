package hust.soict.hedspi.aims.order;

import hust.soict.hedspi.aims.media.Media;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
    public static final int MAX_NUMBERS_ORDERED = 10;
    public static final int MAX_LIMITED_ORDERS = 5;
    private static int nbOrders = 0;
    private Media[] itemsOrdered = new Media[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;
    private LocalDateTime transactionTime;

    public Order() {
    	this.transactionTime = LocalDateTime.now();
        if (nbOrders >= MAX_LIMITED_ORDERS) {
            throw new IllegalStateException("Maximum number of orders reached!");
        }
        nbOrders++;
    }
    
    public String getTransactionTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return transactionTime.format(formatter);
    }

    public void addDigitalVideoDisc(Media media) {
        if (qtyOrdered >= MAX_NUMBERS_ORDERED) {
            System.out.println("The order is full! Cannot add more items.");
            return;
        }
        itemsOrdered[qtyOrdered++] = media;
        System.out.println("The item \"" + media.getTitle() + "\" has been added to the order.");
    }

    public void removeDigitalVideoDisc(Media media) {
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].getTitle().equals(media.getTitle())) {
                found = true;
                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                itemsOrdered[qtyOrdered - 1] = null;
                qtyOrdered--;
                System.out.println("The item \"" + media.getTitle() + "\" has been removed from the order.");
                break;
            }
        }
        if (!found) {
            System.out.println("The item \"" + media.getTitle() + "\" is not in the order.");
        }
    }

    public float totalCost() {
        float total = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            total += itemsOrdered[i].getCost();
        }
        return total;
    }

    public void print() {
        System.out.println("Order Receipt:");
        if (qtyOrdered == 0) {
            System.out.println("The order is empty.");
            return;
        }
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.println((i + 1) + ". " + itemsOrdered[i].toString());
        }
        System.out.println("Total cost: " + totalCost() + " $");
    }
}