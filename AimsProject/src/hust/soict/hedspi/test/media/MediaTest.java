package hust.soict.hedspi.test.media;

import java.util.ArrayList;
import java.util.List;

import hust.soict.hedspi.aims.media.*;

public class MediaTest {
        public static void main(String[] args) {
                List<Media> mediae = new ArrayList<>();

                DigitalVideoDisc dvd = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
                Book book = new Book("Detective Conan", "Detective", 20.00f);

                CompactDisc cd = new CompactDisc("Believer", "Music", "Imagine Dragon", 2500.99f);
                Track track1 = new Track("THE WXRDIES", 110);
                Track track2 = new Track("Hex", 50);
                Track track3 = new Track("The World", 200);

                cd.appendTrack(track1);
                cd.appendTrack(track2);
                cd.appendTrack(track3);

                mediae.add(cd);
                mediae.add(book);
                mediae.add(dvd);

                for (Media m : mediae) {
                        System.out.println(m.toString());
                }
        }
}
