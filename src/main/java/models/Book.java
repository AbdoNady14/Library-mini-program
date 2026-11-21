package models;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private boolean isAvailable;

// Used when creating a new book to be added to the database
    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = true;
    }

// Used when loading an existing book from the database
    public Book(int bookId, String title, String author, String category, boolean isAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book Id: " + bookId + ", Title: " + title + ", Author: " + author 
            + ", Category: " + category + ", Available: " + isAvailable;
    }

}
