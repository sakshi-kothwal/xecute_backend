package main;  // This is the main package where the program starts
import services.LibraryService;  // Import the LibraryService class to use library functions
import java.util.Scanner;  // Import Scanner to get input from user
public class LibraryApp{
    public static void main(String[] args){
        LibraryService library=new LibraryService();  // Create object to manage books and users
        Scanner scanner=new Scanner(System.in);   // Scanner for reading user input
        int choice=-1;     // Variable to store user's menu choice
        System.out.println("📚 Welcome to the Library Management System 📚");  // Welcome message
        do {
            // Print the menu options every time before user picks one
            System.out.println("\n---------- MENU ----------");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Remove Book");
            System.out.println("5. Add User");
            System.out.println("6. View All Users");
            System.out.println("7. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("9. View Issued Books");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice=scanner.nextInt();  // Read the choice from user
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");  // If input is not a number
                scanner.nextLine();  // Clear the wrong input from scanner buffer
                continue;  // Go back to start of loop and show menu again
            }
            // Perform action based on user choice
            switch (choice){
                case 1:
                    library.addBook();  // Call method to add a new book
                    break;
                case 2:
                    library.viewBooks();  // Show all books available
                    break;
                case 3:
                    library.searchBook();  // Search for a book by title or author
                    break;
                case 4:
                    library.removeBook();  // Remove a book by its ID
                    break;
                case 5:
                    library.addUser();  // Add a new user to the system
                    break;
                case 6:
                    library.viewUsers();  // View all users registered
                    break;
                case 7:
                    library.issueBook();  // Issue a book to a user
                    break;
                case 8:
                    library.returnBook();  // Return a previously issued book
                    break;
                case 9:
                    library.viewIssuedBooks();  // Show which books are currently issued
                    break;
                case 0:
                    System.out.println("Thank you for using the Library Management System!");  // Exit message
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");  // If user enters number not in menu
            }
        } while (choice != 0);  // Repeat until user chooses 0 to exit
        scanner.close();  // Close scanner to free resources
    }
}
