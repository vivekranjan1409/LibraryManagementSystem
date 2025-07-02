
package project.LibraryManagementSystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Fine {
    private static int fineIdCounter = 1;

    private int fineId;
    private User user;
    private Book book;
    private int overdueDays;
    private double fineAmount;
    private boolean isPaid;

    private static final double FINE_PER_DAY = 2.0; // You can adjust this

    public Fine(User user, Book book, LocalDate dueDate, LocalDate returnDate) {
        this.fineId = fineIdCounter++;
        this.user = user;
        this.book = book;
        this.overdueDays = (int) ChronoUnit.DAYS.between(dueDate, returnDate);
        this.fineAmount = overdueDays * FINE_PER_DAY;
        this.isPaid = false;
    }

    // Getters and Setters
    public int getFineId() {
        return fineId;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public int getOverdueDays() {
        return overdueDays;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void payFine() {
        this.isPaid = true;
    }

    @Override
    public String toString() {
        return "Fine ID: " + fineId +
               " | User: " + user.getName() +
               " | Book: " + book.getTitle() +
               " | Overdue Days: " + overdueDays +
               " | Fine Amount: ₹" + fineAmount +
               " | Status: " + (isPaid ? "Paid" : "Unpaid");
    }
}


