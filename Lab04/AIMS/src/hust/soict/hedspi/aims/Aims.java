package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.notification.NotificationManager;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.order.Order;
import hust.soict.hedspi.aims.order.OrderHistory;
import java.util.InputMismatchException;
import java.util.Scanner;
import hust.soict.hedspi.aims.user.UserManager;

public class Aims {
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    
	    UserManager userManager = UserManager.loadFromFile();
	    boolean loggedIn = false;

	    while (!loggedIn) {
	        System.out.println("\nAIMS Login System");
	        System.out.println("1. Login");
	        System.out.println("2. Register");
	        System.out.println("0. Exit");
	        System.out.print("Enter your choice: ");
	        
	        int loginChoice;
	        try {
	            loginChoice = scanner.nextInt();
	            scanner.nextLine();
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input! Please enter a number.");
	            scanner.nextLine();
	            continue;
	        }

	        switch (loginChoice) {
	            case 1:
	                System.out.print("Enter username: ");
	                String username = scanner.nextLine();
	                System.out.print("Enter password: ");
	                String password = scanner.nextLine();
	                if (userManager.authenticate(username, password)) {
	                    System.out.println("Login successful! Welcome, " + username + "!");
	                    loggedIn = true;
	                } else {
	                    System.out.println("Invalid username or password. Please try again.");
	                }
	                break;
	            case 2:
	                System.out.print("Enter new username: ");
	                String newUsername = scanner.nextLine();
	                System.out.print("Enter new password: ");
	                String newPassword = scanner.nextLine();
	                userManager.addUser(newUsername, newPassword);
	                break;
	            case 0:
	                System.out.println("Goodbye!");
	                userManager.saveToFile();
	                scanner.close();
	                return;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    }

	    Store store = Store.loadFromFile();
	    Cart cart = Cart.loadFromFile();
	    OrderHistory orderHistory = OrderHistory.loadFromFile();
	    NotificationManager notificationManager = new NotificationManager();
	    int choice = 0;

	    if (store.getQtyInStore() == 0) {
	        Media dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
	        Media dvd2 = new DigitalVideoDisc("Star Wars", "Sci-Fi", "George Lucas", 124, 24.95f);
	        Media book = new Book("Harry Potter", "Fantasy", 29.99f);
	        Media cd = new CompactDisc("Abbey Road", "Rock", "The Beatles", 15.99f);
	        store.addDigitalVideoDisc(dvd1);
	        store.addDigitalVideoDisc(dvd2);
	        store.addDigitalVideoDisc(book);
	        store.addDigitalVideoDisc(cd);
	    }

	    do {
	        System.out.println("\nAIMS Menu:");
	        System.out.println("1. View store");
	        System.out.println("2. Search store by title");
	        System.out.println("3. Search store by ID");
	        System.out.println("4. Sort store by title");
	        System.out.println("5. Sort store by cost");
	        System.out.println("6. Add a media to store");
	        System.out.println("7. Add a media to cart");
	        System.out.println("8. View cart");
	        System.out.println("9. Search cart by title");
	        System.out.println("10. Search cart by ID");
	        System.out.println("11. Sort cart by title");
	        System.out.println("12. Sort cart by cost");
	        System.out.println("13. Remove a media from cart");
	        System.out.println("14. Place order");
	        System.out.println("15. Filter store by category");
	        System.out.println("16. Filter cart by category");
	        System.out.println("17. Play a media from store");
	        System.out.println("18. Play a media from cart");
	        System.out.println("19. View order history");
	        System.out.println("20. Search store by cost range");
	        System.out.println("21. Search cart by cost range");
	        System.out.println("22. View statistics by category in store");
	        System.out.println("23. View statistics by category in cart");
	        System.out.println("24. View notifications");
	        System.out.println("25. Clear notifications");
	        System.out.println("0. Exit");
	        System.out.print("Enter your choice: ");
	        
	        try {
	            choice = scanner.nextInt();
	            scanner.nextLine();
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input! Please enter a number.");
	            scanner.nextLine();
	            continue;
	        }

	        switch (choice) {
	            case 1:
	                store.print();
	                break;
	            case 2:
	                System.out.print("Enter title to search: ");
	                String titleSearch = scanner.nextLine();
	                store.searchByTitle(titleSearch);
	                break;
	            case 3:
	                System.out.print("Enter ID to search: ");
	                try {
	                    int idSearch = scanner.nextInt();
	                    store.searchById(idSearch);
	                } catch (InputMismatchException e) {
	                    System.out.println("Invalid ID! Please enter a number.");
	                    scanner.nextLine();
	                }
	                break;
	            case 4:
	                store.sortByTitle();
	                store.print();
	                break;
	            case 5:
	                store.sortByCost();
	                store.print();
	                break;
	            case 6:
	                try {
	                    System.out.println("Enter media type (1: DVD, 2: Book, 3: CD): ");
	                    int mediaType = scanner.nextInt();
	                    scanner.nextLine();
	                    System.out.print("Enter title: ");
	                    String title = scanner.nextLine();
	                    System.out.print("Enter category: ");
	                    String category = scanner.nextLine();
	                    System.out.print("Enter cost: ");
	                    float cost = scanner.nextFloat();
	                    if (cost < 0) {
	                        System.out.println("Cost cannot be negative!");
	                        scanner.nextLine();
	                        break;
	                    }
	                    scanner.nextLine();

	                    Media newMedia = null;
	                    switch (mediaType) {
	                        case 1:
	                            System.out.print("Enter director: ");
	                            String director = scanner.nextLine();
	                            System.out.print("Enter length: ");
	                            int length = scanner.nextInt();
	                            if (length <= 0) {
	                                System.out.println("Length must be positive!");
	                                scanner.nextLine();
	                                break;
	                            }
	                            newMedia = new DigitalVideoDisc(title, category, director, length, cost);
	                            break;
	                        case 2:
	                            newMedia = new Book(title, category, cost);
	                            break;
	                        case 3:
	                            System.out.print("Enter artist: ");
	                            String artist = scanner.nextLine();
	                            newMedia = new CompactDisc(title, category, artist, cost);
	                            // Thêm track vào CD
	                            while (true) {
	                                System.out.print("Add a track to this CD? (y/n): ");
	                                String addTrackChoice = scanner.nextLine();
	                                if (addTrackChoice.equalsIgnoreCase("n")) break;
	                                System.out.print("Enter track title: ");
	                                String trackTitle = scanner.nextLine();
	                                System.out.print("Enter track length (seconds): ");
	                                int trackLength = scanner.nextInt();
	                                scanner.nextLine();
	                                try {
	                                    Track track = new Track(trackTitle, trackLength);
	                                    ((CompactDisc) newMedia).addTrack(track);
	                                } catch (IllegalArgumentException e) {
	                                    System.out.println("Error: " + e.getMessage());
	                                }
	                            }
	                            break;
	                        default:
	                            System.out.println("Invalid media type.");
	                            continue;
	                    }
	                    store.addDigitalVideoDisc(newMedia);
	                } catch (InputMismatchException e) {
	                    System.out.println("Invalid input! Please enter correct data types.");
	                    scanner.nextLine();
	                }
	                break;
	            case 7:
	                System.out.print("Enter the title of the media to add to cart: ");
	                String titleAdd = scanner.nextLine();
	                boolean foundAdd = false;
	                for (int i = 0; i < store.getQtyInStore(); i++) {
	                    Media media = store.getItemsInStore()[i];
	                    if (media.getTitle().equalsIgnoreCase(titleAdd)) {
	                        cart.addDigitalVideoDisc(media, notificationManager);
	                        store.removeDigitalVideoDisc(media);
	                        foundAdd = true;
	                        break;
	                    }
	                }
	                if (!foundAdd) {
	                    System.out.println("Media with title \"" + titleAdd + "\" not found in the store.");
	                    notificationManager.addNotification("Failed to add \"" + titleAdd + "\" to cart: Item not found in store.");
	                }
	                break;
	            case 8:
	                cart.print();
	                break;
	            case 9:
	                System.out.print("Enter title to search in cart: ");
	                String titleCartSearch = scanner.nextLine();
	                cart.searchByTitle(titleCartSearch);
	                break;
	            case 10:
	                System.out.print("Enter ID to search in cart: ");
	                try {
	                    int idCartSearch = scanner.nextInt();
	                    cart.searchById(idCartSearch);
	                } catch (InputMismatchException e) {
	                    System.out.println("Invalid ID! Please enter a number.");
	                    scanner.nextLine();
	                }
	                break;
	            case 11:
	                cart.sortByTitle();
	                cart.print();
	                break;
	            case 12:
	                cart.sortByCost();
	                cart.print();
	                break;
	            case 13:
	                System.out.print("Enter the title of the media to remove from cart: ");
	                String titleRemove = scanner.nextLine();
	                boolean foundRemove = false;
	                for (int i = 0; i < cart.getQtyOrdered(); i++) {
	                    Media media = cart.getItemsOrdered()[i];
	                    if (media.getTitle().equalsIgnoreCase(titleRemove)) {
	                        cart.removeDigitalVideoDisc(media, notificationManager);
	                        store.addDigitalVideoDisc(media);
	                        foundRemove = true;
	                        break;
	                    }
	                }
	                if (!foundRemove) {
	                    System.out.println("Media with title \"" + titleRemove + "\" not found in the cart.");
	                    notificationManager.addNotification("Failed to remove \"" + titleRemove + "\" from cart: Item not found.");
	                }
	                break;
	            case 14:
	                if (cart.getQtyOrdered() == 0) {
	                    System.out.println("Cart is empty. Cannot place order.");
	                    notificationManager.addNotification("Failed to place order: Cart is empty.");
	                } else {
	                    Order order = new Order();
	                    for (int i = 0; i < cart.getQtyOrdered(); i++) {
	                        order.addDigitalVideoDisc(cart.getItemsOrdered()[i]);
	                    }
	                    order.print();
	                    orderHistory.addOrder(order);
	                    notificationManager.addNotification("Order placed successfully.");
	                    cart = new Cart();
	                }
	                break;
	            case 15:
	                System.out.print("Enter category to filter in store: ");
	                String categoryStoreFilter = scanner.nextLine();
	                store.filterByCategory(categoryStoreFilter);
	                break;
	            case 16:
	                System.out.print("Enter category to filter in cart: ");
	                String categoryCartFilter = scanner.nextLine();
	                cart.filterByCategory(categoryCartFilter);
	                break;
	            case 17:
	                System.out.print("Enter the title of the media to play from store: ");
	                String titlePlayStore = scanner.nextLine();
	                store.playMedia(titlePlayStore);
	                break;
	            case 18:
	                System.out.print("Enter the title of the media to play from cart: ");
	                String titlePlayCart = scanner.nextLine();
	                cart.playMedia(titlePlayCart);
	                break;
	            case 19:
	                orderHistory.printHistory();
	                break;
	            case 20:
	                try {
	                    System.out.print("Enter minimum cost: ");
	                    float minCostStore = scanner.nextFloat();
	                    System.out.print("Enter maximum cost: ");
	                    float maxCostStore = scanner.nextFloat();
	                    scanner.nextLine();
	                    store.searchByCostRange(minCostStore, maxCostStore);
	                } catch (InputMismatchException e) {
	                    System.out.println("Invalid cost input! Please enter valid numbers.");
	                    scanner.nextLine();
	                }
	                break;
	            case 21:
	                try {
	                    System.out.print("Enter minimum cost: ");
	                    float minCostCart = scanner.nextFloat();
	                    System.out.print("Enter maximum cost: ");
	                    float maxCostCart = scanner.nextFloat();
	                    scanner.nextLine();
	                    cart.searchByCostRange(minCostCart, maxCostCart);
	                } catch (InputMismatchException e) {
	                    System.out.println("Invalid cost input! Please enter valid numbers.");
	                    scanner.nextLine();
	                }
	                break;
	            case 22:
	                store.printStatisticsByCategory();
	                break;
	            case 23:
	                cart.printStatisticsByCategory();
	                break;
	            case 24:
	                notificationManager.printNotifications();
	                break;
	            case 25:
	                notificationManager.clearNotifications();
	                break;
	            case 0:
	                store.saveToFile();
	                cart.saveToFile();
	                orderHistory.saveToFile();
	                userManager.saveToFile();
	                System.out.println("Goodbye!");
	                break;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    } while (choice != 0);

	    scanner.close();
	}
}