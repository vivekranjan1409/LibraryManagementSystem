package project.LibraryManagementSystem;

public class Librarian {
    private int librarianId;
    private String name;
    private String username;
    private String password;
    private String contact;

    private static int counter = 1; // Auto-increment ID

    // Constructor
    public Librarian(String name, String username, String password, String contact) {
        this.librarianId = counter++;
        this.name = name;
        this.username = username;
        this.password = password;
        this.contact = contact;
    }

    // Getters and Setters
    public int getLibrarianId() {
        return librarianId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // Utility
    @Override
    public String toString() {
        return "Librarian ID: " + librarianId +
               " | Name: " + name +
               " | Username: " + username +
               " | Contact: " + contact;
    }
}
