package Services;

import models.User;
import models.Book;

import java.util.List;

import Repositories.BookRepository;


public class LibraryService {

    public static String addBook(String title, String author, String category) {
        Book book = new Book(title.trim().toLowerCase(), author.trim(), category.trim());
        BookRepository.addBook(book);
        return "Book added successfully: " + book.getTitle();
    }

    public static String removeBook(String bookTitle) {
        Book book = BookRepository.searchBook(bookTitle);
        if (book == null) {
            return "Book not found.";
        }
        BookRepository.removeBook(book);
        return "Book removed successfully.";
    }

    public static String borrowBook(User user, String bookTitle) {
        Book book = BookRepository.searchBook(bookTitle);
        if (book == null) {
            return "Book not found.";
        }
        if (!book.isAvailable()) {
            return book.getTitle() + " is not available for borrowing.";
        }

        for (int i = 0; i < user.getBorrowedBooks().length; i++) {
            if (user.getBorrowedBooks()[i] == null) {
                user.editBorrowedBooks(i, book);
                book.setAvailable(false);
                return user.getName() + " borrowed the book: " + book.getTitle();
            }
        }
        return user.getName() + " has already borrowed the maximum number of books.";
    }

    public static String returnBook(User user, String bookTitle) {
        Book book = BookRepository.searchBook(bookTitle);
        if (book == null) {
            return "Book not found.";
        }

        for (int i = 0; i < user.getBorrowedBooks().length; i++) {
            if (user.getBorrowedBooks()[i] != null && user.getBorrowedBooks()[i].getBookId() == book.getBookId()) {
                user.editBorrowedBooks(i, null);
                book.setAvailable(true);
                return user.getName() + " returned the book: " + book.getTitle();
            }
        }
        return user.getName() + " has not borrowed the book: " + book.getTitle();
    }

    public static List<Book> getAllBooks() {
        return BookRepository.getBooks();
    }

    public static Book searchBook(String title) {
        return BookRepository.searchBook(title);
    }
}