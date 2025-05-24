import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagement system = new StudentManagement();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student Basic Info");
            System.out.println("4. Update Educational Details");
            System.out.println("5. Update Parent Details");
            System.out.println("6. Delete Student");
            System.out.println("7. Search Student");
            System.out.println("8. Student File");
            System.out.println("9. Export Detailed List to File");
            System.out.println("10. Export Students By Course");
            System.out.println("11. Export Parent Details");
            System.out.println("12. Attendance Management");
            System.out.println("13. Fee Management");
            System.out.println("14. Academic Reports");
            System.out.println("15. Schedule Management");
            System.out.println("16. Exit");
            System.out.print("Enter your choice (1-16): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    system.addStudent();
                    break;
                case 2:
                    system.viewAllStudents();
                    break;
                case 3:
                    system.updateStudent();
                    break;
                case 4:
                    system.updateEducationalDetails();
                    break;
                case 5:
                    system.updateParentDetails();
                    break;
                case 6:
                    system.deleteStudent();
                    break;
                case 7:
                    system.searchStudent();
                    break;
                case 8:
                    system.exportToFile();
                    break;
                case 9:
                    system.exportDetailedToFile();
                    break;
                case 10:
                    system.exportStudentsByCourse();
                    break;
                case 11:
                    system.exportParentDetails();
                    break;
                case 12:
                    system.manageAttendance();
                    break;
                case 13:
                    system.manageFees();
                    break;
                case 14:
                    system.generateReports();
                    break;
                case 15:
                    system.manageSchedule();
                    break;
                case 16:
                    System.out.println("Thank you for using Student Management System!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 16.");
            }
        }
    }
} 