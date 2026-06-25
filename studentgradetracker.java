import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Student {
    private String name;
    private double[] grades;
    
    public Student(String name, double[] grades) {
        this.name = name;
        this.grades = grades;
    }
    
    public String getName() {
        return name;
    }
    
    public double[] getGrades() {
        return grades;
    }
    
    public double calculateAverage() {
        if (grades.length == 0) return 0;
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }
    
    public double getHighestGrade() {
        if (grades.length == 0) return 0;
        double highest = grades[0];
        for (double grade : grades) {
            if (grade > highest) highest = grade;
        }
        return highest;
    }
    
    public double getLowestGrade() {
        if (grades.length == 0) return 0;
        double lowest = grades[0];
        for (double grade : grades) {
            if (grade < lowest) lowest = grade;
        }
        return lowest;
    }
}

public class GradeTracker {
    private ArrayList<Student> students;
    private Scanner scanner;
    
    public GradeTracker() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        System.out.print("How many grades for this student? ");
        int numGrades = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        double[] grades = new double[numGrades];
        for (int i = 0; i < numGrades; i++) {
            System.out.print("Enter grade " + (i + 1) + ": ");
            grades[i] = scanner.nextDouble();
            scanner.nextLine(); // consume newline
        }
        
        students.add(new Student(name, grades));
        System.out.println("Student added successfully!\n");
    }
    
    public void displaySummary() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }
        
        System.out.println("\n=== STUDENT GRADE SUMMARY ===");
        System.out.println("=".repeat(50));
        
        for (Student student : students) {
            System.out.printf("Student: %s\n", student.getName());
            System.out.printf("Grades: ");
            for (double grade : student.getGrades()) {
                System.out.printf("%.2f ", grade);
            }
            System.out.printf("\nAverage: %.2f | Highest: %.2f | Lowest: %.2f\n",
                student.calculateAverage(), student.getHighestGrade(), student.getLowestGrade());
            System.out.println("-".repeat(50));
        }
        
        // Overall statistics
        System.out.println("\n=== OVERALL STATISTICS ===");
        if (students.size() > 1) {
            ArrayList<Double> allGrades = new ArrayList<>();
            for (Student student : students) {
                for (double grade : student.getGrades()) {
                    allGrades.add(grade);
                }
            }
            
            double overallAvg = allGrades.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double overallHigh = Collections.max(allGrades);
            double overallLow = Collections.min(allGrades);
            
            System.out.printf("Overall Average: %.2f\n", overallAvg);
            System.out.printf("Overall Highest Grade: %.2f\n", overallHigh);
            System.out.printf("Overall Lowest Grade: %.2f\n", overallLow);
            System.out.printf("Total Students: %d | Total Grades: %d\n", students.size(), allGrades.size());
        }
        System.out.println("=".repeat(50) + "\n");
    }
    
    public void searchStudent() {
        System.out.print("Enter student name to search: ");
        String searchName = scanner.nextLine();
        
        boolean found = false;
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(searchName)) {
                System.out.println("\n=== STUDENT FOUND ===");
                System.out.printf("Name: %s\n", student.getName());
                System.out.printf("Grades: ");
                for (double grade : student.getGrades()) {
                    System.out.printf("%.2f ", grade);
                }
                System.out.printf("\nAverage: %.2f | Highest: %.2f | Lowest: %.2f\n",
                    student.calculateAverage(), student.getHighestGrade(), student.getLowestGrade());
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Student not found.");
        }
    }
    
    public void showMenu() {
        System.out.println("\n=== GRADE TRACKER MENU ===");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students Summary");
        System.out.println("3. Search Student");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }
    
    public void run() {
        System.out.println("Welcome to Student Grade Tracker!");
        
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displaySummary();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    System.out.println("Thank you for using Grade Tracker!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    public static void main(String[] args) {
        GradeTracker tracker = new GradeTracker();
        tracker.run();
    }
}