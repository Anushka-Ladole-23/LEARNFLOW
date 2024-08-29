//TASK 2
//Student Information System
//Description : Develop a Student Information System for educational institutions. The system should manage student records, course registrations, grades, and generate transcripts.

import java.util.HashMap;
 import java.util.Map;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.stream.Collectors;
 import java.util.Scanner;
 import java.lang.String;
class Student {
    private String id;
    private String name;
    private String email;

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String toString() {
        return "Student{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", email='"+email+ '\'' + '}';
   
 }

}



class Course {
    private String code;
    private String title;
    private int credits;

    public Course(String code, String title, int credits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
    }

    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                '}';
    }
}



class Grade {
    private String studentId;
    private String courseCode;
    private char grade;

    public Grade(String studentId, String courseCode, char grade) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.grade = grade;
            }

    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public char getGrade() { return grade; }
    public void setGrade(char grade) { this.grade = grade; }


    @Override
    public String toString() {
        return "Grade{" +
                "studentId='" + studentId + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", grade=" + grade + '}';
    }
}


class StudentService {
    private Map<String, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student getStudent(String id) {
        return students.get(id);
    }

    public void removeStudent(String id) {
        students.remove(id);
    }
}


class CourseService {
    private Map<String, Course> courses = new HashMap<>();

    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    public Course getCourse(String code) {
        return courses.get(code);
    }

    public void removeCourse(String code) {
        courses.remove(code);
    }
}

 class GradeService {
    private List<Grade> grades = new ArrayList<>();

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public List<Grade> getGradesByStudent(String studentId) {
        return grades.stream()
                     .filter(grade -> grade.getStudentId().equals(studentId))
                     .collect(Collectors.toList());
    }

    public List<Grade> getGradesByCourse(String courseCode) {
        return grades.stream()
                     .filter(grade -> grade.getCourseCode().equals(courseCode))
                     .collect(Collectors.toList());
    }
}

public class StudentInformationSystem {
    private static StudentService studentService = new StudentService();
    private static CourseService courseService = new CourseService();
    private static GradeService gradeService = new GradeService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Information System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Register Grade");
            System.out.println("4. View Student Grades");
            System.out.println("5. View Course Grades");
            System.out.println("6. Generate Transcript");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    addCourse(scanner);
                    break;
                case 3:
                    registerGrade(scanner);
                    break;
                case 4:
                    viewStudentGrades(scanner);
                    break;
                case 5:
                    viewCourseGrades(scanner);
                    break;
                case 6:
                    generateTranscript(scanner);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        Student student = new Student(id, name, email);
        studentService.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void addCourse(Scanner scanner) {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter course credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Course course = new Course(code, title, credits);
        courseService.addCourse(course);
        System.out.println("Course added successfully.");
    }

    private static void registerGrade(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter grade: ");
        char grade = scanner.nextLine().charAt(0);

        Grade gradeObj = new Grade(studentId, courseCode, grade);
        gradeService.addGrade(gradeObj);
        System.out.println("Grade registered successfully.");
    }

    private static void viewStudentGrades(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        System.out.println("Grades for student ID " + studentId + ":");
        gradeService.getGradesByStudent(studentId).forEach(System.out::println);
    }

    private static void viewCourseGrades(Scanner scanner) {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        System.out.println("Grades for course code " + courseCode + ":");
        gradeService.getGradesByCourse(courseCode).forEach(System.out::println);
    }

    private static void generateTranscript(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        Student student = studentService.getStudent(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Transcript for " + student.getName() + " (ID: " + student.getId() + "):");
        gradeService.getGradesByStudent(studentId).forEach(System.out::println);
    }
}



