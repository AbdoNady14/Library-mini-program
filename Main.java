import java.util.Scanner;
import program.Program;


public class Main {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            Program.Options(scan);
        }
    }
}
