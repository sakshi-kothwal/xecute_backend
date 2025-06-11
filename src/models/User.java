package models; // This file is inside models folder
import java.util.ArrayList; // We use ArrayList to keep track of books the user has taken
public class User{
    private int id; // User's ID number
    private String name; // User's name
    private ArrayList<Integer> issuedBookIds; // List of book IDs this user has borrowed
    // When we create a user, we set their ID and name, and start with no borrowed books
    public User(int id,String name) {
        this.id=id; // Save user ID
        this.name=name; // Save user name
        this.issuedBookIds=new ArrayList<>(); // Start with empty list of borrowed books
    }
    // These are to get the user info later
    public int getId(){
        return id; // Return user ID
    }
    public String getName(){
        return name; // Return user name
    }
    public ArrayList<Integer> getIssuedBookIds(){
        return issuedBookIds; // Return list of borrowed book IDs
    }
    // Show user details nicely
    public void displayUser(){
        System.out.println("---------- User Details ----------");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Issued Book IDs: " + issuedBookIds);
        System.out.println("----------------------------------\n");
    }
    // When user borrows a book, add that book's ID here
    public void issueBook(int bookId){
        issuedBookIds.add(bookId);
    }
    // When user returns a book, remove that book's ID from the list
    public void returnBook(int bookId){
        issuedBookIds.remove(Integer.valueOf(bookId));
    }
}
