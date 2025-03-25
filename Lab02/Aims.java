package aims;

public class Aims {
	
	public static void main(String[] args) {
	    Cart anOrder = new Cart();
	    
	    DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
	    anOrder.addDigitalVideoDisc(dvd1);
	    
	    DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
	    anOrder.addDigitalVideoDisc(dvd2);
	    
	    DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
	    anOrder.addDigitalVideoDisc(dvd3);
	    
	    
	    System.out.print("Total Cost is: ");
	    System.out.println(anOrder.totalCost());
	    
	    anOrder.removeDigitalVideoDisc(dvd3);
	    System.out.print("Total Cost is: ");
	    System.out.println(anOrder.totalCost());
	    
	    /**DigitalVideoDisc dvd4 = new DigitalVideoDisc("Boku no Pico", "Anime", 19.26f);
	    DigitalVideoDisc dvd5 = new DigitalVideoDisc("Doraemon", "Animation", "Fujiko Fujio", 26.79f);
	    DigitalVideoDisc dvd6 = new DigitalVideoDisc("Huster ban ben thing thoang treu gheo toi bang OOP", "Anime", "NAP", 49, 88.99f);
	    
	    anOrder.addDigitalVideoDisc(dvd3, dvd4, dvd5);**/
	    
	    DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
	    DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");
	    
	    TestPassingParameter.swap(jungleDVD, cinderellaDVD);
	    System.out.println("jungle dvd title: " + jungleDVD.getTitle());
        System.out.println("cinderella dvd title: " + cinderellaDVD.getTitle());
	    
        TestPassingParameter.changeTitle(jungleDVD, cinderellaDVD.getTitle());
        System.out.println("jungle and title: " + jungleDVD.getTitle());
        
	    System.out.println("DVD 1 ID: " + dvd1.getId());
        System.out.println("DVD 2 ID: " + dvd2.getId());
        System.out.println("DVD 3 ID: " + dvd3.getId());
	}
}