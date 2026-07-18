public class Teacher extends User {
    
    Teacher(String name, String email) {
        super(name, email);
        this.borrowedBooks = new Book[5];
    }
}
