package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

import javax.naming.LimitExceededException;
import java.util.Scanner;

public class Aims {

	private static Store store = new Store();
	private static Cart cart = new Cart();

	public static void main(String[] args) throws LimitExceededException {
		Scanner scanner = new Scanner(System.in);
		boolean emerge = false;
		while (!emerge) {
			showMenu();
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
				case 0:
					emerge = true;
					System.out.println("Thank you for using our product! Goodbye!");
					break;
				case 1:
					storeMenu(scanner);
					break;
				case 2:
					updateStoreMenu(scanner);
					break;
				case 3:
					cartMenu(scanner);
					break;
				default:
					System.out.println("Invalid choice! Please choose again.");
			}
		}
	}

	public static void showMenu() {
		System.out.println("AIMS: ");
		System.out.println("--------------------------------");
		System.out.println("1. View store");
		System.out.println("2. Update store");
		System.out.println("3. See current cart");
		System.out.println("0. Exit");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3");
	}

	public static void storeMenu(Scanner scanner) throws LimitExceededException {
		boolean back = false;
		while (!back) {
			store.print();
			System.out.println("Options:");
			System.out.println("1. See a media’s details");
			System.out.println("2. Add a media to cart");
			System.out.println("3. Play a media");
			System.out.println("4. See current cart");
			System.out.println("0. Back");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 0:
					back = true;
					break;
				case 1:
					handleMediaDetails(scanner);
					break;
				case 2:
					handleAddToCart(scanner);
					break;
				case 3:
					handlePlayMedia(scanner);
					break;
				case 4:
					cartMenu(scanner);
					break;
				default:
					System.out.println("Invalid choice!");
			}
		}
	}

	private static void handleMediaDetails(Scanner scanner) throws LimitExceededException {
		while (true) {
			System.out.println("Enter the title of the media (type '#' to stop): ");
			String title = scanner.nextLine();
			if (title.equals("#")) break;

			Media media = store.search(title);
			if (media != null) {
				System.out.println("Details:\n" + media);
				mediaDetailsMenu(scanner, media);
				break;
			} else {
				System.out.println("Media not found!");
			}
		}
	}

	private static void handleAddToCart(Scanner scanner) {
		while (true) {
			System.out.println("Enter the title of the media (type '#' to stop): ");
			String title = scanner.nextLine();
			if (title.equals("#")) break;

			Media media = store.search(title);
			if (media != null) {
				try {
					cart.addItem(media);
					break;
				} catch (LimitExceededException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Media not found!");
			}
		}
	}

	private static void handlePlayMedia(Scanner scanner) {
		while (true) {
			System.out.println("Enter the title of the media (type '#' to stop): ");
			String title = scanner.nextLine();
			if (title.equals("#")) break;

			Media media = store.search(title);
			if (media instanceof Playable) {
				try {
					((Playable) media).play();
				} catch (PlayerException e) {
					System.err.println("Error playing media: " + e.getMessage());
				}
				break;
			} else {
				System.out.println("This media type cannot be played.");
			}
		}
	}

	public static void mediaDetailsMenu(Scanner scanner, Media media) throws LimitExceededException {
		boolean back = false;
		while (!back) {
			System.out.println("1. Add to cart\n2. Play\n0. Back");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 0:
					back = true;
					break;
				case 1:
					cart.addItem(media);
					break;
				case 2:
					if (media instanceof Playable) {
						try {
							((Playable) media).play();
						} catch (PlayerException e) {
							System.err.println("Error: " + e.getMessage());
						}
					} else {
						System.out.println("This media type is not playable.");
					}
					break;
				default:
					System.out.println("Invalid choice.");
			}
		}
	}

	public static void cartMenu(Scanner scanner) {
		boolean back = false;
		while (!back) {
			cart.showCart();
			System.out.println("Options: ");
			System.out.println("1. Filter media in cart");
			System.out.println("2. Sort media in cart");
			System.out.println("3. Remove media from cart");
			System.out.println("4. Play a media");
			System.out.println("5. Place order");
			System.out.println("0. Back");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 0:
					back = true;
					break;
				case 1:
					handleCartFilter(scanner);
					break;
				case 2:
					handleCartSort(scanner);
					break;
				case 3:
					handleCartRemove(scanner);
					break;
				case 4:
					handlePlayMedia(scanner);
					break;
				case 5:
					cart.clearCart();
					break;
				default:
					System.out.println("Invalid choice!");
			}
		}
	}

	private static void handleCartFilter(Scanner scanner) {
		System.out.println("Filter by id (1) or title (2): ");
		int choice = scanner.nextInt();
		scanner.nextLine();

		if (choice == 1) {
			System.out.println("Enter id: ");
			int id = scanner.nextInt();
			cart.findItemById(id);
		} else if (choice == 2) {
			System.out.println("Enter title: ");
			String title = scanner.nextLine();
			cart.findItemByTitle(title);
		}
	}

	private static void handleCartSort(Scanner scanner) {
		System.out.println("Sort by title (1) or cost (2): ");
		int choice = scanner.nextInt();
		scanner.nextLine();

		if (choice == 1) cart.sortByCostThenTitle();
		else if (choice == 2) cart.sortByTitleThenCost();
		else System.out.println("Invalid choice!");
	}

	private static void handleCartRemove(Scanner scanner) {
		System.out.println("Enter media title to remove: ");
		String title = scanner.nextLine();
		Media media = cart.findExactTitle(title);
		if (media != null) {
			cart.removeItem(media);
		} else {
			System.out.println("Media not found!");
		}
	}

	public static void updateStoreMenu(Scanner scanner) {
		boolean back = false;
		while (!back) {
			System.out.println("1. Add media\n2. Remove media\n0. Back");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 0:
					back = true;
					break;
				case 1:
					addMedia(scanner);
					break;
				case 2:
					removeMedia(scanner);
					break;
				default:
					System.out.println("Invalid option!");
			}
		}
	}

	private static void addMedia(Scanner scanner) {
		System.out.println("1. Book\n2. CD\n3. DVD\n0. Exit");
		int type = scanner.nextInt();
		scanner.nextLine();

		if (type == 1) {
			System.out.print("Title: "); String t = scanner.nextLine();
			System.out.print("Category: "); String c = scanner.nextLine();
			System.out.print("Cost: "); float cost = scanner.nextFloat(); scanner.nextLine();
			store.addMedia(new Book(t, c, cost));
		} else if (type == 2) {
			System.out.print("Title: "); String t = scanner.nextLine();
			System.out.print("Category: "); String c = scanner.nextLine();
			System.out.print("Artist: "); String artist = scanner.nextLine();
			System.out.print("Cost: "); float cost = scanner.nextFloat(); scanner.nextLine();
			CompactDisc cd = new CompactDisc(t, c, artist, cost);
			System.out.println("Add tracks? (1) Yes (0) No");
			if (scanner.nextInt() == 1) {
				scanner.nextLine();
				System.out.print("Number of tracks: "); int n = scanner.nextInt(); scanner.nextLine();
				for (int i = 0; i < n; i++) {
					System.out.print("Track title: "); String trTitle = scanner.nextLine();
					System.out.print("Track length: "); int length = scanner.nextInt(); scanner.nextLine();
					cd.appendTrack(new Track(trTitle, length));
				}
			}
			store.addMedia(cd);
		} else if (type == 3) {
			System.out.print("Title: "); String t = scanner.nextLine();
			System.out.print("Category: "); String c = scanner.nextLine();
			System.out.print("Cost: "); float cost = scanner.nextFloat(); scanner.nextLine();
			store.addMedia(new DigitalVideoDisc(t, c, cost));
		}
	}

	private static void removeMedia(Scanner scanner) {
		System.out.println("Enter title of media to remove: ");
		String title = scanner.nextLine();
		Media media = store.search(title);
		if (media != null) {
			store.removeMedia(media);
		} else {
			System.out.println("Media not found!");
		}
	}
}
