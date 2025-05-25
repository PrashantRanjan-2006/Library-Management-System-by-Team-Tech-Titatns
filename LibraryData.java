import java.util.*;

public class LibraryData {
    private static LibraryData instance;
    private List<User> users;
    private List<Book> books;

    private LibraryData() {
        users = new ArrayList<>();
        users.add(new User("admin", "admin"));
        users.add(new User("student", "1234"));
        users.add(new User("divyansh", "pass123"));
        users.add(new User("prashant", "prashant@321"));
        users.add(new User("nishant", "nishant456"));
        users.add(new User("kushagra", "kushagra789"));
        users.add(new User("avesh", "avesh007"));
        users.add(new User("gautam", "gautam007"));

        books = new ArrayList<>();
        books.add(new Book("Java Programming"));
        books.add(new Book("Data Structures"));
        books.add(new Book("Discrete Mathematics"));
        books.add(new Book("Introduction to Algorithms"));
        books.add(new Book("The Pragmatic Programmer"));
        books.add(new Book("Clean Code"));
        books.add(new Book("Data Structures and Algorithms in Java"));
        books.add(new Book("Operating System Concepts"));
        books.add(new Book("Computer Networks"));
        books.add(new Book("Artificial Intelligence: A Modern Approach"));
        books.add(new Book("Java: The Complete Reference"));
        books.add(new Book("Python Crash Course"));
        books.add(new Book("C Programming Language"));
        books.add(new Book("Effective Java"));
        books.add(new Book("Design Patterns: Elements of Reusable Object-Oriented Software"));
        books.add(new Book("Head First Design Patterns"));
        books.add(new Book("Modern Database Management"));
        books.add(new Book("Introduction to Machine Learning"));
        books.add(new Book("Deep Learning with Python"));
        books.add(new Book("Beginning Android Development"));
        books.add(new Book("Full Stack Web Development"));
        books.add(new Book("HTML and CSS: Design and Build Websites"));
        books.add(new Book("JavaScript: The Good Parts"));
        books.add(new Book("React Up & Running"));
        books.add(new Book("Learning SQL"));
        books.add(new Book("Network Security Essentials"));
        books.add(new Book("Cybersecurity for Beginners"));
        books.add(new Book("Engineering Mechanics"));
        books.add(new Book("Fluid Mechanics"));
        books.add(new Book("Concrete Technology"));
        books.add(new Book("Environmental Engineering"));
        books.add(new Book("Structural Analysis"));
    }

    public static LibraryData getInstance() {
        if (instance == null) instance = new LibraryData();
        return instance;
    }

    public User authenticate(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.checkPassword(password)) return u;
        }
        return null;
    }

    public String listBooks() {
        StringBuilder sb = new StringBuilder("Available Books:\n");
        for (Book b : books) {
            sb.append(b.getTitle()).append(b.isAvailable() ? " (Available)" : " (Borrowed)").append("\n");
        }
        return sb.toString();
    }

    public String borrowBook(String title, String user) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) return b.borrow(user);
        }
        return "Book not found.";
    }

    public String returnBook(String title, String user) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) return b.returnBook(user);
        }
        return "Book not found.";
    }

    public int getFine(String user) {
        int total = 0;
        for (Book b : books) {
            if (b.isBorrowedBy(user)) total += b.calculateFine();
        }
        return total;
    }

    // Admin-only methods
    public String getBorrowedBooksDetails() {
        StringBuilder sb = new StringBuilder("Borrowed Books Details:\n\n");
        boolean hasBorrowedBooks = false;
        
        for (Book b : books) {
            if (!b.isAvailable()) {
                sb.append(b.getBorrowingDetails()).append("\n");
                hasBorrowedBooks = true;
            }
        }
        
        if (!hasBorrowedBooks) {
            sb.append("No books are currently borrowed.\n");
        }
        
        return sb.toString();
    }

    public String getAllBooksDetails() {
        StringBuilder sb = new StringBuilder("All Books Status:\n\n");
        
        for (Book b : books) {
            sb.append(b.getBorrowingDetails()).append("\n");
        }
        
        return sb.toString();
    }

    public String getUserBorrowingHistory(String username) {
        StringBuilder sb = new StringBuilder("Books borrowed by " + username + ":\n\n");
        boolean hasBorrowedBooks = false;
        
        for (Book b : books) {
            if (b.isBorrowedBy(username)) {
                sb.append(b.getBorrowingDetails()).append("\n");
                hasBorrowedBooks = true;
            }
        }
        
        if (!hasBorrowedBooks) {
            sb.append("This user has not borrowed any books.\n");
        }
        
        return sb.toString();
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        for (User u : users) {
            if (!u.isAdmin()) {
                usernames.add(u.getUsername());
            }
        }
        return usernames;
    }
}