import java.io.IOException;
import java.util.Scanner;
class WrongStudentName extends Exception {
    public WrongStudentName(String message) {
        super(message);
    }
}

class WrongAge extends Exception {
    public WrongAge(String message) {
        super(message);
    }
}

class WrongDateOfBirth extends Exception {
    public WrongDateOfBirth(String message) {
        super(message);
    }
}
public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                int ex = menu();
                switch (ex) {
                    case 1:
                        exercise1();
                        break;
                    case 2:
                        exercise2();
                        break;
                    case 3:
                        exercise3();
                        break;
                    default:
                        return;
                }
            } catch (IOException e) {
                System.out.println("IOException occurred!");
            } catch (WrongStudentName e) {
                System.out.println("Błędne imię studenta: " + e.getMessage());
            } catch (WrongAge e) {
                System.out.println("Błędny wiek studenta: " + e.getMessage());
            } catch (WrongDateOfBirth e) {
                System.out.println("Błędna data urodzenia studenta: " + e.getMessage());
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return scan.nextInt();
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine(); 
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        if (name.contains(" ")) {
            throw new WrongStudentName("Imię nie może zawierać spacji.");
        }
        return name;
    }

    public static int ReadAge() throws WrongAge {
        System.out.println("Podaj wiek: ");
        int age = scan.nextInt();
        if (age < 0 || age > 100) {
            throw new WrongAge("Wiek musi być w przedziale 0-100.");
        }
        return age;
    }

    public static String ReadDateOfBirth() throws WrongDateOfBirth {
        scan.nextLine(); 
        System.out.println("Podaj datę urodzenia (DD-MM-YYYY): ");
        String date = scan.nextLine();
        if (!date.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            throw new WrongDateOfBirth("Data urodzenia musi być w formacie DD-MM-YYYY.");
        }
        return date;
    }

    public static void exercise1() throws IOException, WrongStudentName, WrongAge, WrongDateOfBirth {
        var name = ReadName();
        var age = ReadAge();
        var date = ReadDateOfBirth();
        (new Service()).addStudent(new Student(name, age, date));
    }

    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for (Student current : students) {
            System.out.println(current.toString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine(); 
        System.out.println("Podaj imię: ");
        var name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if (wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.toString());
        }
    }
}