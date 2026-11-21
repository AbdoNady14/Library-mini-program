package models;
import java.util.Scanner;

public abstract class User {
    private int id;
    private String name;
    private String email;
    private String password;
    protected Book[] borrowedBooks;
    
// Used when creating a new user to be added to the database
    protected User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


// to load an existing user from the database
    protected User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Book[] getBorrowedBooks() {
        return borrowedBooks;
    }

    public void editBorrowedBooks(int index, Book book) {
        this.borrowedBooks[index] = book;
    }

    public abstract void showMenu(Scanner scan);
}
