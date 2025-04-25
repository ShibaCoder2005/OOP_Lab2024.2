package hust.soict.hedspi.aims.notification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NotificationManager {
    private List<String> notifications;

    public NotificationManager() {
        this.notifications = new ArrayList<>();
    }

    public void addNotification(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);
        notifications.add("[" + timestamp + "] " + message);
    }

    public void printNotifications() {
        if (notifications.isEmpty()) {
            System.out.println("No notifications.");
            return;
        }
        System.out.println("\nNotifications:");
        for (String notification : notifications) {
            System.out.println(notification);
        }
    }

    public void clearNotifications() {
        notifications.clear();
        System.out.println("Notifications cleared.");
    }
}