package hust.soict.hedspi.test.cart;

import javax.naming.LimitExceededException;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class CartTest {
	public static void main(String[] args) throws LimitExceededException {
		Cart cart = new Cart();

		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
		cart.addItem(dvd1);

		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
		cart.addItem(dvd2);

		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
		cart.addItem(dvd3);

		cart.showCart();

		cart.findItemById(87);

		cart.findItemById(2);

		cart.findItemByTitle("aLaDIn");

		cart.findItemByTitle("stars");
	}
}
