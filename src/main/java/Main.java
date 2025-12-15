import java.io.*;
import java.util.*;

class WrongStudentName extends Exception {}
class WrongAge extends Exception {}
class WrongDateOfBirth extends Exception {}

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                int choice = menu();
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        showStudents();
                        break;
                    case 3:
                        findStudent();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Nieprawidłowa opcja");
                }
            } catch (WrongStudentName e) {
                System.out.println("Błędne imię studenta!");
            } catch (WrongAge e) {
                System.out.println("Błędny wiek! Poprawny zakres: 1-99");
            } catch (WrongDateOfBirth e) {
                System.out.println("Błędna data urodzenia! Format DD-MM-YYYY");
            } catch (IOException e) {
                System.out.println("Błąd pliku!");
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - dodaj studenta");
        System.out.println("2 - wypisz studentów");
        System.out.println("3 - wyszukaj studenta");
        System.out.println("0 - wyjście");
        return scan.nextInt();
    }

    public static String readName() throws WrongStudentName {
        scan.nextLine();
        System.out.print("Podaj imię: ");
        String name = scan.nextLine();
        if (name.isEmpty() || name.contains(" "))
            throw new WrongStudentName();
        return name;
    }

    public static int readAge() throws WrongAge {
        System.out.print("Podaj wiek: ");
        int age = scan.nextInt();
        if (age < 1 || age > 99)
            throw new WrongAge();
        return age;
    }

    public static String readDate() throws WrongDateOfBirth {
        scan.nextLine();
        System.out.print("Podaj datę urodzenia (DD-MM-YYYY): ");
        String date = scan.nextLine();

        if (!date.matches("\\\\d{2}-\\\\d{2}-\\\\d{4}"))
            throw new WrongDateOfBirth();

        return date;
    }

    public static void addStudent() throws IOException, WrongStudentName, WrongAge, WrongDateOfBirth {
        String name = readName();
        int age = readAge();
        String date = readDate();

        new Service().addStudent(new Student(name, age, date));
        System.out.println("Student dodany");
    }

    public static void showStudents() throws IOException {
        for (Student s : new Service().getStudents())
            System.out.println(s);
    }

    public static void findStudent() throws IOException {
        scan.nextLine();
        System.out.print("Podaj imię: ");
        String name = scan.nextLine();
        Student s = new Service().findStudentByName(name);
        if (s == null)
            System.out.println("Nie znaleziono");
        else
            System.out.println(s);
    }
}