package Services;

import Repositories.BookRepository;
import Repositories.UserRepository;
import java.util.List;
import models.Book;
import models.User;

public class LibraryService {

    public static String addBook(String title, String author, String category) {
        if (title == null || title.trim().isEmpty() ||
            author == null || author.trim().isEmpty() ||
            category == null || category.trim().isEmpty()) {
            return "All book fields are required.";
        }
        Book book = new Book(title.trim().toLowerCase(), author.trim(), category.trim());
        BookRepository.addBook(book);
        return "Book added successfully: " + book.getTitle();
    }

    public static String updateBook(Book book) {
        if (book == null) {
            return "Book not found.";
        }
        BookRepository.updateBook(book);
        return "Book updated successfully.";
    }

    public static String removeBook(int bookId) {
        Book book = BookRepository.searchBook(bookId);
        if (book == null) {
            return "Book not found.";
        }
        if (!book.isAvailable()) {
            return "Cannot remove a book that is currently borrowed.";
        }
        BookRepository.removeBook(bookId);
        return "Book removed successfully.";
    }

    public static String removeBook(String bookTitle) {
        Book book = BookRepository.searchBook(bookTitle);
        if (book == null) {
            return "Book not found.";
        }
        return removeBook(book.getBookId());
    }

    public static String borrowBook(User user, String bookTitle) {
        if (user == null || bookTitle == null || bookTitle.trim().isEmpty()) {
            return "Invalid borrow request.";
        }
        Book book = BookRepository.searchBook(bookTitle);
        if (book == null) {
            return "Book not found.";
        }
        if (!book.isAvailable()) {
            return book.getTitle() + " is not available for borrowing.";
        }

        for (int i = 0; i < user.getBorrowedBooks().length; i++) {
            if (user.getBorrowedBooks()[i] == null) {
                book.setAvailable(false);
                user.editBorrowedBooks(i, book);

                BookRepository.updateBook(book);
                UserRepository.addBorrowedBook(user.getId(), book.getBookId());

                return user.getName() + " borrowed the book: " + book.getTitle();
            }
        }
        return user.getName() + " has already borrowed the maximum number of books.";
    }

    public static String returnBook(User user, String bookTitle) {
        if (user == null || bookTitle == null || bookTitle.trim().isEmpty()) {
            return "Invalid return request.";
        }
        Book book = BookRepository.searchBook(bookTitle);
        if (book == null) {
            return "Book not found.";
        }

        for (int i = 0; i < user.getBorrowedBooks().length; i++) {
            if (user.getBorrowedBooks()[i] != null && user.getBorrowedBooks()[i].getBookId() == book.getBookId()) {
                user.editBorrowedBooks(i, null);
                book.setAvailable(true);

                BookRepository.updateBook(book);
                UserRepository.removeBorrowedBook(user.getId(), book.getBookId());

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

    public static List<Book> searchBooksByKeyword(String keyword) {
        return BookRepository.searchBooksByKeyword(keyword);
    }
}