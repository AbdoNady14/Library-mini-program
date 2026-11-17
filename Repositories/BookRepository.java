package Repositories;

import java.util.ArrayList;
import java.util.List;

import models.Book;

public class BookRepository {
    private static final List<Book> books = new ArrayList<>();

    public static void addBook(Book book) {
        books.add(book);
    }

    public static void removeBook(Book book) {
        books.remove(book);
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static int getNumberOfBooks() {
        return books.size();
    }

    public static Book searchBook(String bookName) {
        String target = bookName.trim().toLowerCase();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(target)) {
                return book;
            }
        }
        return null;
    }

    public static Book searchBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}