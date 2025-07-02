package project.LibraryManagementSystem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, User> userMap = new HashMap<>();
    private User currentUser = null;
    private int userIdCounter = 1001;

    public boolean registerUser(String name, String username, String password, String contact) {
        if (userMap.containsKey(username)) {
            System.out.println(" Username already taken.");
            return false;
        }
        String regDate = LocalDate.now().toString();
        User newUser = new User(userIdCounter++, name, password, contact, regDate);  // fixed order
        userMap.put(username, newUser);
        System.out.println(" Registration successful.");
        return true;
    }

    public boolean loginUser(String username, String password) {
        if (!userMap.containsKey(username)) {
            System.out.println("User not found.");
            return false;
        }

        User user = userMap.get(username);
        if (!user.getPassword().equals(password)) {
            System.out.println(" Incorrect password.");
            return false;
        }

        currentUser = user;
        System.out.println(" Welcome, " + user.getName() + "!");
        return true;
    }

    public void logoutUser() {
        if (currentUser != null) {
            System.out.println("Logged out: " + currentUser.getName());
            currentUser = null;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
