import database.Database;
import java.util.Scanner;
import program.Program;

public class Main {
    public static void main(String[] args) {
        try {
            Database.initializeDatabase();

            try (Scanner scan = new Scanner(System.in)) {
                Program.Options(scan);
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}