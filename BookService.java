package project.LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final List<Book> bookList = new ArrayList<>();
    private int bookIdCounter = 1001;

    public void addBook(Book book) {
        book.setBookId(bookIdCounter++);
        book.setAvailable(true);
        bookList.add(book);
    }
    public void listAllBooks() {
        if (bookList.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("All Books:");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    public void listAvailableBooks() {
        boolean found = false;
        System.out.println("Available Books:");
        for (Book book : bookList) {
            if (book.isAvailable()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books available at the moment.");
        }
    }

    public Book findBookById(int bookId) {
        for (Book book : bookList) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public boolean removeBook(int bookId) {
        return bookList.removeIf(book -> book.getBookId() == bookId);
    }
}
