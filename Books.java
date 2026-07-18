import java.util.ArrayList;
import java.util.List;

public class Books {
    private static int numberOfBooks = 0;
    private static final List<Book> books = new ArrayList<>();

    public static void addBook(Book book) {
        books.add(book);
        numberOfBooks++;
    }

    public static void removeBook(Book book) {
        books.remove(book);
        numberOfBooks--;
    }
    public static List<Book> getBooks() {
        return books;
    }
    public static int getNumberOfBooks() {
        return numberOfBooks;
    }
}
