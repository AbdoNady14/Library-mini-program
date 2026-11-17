package models;
import java.util.Scanner;

public abstract class User {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String email;
    private String password;
    protected Book[] borrowedBooks;
    
// لإنشاء مستخدم جديد (Signup)
    protected User(String name, String email, String password) {
        this(idCounter++, name, email, password);
    }


// لتحميل مستخدم موجود مسبقاً من الداتا بيز أو الملف
    protected User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        if (id >= idCounter) {
            idCounter = id + 1; // يضمن إن العداد يكمل بعد أكبر ID موجود
        }
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
