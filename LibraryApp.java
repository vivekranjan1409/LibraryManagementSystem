package project.LibraryManagementSystem;

import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private final Scanner sc = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final LibrarianService librarianService = new LibrarianService();
    private final FineService fineService = new FineService();
    private final IssueService issueService = new IssueService(fineService);
    private final BookService bookService = new BookService();

    public static void main(String[] args) {
        new LibraryApp().start();
    }

    public void start() {
        while (true) {

            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Login as Student");
            System.out.println("2. Register as Student");
            System.out.println("3. Login as Librarian");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> studentLogin();
                case 2 -> registerStudent();
                case 3 -> librarianLogin();
                case 4 -> {
                    System.out.println("Thank you for Using Library Managemnet System");
                    return;
                }
                default -> System.out.println("Invalid Choice.");

            }
        }
    }

    private void registerStudent() {
        sc.nextLine();
        System.out.print("Enter name: ");

        String name = sc.nextLine().trim();
        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();
        System.out.print("Enter password: ");
        String password = sc.nextLine().trim();
        System.out.print("Enter contact: ");
        String contact = sc.nextLine().trim();

        userService.registerUser(name, username, password, contact);
    }

    private void studentLogin() {
        sc.nextLine(); // consume leftover newline
        System.out.print("Enter Username: ");
        String username = sc.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = sc.nextLine().trim();

        if (userService.loginUser(username, password)) {
            studentMenu();
        }
    }

    private void librarianLogin() {
        System.out.print("Enter Librarian Username: ");
        String username = sc.next();
        System.out.print("Enter Password: ");
        String password = sc.next();

        if (librarianService.loginLibrarian(username, password)) {
            librarianMenu();
        }
    }

    private void studentMenu() {
        while (userService.isLoggedIn()) {
            System.out.println("\n=== STUDENT MENU ===");
            System.out.println("1. View Available Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. View My Issued Books");
            System.out.println("5. View & Pay My Fines");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1 -> bookService.listAvailableBooks();
                case 2 -> issueBook();
                case 3 -> returnBook();
                case 4 -> viewMyIssues();
                case 5 -> viewAndPayFines();
                case 6 -> {
                    userService.logoutUser();
                    return;
                }
                case 7 -> System.out.println("Invalid Choice.");
            }
        }
    }

    private void librarianMenu() {
        while (librarianService.isLoggedIn()) {
            System.out.println("\n=== LIBRARIAN MENU ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. View All Issued Records");
            System.out.println("4. View All Fines");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> addBook();
                case 2 -> bookService.listAllBooks();
                case 3 -> viewAllIssues();
                case 4 -> fineService.showAllFines();
                case 5 -> {
                    librarianService.logout();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter Book Title: ");
        sc.nextLine();
        String title = sc.nextLine();
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        Book book = new Book(title, author);
        bookService.addBook(book);
        System.out.println("Book Added.");
    }

    private void issueBook() {
        bookService.listAvailableBooks();
        System.out.print("Enter Book ID for issue ");
        int bookId = sc.nextInt();
        System.out.print("Enter number of days to borrow: ");
        int days = sc.nextInt();

        Book book = bookService.findBookById(bookId);

        if (book != null) {
            issueService.issueBook(book, userService.getCurrentUser(), days);
        } else {
            System.out.println("Book Not Found.");
        }
    }

    private void returnBook() {
        List<IssueRecord> myIssues = issueService.getIssuedBooksByUser(userService.getCurrentUser());

        if (myIssues.isEmpty()) {
            System.out.println("You haven't issued any booked");
            return;
        }

        System.out.println("Your Issued Book:");
        for (IssueRecord record : myIssues) {
            System.out.println(record);
        }
        System.out.print("Enter issue ID to return: ");
        int id = sc.nextInt();
        issueService.returnBook(id, userService.getCurrentUser());
    }

    private void viewMyIssues() {
        List<IssueRecord> myIssues = issueService.getIssuedBooksByUser(userService.getCurrentUser());
        if (myIssues.isEmpty()) {
            System.out.println("No books issued.");
            return;
        }
        for (IssueRecord record : myIssues) {
            System.out.println(record);
        }
    }

    private void viewAndPayFines() {
        List<Fine> fines = fineService.getUnpaidFines(userService.getCurrentUser());
        if (fines.isEmpty()) {
            System.out.println("No unpaid fines.");
            return;
        }

        for (Fine fine : fines) {
            System.out.println(fine);
        }
        System.out.print("Enter fine ID to pay (0 to cancel): ");
        int fineId = sc.nextInt();
        if (fineId != 0) {
            fineService.payFine(fineId, userService.getCurrentUser());
        }
    }

    private void viewAllIssues() {
        List<IssueRecord> all = issueService.getAllIssueRecords();
        if (all.isEmpty()) {
            System.out.println("No books have been issued.");
            return;
        }
        for (IssueRecord record : all) {
            System.out.println(record);
        }
    }
}
