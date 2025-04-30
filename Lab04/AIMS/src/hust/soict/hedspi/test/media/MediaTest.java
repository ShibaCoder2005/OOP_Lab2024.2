package hust.soict.hedspi.test.media;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class MediaTest {
    public static void main(String[] args) {
        DigitalVideoDisc dvd = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        Book book = new Book("Harry Potter", "Fantasy", 29.99f);
        CompactDisc cd = new CompactDisc("Abbey Road", "Rock", "The Beatles", 15.99f);

        dvd.setId(1);
        book.setId(2);
        cd.setId(3);

        System.out.println("Testing toString() method:");
        System.out.println(dvd.toString());
        System.out.println(book.toString());
        System.out.println(cd.toString());

        System.out.println("\nTesting isMatch() method:");
        System.out.println("Search 'Lion' in DVD: " + dvd.isMatch("Lion"));
        System.out.println("Search 'Potter' in Book: " + book.isMatch("Potter"));
        System.out.println("Search 'Beatles' in CD: " + cd.isMatch("Beatles")); 
        System.out.println("Search 'Abbey' in CD: " + cd.isMatch("Abbey"));
    }
}