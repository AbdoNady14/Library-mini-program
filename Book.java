public class Book {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private boolean isAvailable;

    public Book(String title, String author, String category) {
        this.bookId = Books.getNumberOfBooks() + 1;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = true;
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

}
