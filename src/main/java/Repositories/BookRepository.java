package Repositories;

import database.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Book;

public class BookRepository {

    public static void addBook(Book book) {
        if (book == null) return;

        String sql = "INSERT INTO books (title, author, category, available) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setInt(4, book.isAvailable() ? 1 : 0);
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    book.setBookId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public static void updateBook(Book book) {
        if (book == null) return;

        String sql = "UPDATE books SET title = ?, author = ?, category = ?, available = ? WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setInt(4, book.isAvailable() ? 1 : 0);
            pstmt.setInt(5, book.getBookId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    public static void removeBook(int bookId) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing book: " + e.getMessage());
        }
    }

    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(mapRowToBook(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching books: " + e.getMessage());
        }
        return books;
    }

    public static int getNumberOfBooks() {
        String sql = "SELECT COUNT(*) FROM books";
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error counting books: " + e.getMessage());
        }
        return 0;
    }

    public static Book searchBook(String bookName) {
        if (bookName == null || bookName.trim().isEmpty()) return null;

        String sql = "SELECT * FROM books WHERE LOWER(title) = LOWER(?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookName.trim());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToBook(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching book: " + e.getMessage());
        }
        return null;
    }

    public static Book searchBook(int bookId) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToBook(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching book by ID: " + e.getMessage());
        }
        return null;
    }

    public static List<Book> searchBooksByKeyword(String keyword) {
        List<Book> results = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) return results;

        String sql = "SELECT * FROM books WHERE LOWER(title) LIKE LOWER(?) OR LOWER(author) LIKE LOWER(?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String pattern = "%" + keyword.trim() + "%";
            pstmt.setString(1, pattern);
            pstmt.setString(2, pattern);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRowToBook(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching books by keyword: " + e.getMessage());
        }
        return results;
    }

    private static Book mapRowToBook(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("category"),
                rs.getInt("available") == 1
        );
    }
}