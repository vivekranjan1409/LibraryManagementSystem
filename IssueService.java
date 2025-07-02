package project.LibraryManagementSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IssueService {
    private final List<IssueRecord> issueRecords = new ArrayList<>();
    private final FineService fineService;

    public IssueService(FineService fineService) {
        this.fineService = fineService;
    }

    public boolean issueBook(Book book, User user, int loanDays) {
        if (!book.isAvailable()) {
            System.out.println("Book is already issued.");
            return false;
        }
        IssueRecord record = new IssueRecord(book, user, loanDays);
        issueRecords.add(record);
        System.out.println("Book issued successfully.");
        System.out.println(record);
        return true;
    }

    public boolean returnBook(int issueId, User user) {
        for (IssueRecord record : issueRecords) {
            if (record.getIssueId() == issueId && record.getUser().getUserId() == user.getUserId()) {
                if (record.isReturned()) {
                    System.out.println("Book already returned.");
                    return false;
                }

                LocalDate returnDate = LocalDate.now();
                record.returnBook(); // set return date and update book status

                // Check if overdue
                if (returnDate.isAfter(record.getDueDate())) {
                    Fine fine = new Fine(user, record.getBook(), record.getDueDate(), returnDate);
                    fineService.addFine(fine);
                    System.out.println("Book returned late. Fine generated:");
                    System.out.println(fine);
                } else {
                    System.out.println("Book returned successfully.");
                }

                return true;
            }
        }

        System.out.println("Issue record not found.");
        return false;
    }

    
    public List<IssueRecord> getIssuedBooksByUser(User user) {
        List<IssueRecord> result = new ArrayList<>();
        for (IssueRecord record : issueRecords) {
            if (record.getUser().getUserId() == user.getUserId()) {
                result.add(record);
            }
        }
        return result;
    }

    
    public List<IssueRecord> getAllIssueRecords() {
        return new ArrayList<>(issueRecords);
    }

   
    public boolean cancelIssue(int issueId, User user) {
        Iterator<IssueRecord> iterator = issueRecords.iterator();
        while (iterator.hasNext()) {
            IssueRecord record = iterator.next();
            if (record.getIssueId() == issueId && record.getUser().getUserId() == user.getUserId()) {
                if (record.isReturned()) {
                    System.out.println("Cannot cancel, book already returned.");
                    return false;
                }
                record.getBook().setAvailable(true); 
                iterator.remove();
                System.out.println("Issue record canceled.");
                return true;
            }
        }
        System.out.println("Issue record not found.");
        return false;
    }
}


