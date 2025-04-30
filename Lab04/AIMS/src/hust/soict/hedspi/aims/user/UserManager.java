package hust.soict.hedspi.aims.user;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
        users.add(new User("admin", "admin123"));
    }

    public boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void addUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists!");
                return;
            }
        }
        users.add(new User(username, password));
        System.out.println("User \"" + username + "\" has been added.");
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            oos.writeObject(this);
            System.out.println("User data saved to users.dat.");
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

    public static UserManager loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
            return (UserManager) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous user data found. Starting with default user (admin/admin123).");
            return new UserManager();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading user data: " + e.getMessage());
            return new UserManager();
        }
    }
}