package project.LibraryManagementSystem;

import java.util.HashMap;
import java.util.Map;

public class LibrarianService {
    private Map<String,Librarian> librarianMap = new HashMap<>();

    private Librarian currLibrarian = null;

    public LibrarianService(){
        Librarian defaultAdmin = new Librarian("Admin", "admin", "admin123", "9999999999");
        librarianMap.put(defaultAdmin.getUsername(), defaultAdmin);
    }

    public boolean registerLibrarian(String name, String username, String password, String contact){
        if (librarianMap.containsKey(username)) {
            System.out.println("Username already exists. Please choose another.");
            return false;
        }
        Librarian librarian = new Librarian(name, username, password, contact);
        librarianMap.put(username, librarian);
        System.out.println("Librarian registered successfully!");
        return true;
    }

    public boolean loginLibrarian(String username,String password){
        if(!librarianMap.containsKey(username)){
            System.out.println("No Librarian found with this name.");
            return false;
        }
        Librarian librarian = librarianMap.get(username);
        if (!librarian.getPassword().equals(password)) {
            System.out.println("Incorrect Password");
            return false;
        }

        currLibrarian = librarian;
        System.out.println("Welcome"+currLibrarian.getName());

        return true;
    }

    public void logout(){
        if(currLibrarian != null){
            System.out.println("Logged Out "+currLibrarian.getName());
        }
        currLibrarian = null;
    }

    public boolean isLoggedIn(){
        return currLibrarian != null;
    }
    public Librarian getCurrLibrarian(){
        return currLibrarian;
    }
}
