import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Service {

    private static final String DB_FILE = "db.txt";

    public void addStudent(Student student) throws IOException {
        try (BufferedWriter b = new BufferedWriter(new FileWriter(DB_FILE, true))) {
            b.append(student.toString());
            b.newLine();
        }
    }

    public Collection<Student> getStudents() throws IOException {
        var students = new ArrayList<Student>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(Student.parse(line));
            }
        }
        return students;
    }

    public Student findStudentByName(String name) throws IOException {
        var students = getStudents();
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }
}
