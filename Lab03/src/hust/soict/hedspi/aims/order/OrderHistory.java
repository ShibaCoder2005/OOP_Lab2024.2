package hust.soict.hedspi.aims.order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Order added to history.");
    }

    public void printHistory() {
        if (orders.isEmpty()) {
            System.out.println("No orders in history.");
            return;
        }
        System.out.println("\nOrder History:");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("Order #" + (i + 1) + " (Placed on " + orders.get(i).getTransactionTime() + "):");
            orders.get(i).print();
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("order_history.dat"))) {
            oos.writeObject(this);
            System.out.println("Order history saved to order_history.dat.");
        } catch (IOException e) {
            System.out.println("Error saving order history: " + e.getMessage());
        }
    }

    public static OrderHistory loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("order_history.dat"))) {
            return (OrderHistory) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous order history found. Starting with an empty history.");
            return new OrderHistory();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading order history: " + e.getMessage());
            return new OrderHistory();
        }
    }
}