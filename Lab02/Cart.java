package aims;

public class Cart {
	public static final int MAX_NUMBER_ORDERED = 20;
	private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBER_ORDERED];
	
	private int qtyOrdered = 0;
	
	public void addDigitalVideoDisc(DigitalVideoDisc disc) {
		if (qtyOrdered + 1 > MAX_NUMBER_ORDERED) {
			System.out.println("The cart is almost full");
		}
		else {
			itemsOrdered[qtyOrdered++] = disc;
			System.out.println("The disc has been added");
		}
	}
	
	public void addDigitalVideoDisc(DigitalVideoDisc [] dvdList) {
		int numDvd = 0;
		for (int i = 0; i < 20; i++) {
			if (dvdList[i] != null) {
				numDvd++;
			}
			else break;
		}
		if (qtyOrdered + numDvd > MAX_NUMBER_ORDERED) {
			System.out.println("The cart is almost full");
		}
		else {
			for (int i = 0; i < numDvd; i++) {
				itemsOrdered[qtyOrdered++] = dvdList[i];
			}
			System.out.println("The discs have been added");
		}
	}
	
	/**public void addDigitalVideoDisc(DigitalVideoDisc... dvds) {
		int numDvd = dvds.length;
		if (qtyOrdered + numDvd > MAX_NUMBER_ORDERED) {
			System.out.println("The cart is almost full");
		}
		else {
			for (DigitalVideoDisc dvd: dvds) {
				itemsOrdered[qtyOrdered++] = dvd;
			}
			System.out.println("The discs have been added");
		}
    }**/
	
	public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
		boolean found = false;
		for (int i = 0; i < qtyOrdered; i++) {
			if (itemsOrdered[i].getTitle().equals(disc.getTitle())) {
				found = true;
				for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j+1];
                }
                itemsOrdered[qtyOrdered - 1] = null;
                qtyOrdered--;
                System.out.println("The disc has been removed");
                break;
			}
		}
		if (!found) {
			 System.out.println("The disc is not in the cart");
		}
	}
	
	public float totalCost() {
		float total = 0;
		for (int i = 0; i < qtyOrdered; i++) {
			total += itemsOrdered[i].getCost(); 
		}
		return total;
	}  
}