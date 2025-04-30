package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        Media dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        dvd1.setId(1);
        Media dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        dvd2.setId(2);
        Media dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);
        dvd3.setId(3);

        cart.addDigitalVideoDisc(dvd1);
        cart.addDigitalVideoDisc(dvd2);
        cart.addDigitalVideoDisc(dvd3);

        System.out.println("Testing print() method:");
        cart.print();

        System.out.println("\nTesting searchById() method:");
        cart.searchById(2);
        cart.searchById(4);

        System.out.println("\nTesting searchByTitle() method:");
        cart.searchByTitle("Lion");
        cart.searchByTitle("Harry");
    }
}