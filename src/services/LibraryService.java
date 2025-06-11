package services;  // This file is inside services folder
import models.Book;  // We use Book class from models
import models.User;  // We use User class from models
import java.util.ArrayList;  // To store list of books and users
import java.util.Scanner;    // To take input from user
public class LibraryService {
    private ArrayList<Book> books=new ArrayList<>();  // List to store all books
    private ArrayList<User> users=new ArrayList<>();  // List to store all users
    private Scanner scanner=new Scanner(System.in);   // Scanner to read input
    // Method to add a new book to the system
    public void addBook(){
        try {
            System.out.print("Enter Book ID: ");  // Ask for book ID
            int id=scanner.nextInt();  // Read book ID
            scanner.nextLine();  // Clear the buffer
            // Check if book ID already exists
            if (findBookById(id) != null){
                System.out.println("Book ID already exists!\n");
                return;  // Stop here if book is already there
            }
            System.out.print("Enter Book Title: ");  // Ask for book title
            String title=scanner.nextLine();  // Read book title
            System.out.print("Enter Book Author: ");  // Ask for author name
            String author=scanner.nextLine();  // Read author name
            System.out.print("Enter number of copies: ");  // Ask how many copies
            int copies=scanner.nextInt();  // Read number of copies
            scanner.nextLine();  // Clear buffer
            // If copies are less than or equal to zero, show error and stop
            if (copies <= 0) {
                System.out.println("Number of copies must be positive!\n");
                return;
            }
            // Add new book to the list
            books.add(new Book(id, title, author, copies));
            System.out.println("Book added successfully!\n");  // Show success message
        } catch (Exception e){
            System.out.println("Invalid input! Please try again.\n");  // If user inputs wrong thing
            scanner.nextLine();  // Clear scanner buffer
        }
    }
    // Show all books present
    public void viewBooks(){
        if (books.isEmpty()){  // If no books present
            System.out.println("No books available.\n");
        } else {
            // Loop through each book and display details
            for (Book book : books){
                book.displayBooks();
            }
        }
    }
    // Search book by title or author
    public void searchBook(){
        scanner.nextLine();  // Clear buffer before input
        System.out.print("Enter title or author to search: ");  // Ask what to search
        String query=scanner.nextLine().toLowerCase();  // Convert input to lowercase for easy matching
        boolean found=false;  // Flag to check if any book found
        // Check all books one by one
        for (Book book : books){
            // If title or author contains search word
            if (book.getTitle().toLowerCase().contains(query) || book.getAuthor().toLowerCase().contains(query)) {
                book.displayBooks();  // Show the book
                found=true;  // Mark as found
            }
        }
        if (!found){  // If nothing found, show this
            System.out.println("No books found matching the query.\n");
        }
    }
    // Remove a book by its ID
    public void removeBook(){
        try {
            System.out.print("Enter Book ID to remove: ");  // Ask which book to remove
            int id=scanner.nextInt();
            scanner.nextLine();  // Clear buffer
            Book book=findBookById(id);  // Find book by ID
            if (book == null){  // If not found
                System.out.println("Book not found!\n");
                return;
            }
            books.remove(book);  // Remove the book from list
            System.out.println("Book removed successfully!\n");
        } catch (Exception e){
            System.out.println("Invalid input! Please try again.\n");  // Handle bad input
            scanner.nextLine();
        }
    }
    // Add a new user to the system
    public void addUser(){
        try {
            System.out.print("Enter User ID: ");  // Ask for user ID
            int id=scanner.nextInt();
            scanner.nextLine();
            if (findUserById(id) != null) {  // Check if user ID already exists
                System.out.println("User ID already exists!\n");
                return;
            }
            System.out.print("Enter User Name: ");  // Ask for user name
            String name = scanner.nextLine();
            users.add(new User(id, name));  // Add new user to list
            System.out.println("User added successfully!\n");
        } catch (Exception e) {
            System.out.println("Invalid input! Please try again.\n");
            scanner.nextLine();
        }
    }
    // Show all users
    public void viewUsers(){
        if (users.isEmpty()){
            System.out.println("No users registered.\n");
        } else {
            for (User user : users){
                user.displayUser();
            }
        }
    }
    // Issue a book to a user
    public void issueBook(){
        try {
            System.out.print("Enter User ID: ");  // Ask user ID
            int userId=scanner.nextInt();
            System.out.print("Enter Book ID: ");  // Ask book ID
            int bookId=scanner.nextInt();
            scanner.nextLine();
            Book book=findBookById(bookId);
            User user=findUserById(userId);
            if (book == null || user == null){  // If user or book not found
                System.out.println("User or Book not found!\n");
                return;
            }
            if (book.getAvailableCopies() <= 0){  // If no copies available
                System.out.println("No copies available!\n");
                return;
            }
            book.setAvailableCopies(book.getAvailableCopies() - 1);  // Reduce copies by one
            user.issueBook(bookId);  // Add book ID to user's issued list
            System.out.println("Book issued successfully!\n");
        } catch (Exception e) {
            System.out.println("Invalid input! Please try again.\n");
            scanner.nextLine();
        }
    }
    // Return a book from user
    public void returnBook(){
        try {
            System.out.print("Enter User ID: ");  // Ask user ID
            int userId=scanner.nextInt();
            System.out.print("Enter Book ID: ");  // Ask book ID
            int bookId=scanner.nextInt();
            scanner.nextLine();
            Book book=findBookById(bookId);
            User user=findUserById(userId);
            if (book == null || user == null){  // If either not found
                System.out.println("User or Book not found!\n");
                return;
            }
            if (!user.getIssuedBookIds().contains(bookId)){  // If user didn't borrow this book
                System.out.println("This user didn't borrow this book!\n");
                return;
            }
            book.setAvailableCopies(book.getAvailableCopies() + 1);  // Increase copies by one
            user.returnBook(bookId);  // Remove book from user's issued list
            System.out.println("Book returned successfully!\n");
        } catch (Exception e){
            System.out.println("Invalid input! Please try again.\n");
            scanner.nextLine();
        }
    }
    // Show all issued books by users
    public void viewIssuedBooks(){
        boolean anyIssued=false;  // Flag to check if anyone has issued books

        for (User user : users){  // Check every user
            if (!user.getIssuedBookIds().isEmpty()){  // If user has issued books
                System.out.println("User: " + user.getName() + " (ID: " + user.getId() + ")");
                System.out.println("Issued Book IDs: " + user.getIssuedBookIds() + "\n");
                anyIssued=true;  // Mark that there are issued books
            }
        }
        if (!anyIssued){
            System.out.println("No books are currently issued.\n");
        }
    }
    // Helper method: find book by its ID in list
    private Book findBookById(int id){
        for (Book book : books) {
            if (book.getId() == id)
                return book;
        }
        return null;  // Return null if not found
    }
    // Helper method: find user by ID
    private User findUserById(int id){
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }
}
