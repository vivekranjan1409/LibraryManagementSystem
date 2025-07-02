package project.LibraryManagementSystem;

public class User {
    private int userId;
    private String name;
    private String password;
    private String contact;
    private String registrationDate;
    public User(int userId, String name, String password, String contact, String registrationDate) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.registrationDate = registrationDate;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "User ID: " + userId +
               " | Name: " + name +
               " | Email: " + password +
               " | Contact: " + contact +
               " | Registered On: " + registrationDate;
    }
}
