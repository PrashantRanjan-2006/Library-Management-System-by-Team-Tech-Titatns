import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
    private String title;
    private boolean isBorrowed;
    private String borrower;
    private LocalDate borrowDate;

    public Book(String title) {
        this.title = title;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return !isBorrowed;
    }

    public String getBorrower() {
        return borrower;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public String getBorrowDateFormatted() {
        if (borrowDate == null) return "N/A";
        return borrowDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String borrow(String user) {
        if (isBorrowed) return "Book is already borrowed.";
        isBorrowed = true;
        borrower = user;
        borrowDate = LocalDate.now();
        return "Book borrowed successfully.";
    }

    public String returnBook(String user) {
        if (!isBorrowed || !borrower.equals(user)) return "You did not borrow this book.";
        isBorrowed = false;
        int days = (int) java.time.temporal.ChronoUnit.DAYS.between(borrowDate, LocalDate.now());
        int fine = Math.max(0, days - 14) * 5;
        borrower = null;
        borrowDate = null;
        return "Book returned. Fine: ₹" + fine;
    }

    public boolean isBorrowedBy(String user) {
        return isBorrowed && borrower.equals(user);
    }

    public int calculateFine() {
        if (!isBorrowed) return 0;
        int days = (int) java.time.temporal.ChronoUnit.DAYS.between(borrowDate, LocalDate.now());
        return Math.max(0, days - 14) * 5;
    }

    
    public String getBorrowingDetails() {
        if (!isBorrowed) {
            return title + " - Available";
        } else {
            int daysHeld = (int) java.time.temporal.ChronoUnit.DAYS.between(borrowDate, LocalDate.now());
            int fine = calculateFine();
            return title + " - Borrowed by: " + borrower + 
                   " | Date: " + getBorrowDateFormatted() + 
                   " | Days held: " + daysHeld + 
                   " | Fine: ₹" + fine;
        }
    }
}