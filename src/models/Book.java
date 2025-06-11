package models; // This file is in the models folder
public class Book{
    private int id; // This is the book’s ID
    private String title; // Book name
    private String author; // Author’s name
    private int availableCopies; // How many copies are there to borrow
    // When we create a new book, we set all these details here
    public Book(int id,String title,String author,int availableCopies){
        this.id=id; // Save the book ID
        this.title=title; // Save the title
        this.author=author; // Save author name
        this.availableCopies=availableCopies; // Save copies available
    }
    // These methods help to get info about the book later
    public int getId(){
        return id; // Return the book ID
    }
    public String getTitle(){
        return title; // Return book title
    }
    public String getAuthor(){
        return author; // Return author name
    }
    public int getAvailableCopies(){
        return availableCopies; // Return how many copies left
    }
    // This is to update the number of copies when someone borrows or returns
    public void setAvailableCopies(int availableCopies){
        this.availableCopies=availableCopies;
    }
    // Show the book details in a nice way when we want to see them
    public void displayBooks(){
        System.out.println("---------- Book Details ----------");
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Available copies: " + availableCopies);
        System.out.println("----------------------------------\n");
    }
}
