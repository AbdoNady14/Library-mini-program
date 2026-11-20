package Repositories;

import database.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Book;
import models.Student;
import models.Teacher;
import models.User;

public class UserRepository {

    public static void addUser(User user) {
        if (user == null) return;

        String role = (user instanceof Teacher) ? "TEACHER" : "STUDENT";
        String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, role);
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    public static User getUserByEmail(String email) {
        if (email == null || email.trim().isEmpty()) return null;

        String sql = "SELECT * FROM users WHERE LOWER(email) = LOWER(?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email.trim());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = mapRowToUser(rs);
                    loadBorrowedBooks(user);
                    return user;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding user by email: " + e.getMessage());
        }
        return null;
    }

    public static List<User> getUsersByRole(String role) {
        List<User> users = new ArrayList<>();
        if (role == null || role.trim().isEmpty()) return users;

        String sql = "SELECT * FROM users WHERE role = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, role.trim());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    User user = mapRowToUser(rs);
                    loadBorrowedBooks(user);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching users by role: " + e.getMessage());
        }
        return users;
    }

    public static void updateUser(User user) {
        if (user == null) return;

        String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setInt(4, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public static void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    public static void addBorrowedBook(int userId, int bookId) {
        String sql = "INSERT INTO borrowed_books (user_id, book_id) VALUES (?, ?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error recording borrowed book: " + e.getMessage());
        }
    }

    public static void removeBorrowedBook(int userId, int bookId) {
        String sql = "DELETE FROM borrowed_books WHERE user_id = ? AND book_id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing borrowed book: " + e.getMessage());
        }
    }

    public static void loadBorrowedBooks(User user) {
        if (user == null) return;

        String sql = """
            SELECT b.* FROM books b
            INNER JOIN borrowed_books bb ON b.id = bb.book_id
            WHERE bb.user_id = ?
            """;
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getId());
            try (ResultSet rs = pstmt.executeQuery()) {
                int index = 0;
                Book[] array = user.getBorrowedBooks();
                while (rs.next() && index < array.length) {
                    Book book = new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("category"),
                            rs.getInt("available") == 1
                    );
                    user.editBorrowedBooks(index++, book);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading borrowed books: " + e.getMessage());
        }
    }

    private static User mapRowToUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String role = rs.getString("role");

        if ("TEACHER".equalsIgnoreCase(role)) {
            return new Teacher(id, name, email, password);
        }
        return new Student(id, name, email, password);
    }
}