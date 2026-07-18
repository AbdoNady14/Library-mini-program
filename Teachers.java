import java.util.ArrayList; 
import java.util.List;

public class Teachers {
    private static List<Teacher> teachers = new ArrayList<>();

    public static void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public static List<Teacher> getTeachers() {
        return teachers;
    }
}
