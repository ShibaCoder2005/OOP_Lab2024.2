package hust.soict.hedspi.test.store;

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        Media dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        Media dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        Media dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);

        System.out.println("Testing print() method (empty store):");
        store.print();

        System.out.println("\nTesting addDigitalVideoDisc() method:");
        store.addDigitalVideoDisc(dvd1);
        store.addDigitalVideoDisc(dvd2);
        store.addDigitalVideoDisc(dvd3);

        System.out.println("\nTesting print() method (after adding DVDs):");
        store.print();

        System.out.println("\nTesting removeDigitalVideoDisc() method:");
        store.removeDigitalVideoDisc(dvd2);
        store.removeDigitalVideoDisc(new DigitalVideoDisc("Harry Potter", "Unknown", 0.0f));

        System.out.println("\nTesting print() method (after removing DVDs):");
        store.print();
    }
}