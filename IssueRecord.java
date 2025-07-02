package project.LibraryManagementSystem;

import java.time.LocalDate;

public class IssueRecord {
    private static int counter = 1001;

    private int issueId;
    private Book book;
    private User user;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public IssueRecord(Book book, User user, int loanDays) {
        this.issueId = counter++;
        this.book = book;
        this.user = user;
        this.issueDate = LocalDate.now();
        this.dueDate = issueDate.plusDays(loanDays);
        this.returnDate = null;
        this.isReturned = false;

        book.setAvailable(false); // mark the book as issued
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
        this.isReturned = true;
        book.setAvailable(true); // book is now available
    }

    // Getters and toString()
    public int getIssueId() {
        return issueId;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    @Override
    public String toString() {
        return "Issue ID: " + issueId +
               " | Book: " + book.getTitle() +
               " | User: " + user.getName() +
               " | Issue Date: " + issueDate +
               " | Due Date: " + dueDate +
               " | Returned: " + (isReturned ? "Yes on " + returnDate : "No");
    }
}