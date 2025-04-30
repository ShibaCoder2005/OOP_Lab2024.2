package hust.soict.hedspi.test.order;

import hust.soict.hedspi.aims.order.Order;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;

public class OrderTest {
    public static void main(String[] args) {
        try {
            Order order = new Order();

            Media dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
            Media dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
            Media dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);

            System.out.println("Testing print() method (empty order):");
            order.print();

            System.out.println("\nTesting addDigitalVideoDisc() method:");
            order.addDigitalVideoDisc(dvd1);
            order.addDigitalVideoDisc(dvd2);
            order.addDigitalVideoDisc(dvd3);

            System.out.println("\nTesting print() method (after adding DVDs):");
            order.print();

            System.out.println("\nTesting removeDigitalVideoDisc() method:");
            order.removeDigitalVideoDisc(dvd2);
            order.removeDigitalVideoDisc(new DigitalVideoDisc("Harry Potter", "Unknown", 0.0f));

            System.out.println("\nTesting print() method (after removing DVDs):");
            order.print();

            System.out.println("\nTesting maximum number of orders:");
            Order order2 = new Order();
            Order order3 = new Order();
            Order order4 = new Order();
            Order order5 = new Order();
            Order order6 = new Order();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}