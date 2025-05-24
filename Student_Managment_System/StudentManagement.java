import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentManagement {
    private List<Student> students;
    private Scanner scanner;
    private static final String TABLE_HEADER = "\n+------+----------------------+------+-----------------+---------------------------+-----------------+--------------+\n" +
                                             "| ID   | Name                 | Age  | Course          | Email                     | Phone          | Enroll Date  |\n" +
                                             "+------+----------------------+------+-----------------+---------------------------+-----------------+--------------+";
    private static final String TABLE_FOOTER = "+------+----------------------+------+-----------------+---------------------------+-----------------+--------------+";

    public StudentManagement() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Add a new student
    public void addStudent() {
        System.out.println("\n=== Add New Student ===");
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter student course: ");
        String course = scanner.nextLine();
        
        System.out.print("Enter semester (1-8): ");
        int semester = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter year (1-4): ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        System.out.print("Enter student phone number: ");
        String phoneNumber = scanner.nextLine();

        String enrollmentDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        Student student = new Student(id, name, age, course, email, phoneNumber, enrollmentDate);
        student.setSemester(semester);
        student.setYear(year);
        
        System.out.println("\nWould you like to add educational and parent details? (y/n): ");
        String choice = scanner.nextLine();
        
        if (choice.equalsIgnoreCase("y")) {
            // 10th Marksheet Details
            System.out.println("\n=== 10th Marksheet Details ===");
            System.out.print("Enter 10th school name: ");
            String tenthSchoolName = scanner.nextLine();
            
            System.out.print("Enter 10th board: ");
            String tenthBoard = scanner.nextLine();
            
            System.out.print("Enter 10th year of passing: ");
            int tenthYearOfPassing = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter 10th percentage: ");
            double tenthPercentage = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            student.setTenthSchoolName(tenthSchoolName);
            student.setTenthBoard(tenthBoard);
            student.setTenthYearOfPassing(tenthYearOfPassing);
            student.setTenthPercentage(tenthPercentage);
            
            // 12th Marksheet Details
            System.out.println("\n=== 12th Marksheet Details ===");
            System.out.print("Enter 12th school name: ");
            String twelfthSchoolName = scanner.nextLine();
            
            System.out.print("Enter 12th board: ");
            String twelfthBoard = scanner.nextLine();
            
            System.out.print("Enter 12th year of passing: ");
            int twelfthYearOfPassing = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter 12th percentage: ");
            double twelfthPercentage = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            student.setTwelfthSchoolName(twelfthSchoolName);
            student.setTwelfthBoard(twelfthBoard);
            student.setTwelfthYearOfPassing(twelfthYearOfPassing);
            student.setTwelfthPercentage(twelfthPercentage);
            
            // Parent Details
            System.out.println("\n=== Parent Details ===");
            System.out.print("Enter father's name: ");
            String fatherName = scanner.nextLine();
            
            System.out.print("Enter mother's name: ");
            String motherName = scanner.nextLine();
            
            System.out.print("Enter parent's email: ");
            String parentEmail = scanner.nextLine();
            
            System.out.print("Enter parent's phone: ");
            String parentPhone = scanner.nextLine();
            
            System.out.print("Enter parent's occupation: ");
            String parentOccupation = scanner.nextLine();
            
            System.out.print("Enter parent's address: ");
            String parentAddress = scanner.nextLine();
            
            student.setFatherName(fatherName);
            student.setMotherName(motherName);
            student.setParentEmail(parentEmail);
            student.setParentPhone(parentPhone);
            student.setParentOccupation(parentOccupation);
            student.setParentAddress(parentAddress);
        }
        
        students.add(student);
        System.out.println("Student added successfully!");
    }

    // View all students
    public void viewAllStudents() {
        System.out.println("\n=== All Students ===");
        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }
        System.out.println(TABLE_HEADER);
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println(TABLE_FOOTER);
    }

    // View student details
    public void viewStudentDetails() {
        System.out.println("\n=== View Student Details ===");
        System.out.print("Enter student ID to view details: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean found = false;
        for (Student student : students) {
            if (student.getId() == id) {
                student.displayDetailedInfo();
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Student not found!");
        }
    }

    // Update student information
    public void updateStudent() {
        System.out.println("\n=== Update Student ===");
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Student student : students) {
            if (student.getId() == id) {
                System.out.print("Enter new age (0 to keep current): ");
                int age = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (age != 0) {
                    student.setAge(age);
                }

                System.out.print("Enter new course (press Enter to keep current): ");
                String course = scanner.nextLine();
                if (!course.isEmpty()) {
                    student.setCourse(course);
                }
                
                System.out.print("Enter new semester (0 to keep current): ");
                int semester = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (semester != 0) {
                    student.setSemester(semester);
                }
                
                System.out.print("Enter new year (0 to keep current): ");
                int year = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (year != 0) {
                    student.setYear(year);
                }

                System.out.print("Enter new email (press Enter to keep current): ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) {
                    student.setEmail(email);
                }

                System.out.print("Enter new phone number (press Enter to keep current): ");
                String phoneNumber = scanner.nextLine();
                if (!phoneNumber.isEmpty()) {
                    student.setPhoneNumber(phoneNumber);
                }

                System.out.println("Student information updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Update educational details
    public void updateEducationalDetails() {
        System.out.println("\n=== Update Educational Details ===");
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("1. Update 10th Marksheet Details");
                System.out.println("2. Update 12th Marksheet Details");
                System.out.print("Enter your choice (1-2): ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                if (choice == 1) {
                    System.out.print("Enter new 10th school name (press Enter to keep current): ");
                    String schoolName = scanner.nextLine();
                    if (!schoolName.isEmpty()) {
                        student.setTenthSchoolName(schoolName);
                    }
                    
                    System.out.print("Enter new 10th board (press Enter to keep current): ");
                    String board = scanner.nextLine();
                    if (!board.isEmpty()) {
                        student.setTenthBoard(board);
                    }
                    
                    System.out.print("Enter new 10th year of passing (0 to keep current): ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (year != 0) {
                        student.setTenthYearOfPassing(year);
                    }
                    
                    System.out.print("Enter new 10th percentage (0 to keep current): ");
                    double percentage = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    if (percentage != 0) {
                        student.setTenthPercentage(percentage);
                    }
                } else if (choice == 2) {
                    System.out.print("Enter new 12th school name (press Enter to keep current): ");
                    String schoolName = scanner.nextLine();
                    if (!schoolName.isEmpty()) {
                        student.setTwelfthSchoolName(schoolName);
                    }
                    
                    System.out.print("Enter new 12th board (press Enter to keep current): ");
                    String board = scanner.nextLine();
                    if (!board.isEmpty()) {
                        student.setTwelfthBoard(board);
                    }
                    
                    System.out.print("Enter new 12th year of passing (0 to keep current): ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (year != 0) {
                        student.setTwelfthYearOfPassing(year);
                    }
                    
                    System.out.print("Enter new 12th percentage (0 to keep current): ");
                    double percentage = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    if (percentage != 0) {
                        student.setTwelfthPercentage(percentage);
                    }
                } else {
                    System.out.println("Invalid choice!");
                    return;
                }
                
                System.out.println("Educational details updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Update parent details
    public void updateParentDetails() {
        System.out.println("\n=== Update Parent Details ===");
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Student student : students) {
            if (student.getId() == id) {
                System.out.print("Enter new father's name (press Enter to keep current): ");
                String fatherName = scanner.nextLine();
                if (!fatherName.isEmpty()) {
                    student.setFatherName(fatherName);
                }
                
                System.out.print("Enter new mother's name (press Enter to keep current): ");
                String motherName = scanner.nextLine();
                if (!motherName.isEmpty()) {
                    student.setMotherName(motherName);
                }
                
                System.out.print("Enter new parent's email (press Enter to keep current): ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) {
                    student.setParentEmail(email);
                }
                
                System.out.print("Enter new parent's phone (press Enter to keep current): ");
                String phone = scanner.nextLine();
                if (!phone.isEmpty()) {
                    student.setParentPhone(phone);
                }
                
                System.out.print("Enter new parent's occupation (press Enter to keep current): ");
                String occupation = scanner.nextLine();
                if (!occupation.isEmpty()) {
                    student.setParentOccupation(occupation);
                }
                
                System.out.print("Enter new parent's address (press Enter to keep current): ");
                String address = scanner.nextLine();
                if (!address.isEmpty()) {
                    student.setParentAddress(address);
                }
                
                System.out.println("Parent details updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Delete a student
    public void deleteStudent() {
        System.out.println("\n=== Delete Student ===");
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        students.removeIf(student -> student.getId() == id);
        System.out.println("Student deleted successfully!");
    }

    // Search for students
    public void searchStudent() {
        System.out.println("\n=== Search Student ===");
        System.out.println("1. Exact Search");
        System.out.println("2. Partial Search");
        System.out.println("3. Semester/Year Search");
        System.out.println("4. Parent Search");
        System.out.print("Enter your choice (1-4): ");
        
        int searchType = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (searchType < 1 || searchType > 4) {
            System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            return;
        }

        String searchQuery;
        boolean found = false;

        if (searchType == 1) {
            // Exact Search
            System.out.println("\nExact Search Options:");
            System.out.println("1. Search by ID");
            System.out.print("Enter your choice (1): ");
            
            int exactChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (exactChoice == 1) {
                System.out.print("Enter student ID to search: ");
                searchQuery = scanner.nextLine();
                System.out.println(TABLE_HEADER);
                try {
                    int searchId = Integer.parseInt(searchQuery);
                    for (Student student : students) {
                        if (student.getId() == searchId) {
                            System.out.println(student);
                            student.displayDetailedInfo();
                            found = true;
                            break;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println(TABLE_FOOTER);
                    System.out.println("Invalid ID format! Please enter a number.");
                    return;
                }
            } else {
                System.out.println("Invalid choice!");
                return;
            }
        } else if (searchType == 2) {
            // Partial Search
            System.out.println("\nPartial Search Options:");
            System.out.println("1. Search by Course");
            System.out.println("2. Search by Name");
            System.out.print("Enter your choice (1-2): ");
            
            int partialChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (partialChoice == 1) {
                System.out.print("Enter course to search: ");
                searchQuery = scanner.nextLine().toLowerCase();
                System.out.println(TABLE_HEADER);
                for (Student student : students) {
                    if (student.getCourse().toLowerCase().contains(searchQuery)) {
                        System.out.println(student);
                        found = true;
                    }
                }
            } else if (partialChoice == 2) {
                System.out.print("Enter student name to search: ");
                searchQuery = scanner.nextLine().toLowerCase();
                System.out.println(TABLE_HEADER);
                for (Student student : students) {
                    if (student.getName().toLowerCase().contains(searchQuery)) {
                        System.out.println(student);
                        found = true;
                    }
                }
            } else {
                System.out.println("Invalid choice!");
                return;
            }
        } else if (searchType == 3) {
            // Semester/Year Search
            System.out.println("\nSemester/Year Search Options:");
            System.out.println("1. Search by Semester");
            System.out.println("2. Search by Year");
            System.out.println("3. Search by Semester and Year");
            System.out.print("Enter your choice (1-3): ");
            
            int semYearChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (semYearChoice == 1) {
                System.out.print("Enter semester to search (1-8): ");
                int searchSemester = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                System.out.println(TABLE_HEADER);
                for (Student student : students) {
                    if (student.getSemester() == searchSemester) {
                        System.out.println(student);
                        found = true;
                    }
                }
            } else if (semYearChoice == 2) {
                System.out.print("Enter year to search (1-4): ");
                int searchYear = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                System.out.println(TABLE_HEADER);
                for (Student student : students) {
                    if (student.getYear() == searchYear) {
                        System.out.println(student);
                        found = true;
                    }
                }
            } else if (semYearChoice == 3) {
                System.out.print("Enter semester to search (1-8): ");
                int searchSemester = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                System.out.print("Enter year to search (1-4): ");
                int searchYear = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                System.out.println(TABLE_HEADER);
                for (Student student : students) {
                    if (student.getSemester() == searchSemester && student.getYear() == searchYear) {
                        System.out.println(student);
                        found = true;
                    }
                }
            } else {
                System.out.println("Invalid choice!");
                return;
            }
        } else if (searchType == 4) {
            // Parent Search
            System.out.println("\nParent Search:");
            System.out.print("Enter student ID to search for parent details: ");
            try {
                int searchId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                for (Student student : students) {
                    if (student.getId() == searchId) {
                        System.out.println("\n=== Parent Details for Student ID: " + student.getId() + " ===");
                        System.out.println("Student Name: " + student.getName());
                        System.out.println("\nFather's Name: " + (student.getFatherName() != null ? student.getFatherName() : "Not provided"));
                        System.out.println("Mother's Name: " + (student.getMotherName() != null ? student.getMotherName() : "Not provided"));
                        System.out.println("Parent's Email: " + (student.getParentEmail() != null ? student.getParentEmail() : "Not provided"));
                        System.out.println("Parent's Phone: " + (student.getParentPhone() != null ? student.getParentPhone() : "Not provided"));
                        System.out.println("Parent's Occupation: " + (student.getParentOccupation() != null ? student.getParentOccupation() : "Not provided"));
                        System.out.println("Parent's Address: " + (student.getParentAddress() != null ? student.getParentAddress() : "Not provided"));
                        found = true;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid student ID.");
                return;
            }
        }

        if (searchType != 4) {
            System.out.println(TABLE_FOOTER);
        }
        
        if (!found) {
            System.out.println("No students found!");
        }
    }

    // Export student list to file
    public void exportToFile() {
        System.out.println("\n=== Export Students to File ===");
        System.out.println("1. Create new file");
        System.out.println("2. Append to existing file");
        System.out.print("Enter your choice (1-2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String filename;
        if (choice == 1) {
            System.out.print("Enter new filename to create (e.g., students.txt): ");
            filename = scanner.nextLine();
            // Add new filename to file_list.txt
            try (PrintWriter listWriter = new PrintWriter(new FileWriter("file_list.txt", true))) {
                listWriter.println(filename);
            } catch (IOException e) {
                System.out.println("Error updating file list: " + e.getMessage());
                return;
            }
        } else if (choice == 2) {
            // Read and display existing files
            try {
                java.io.File fileList = new java.io.File("file_list.txt");
                if (!fileList.exists()) {
                    System.out.println("No existing files found. Please create a new file.");
                    return;
                }
                
                System.out.println("\nExisting files:");
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("file_list.txt"));
                String line;
                int fileNum = 1;
                while ((line = reader.readLine()) != null) {
                    System.out.println(fileNum++ + ". " + line);
                }
                reader.close();
                
                System.out.print("\nEnter the number of the file to append to: ");
                int fileChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                // Get the selected filename
                reader = new java.io.BufferedReader(new java.io.FileReader("file_list.txt"));
                int currentNum = 1;
                filename = null;
                while ((line = reader.readLine()) != null) {
                    if (currentNum == fileChoice) {
                        filename = line;
                        break;
                    }
                    currentNum++;
                }
                reader.close();
                
                if (filename == null) {
                    System.out.println("Invalid file number!");
                    return;
                }
            } catch (IOException e) {
                System.out.println("Error reading file list: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, choice == 2))) {
            if (choice == 1) {
                writer.println("=== Student List ===");
                writer.println("ID\tName\tAge\tCourse\tEmail\tPhone\tEnrollment Date");
                writer.println("------------------------------------------------------------");
            }
            
            for (Student student : students) {
                writer.printf("%d\t%s\t%d\t%s\t%s\t%s\t%s\n",
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getCourse(),
                    student.getEmail(),
                    student.getPhoneNumber(),
                    student.getEnrollmentDate());
            }
            System.out.println("Student list " + (choice == 1 ? "exported" : "appended") + " successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error " + (choice == 1 ? "exporting to" : "appending to") + " file: " + e.getMessage());
        }
    }

    // Export student list to file with detailed information
    public void exportDetailedToFile() {
        System.out.println("\n=== Export Detailed Student Information to File ===");
        System.out.println("1. Create new file");
        System.out.println("2. Append to existing file");
        System.out.print("Enter your choice (1-2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String filename;
        if (choice == 1) {
            System.out.print("Enter new filename to create (e.g., detailed_students.txt): ");
            filename = scanner.nextLine();
            // Add new filename to file_list.txt
            try (PrintWriter listWriter = new PrintWriter(new FileWriter("file_list.txt", true))) {
                listWriter.println(filename);
            } catch (IOException e) {
                System.out.println("Error updating file list: " + e.getMessage());
                return;
            }
        } else if (choice == 2) {
            // Read and display existing files
            try {
                java.io.File fileList = new java.io.File("file_list.txt");
                if (!fileList.exists()) {
                    System.out.println("No existing files found. Please create a new file.");
                    return;
                }
                
                System.out.println("\nExisting files:");
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("file_list.txt"));
                String line;
                int fileNum = 1;
                while ((line = reader.readLine()) != null) {
                    System.out.println(fileNum++ + ". " + line);
                }
                reader.close();
                
                System.out.print("\nEnter the number of the file to append to: ");
                int fileChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                // Get the selected filename
                reader = new java.io.BufferedReader(new java.io.FileReader("file_list.txt"));
                int currentNum = 1;
                filename = null;
                while ((line = reader.readLine()) != null) {
                    if (currentNum == fileChoice) {
                        filename = line;
                        break;
                    }
                    currentNum++;
                }
                reader.close();
                
                if (filename == null) {
                    System.out.println("Invalid file number!");
                    return;
                }
            } catch (IOException e) {
                System.out.println("Error reading file list: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, choice == 2))) {
            if (choice == 1) {
                writer.println("=== Detailed Student List ===");
            }
            
            for (Student student : students) {
                writer.println("\n=== Student ID: " + student.getId() + " ===");
                writer.println("Name: " + student.getName());
                writer.println("Age: " + student.getAge());
                writer.println("Course: " + student.getCourse());
                writer.println("Email: " + student.getEmail());
                writer.println("Phone: " + student.getPhoneNumber());
                writer.println("Enrollment Date: " + student.getEnrollmentDate());
                
                writer.println("\n10th Marksheet Details:");
                writer.println("School Name: " + (student.getTenthSchoolName() != null ? student.getTenthSchoolName() : "Not provided"));
                writer.println("Board: " + (student.getTenthBoard() != null ? student.getTenthBoard() : "Not provided"));
                writer.println("Year of Passing: " + (student.getTenthYearOfPassing() > 0 ? student.getTenthYearOfPassing() : "Not provided"));
                writer.println("Percentage: " + (student.getTenthPercentage() > 0 ? student.getTenthPercentage() + "%" : "Not provided"));
                
                writer.println("\n12th Marksheet Details:");
                writer.println("School Name: " + (student.getTwelfthSchoolName() != null ? student.getTwelfthSchoolName() : "Not provided"));
                writer.println("Board: " + (student.getTwelfthBoard() != null ? student.getTwelfthBoard() : "Not provided"));
                writer.println("Year of Passing: " + (student.getTwelfthYearOfPassing() > 0 ? student.getTwelfthYearOfPassing() : "Not provided"));
                writer.println("Percentage: " + (student.getTwelfthPercentage() > 0 ? student.getTwelfthPercentage() + "%" : "Not provided"));
                
                writer.println("\nParent Details:");
                writer.println("Father's Name: " + (student.getFatherName() != null ? student.getFatherName() : "Not provided"));
                writer.println("Mother's Name: " + (student.getMotherName() != null ? student.getMotherName() : "Not provided"));
                writer.println("Parent's Email: " + (student.getParentEmail() != null ? student.getParentEmail() : "Not provided"));
                writer.println("Parent's Phone: " + (student.getParentPhone() != null ? student.getParentPhone() : "Not provided"));
                writer.println("Parent's Occupation: " + (student.getParentOccupation() != null ? student.getParentOccupation() : "Not provided"));
                writer.println("Parent's Address: " + (student.getParentAddress() != null ? student.getParentAddress() : "Not provided"));
                
                writer.println("\n----------------------------------------------------------------------");
            }
            System.out.println("Detailed student list " + (choice == 1 ? "exported" : "appended") + " successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error " + (choice == 1 ? "exporting to" : "appending to") + " file: " + e.getMessage());
        }
    }
    
    // Export students by course
    public void exportStudentsByCourse() {
        System.out.println("\n=== Export Students By Course ===");
        
        // Get unique courses
        List<String> uniqueCourses = new ArrayList<>();
        for (Student student : students) {
            String course = student.getCourse();
            if (!uniqueCourses.contains(course)) {
                uniqueCourses.add(course);
            }
        }
        
        if (uniqueCourses.isEmpty()) {
            System.out.println("No courses found in the system.");
            return;
        }
        
        System.out.println("Available courses:");
        for (int i = 0; i < uniqueCourses.size(); i++) {
            System.out.println((i + 1) + ". " + uniqueCourses.get(i));
        }
        
        System.out.println((uniqueCourses.size() + 1) + ". Export all courses to separate files");
        System.out.print("Enter your choice (1-" + (uniqueCourses.size() + 1) + "): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (choice < 1 || choice > uniqueCourses.size() + 1) {
            System.out.println("Invalid choice!");
            return;
        }
        
        if (choice <= uniqueCourses.size()) {
            // Export specific course
            String selectedCourse = uniqueCourses.get(choice - 1);
            exportCourseToFile(selectedCourse);
        } else {
            // Export all courses
            for (String course : uniqueCourses) {
                exportCourseToFile(course);
            }
            System.out.println("All courses exported successfully!");
        }
    }
    
    // Helper method to export a specific course to file
    private void exportCourseToFile(String course) {
        String filename = "course_" + course.replaceAll("\\s+", "_").toLowerCase() + ".txt";
        
        // Add filename to file_list.txt
        try (PrintWriter listWriter = new PrintWriter(new FileWriter("file_list.txt", true))) {
            listWriter.println(filename);
        } catch (IOException e) {
            System.out.println("Error updating file list: " + e.getMessage());
            return;
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("=== Students Enrolled in " + course + " ===");
            writer.println("ID\tName\tAge\tEmail\tPhone\tEnrollment Date");
            writer.println("------------------------------------------------------------");
            
            int count = 0;
            for (Student student : students) {
                if (student.getCourse().equals(course)) {
                    writer.printf("%d\t%s\t%d\t%s\t%s\t%s\n",
                        student.getId(),
                        student.getName(),
                        student.getAge(),
                        student.getEmail(),
                        student.getPhoneNumber(),
                        student.getEnrollmentDate());
                    count++;
                }
            }
            
            writer.println("------------------------------------------------------------");
            writer.println("Total Students: " + count);
            
            System.out.println("Course '" + course + "' exported successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error exporting course to file: " + e.getMessage());
        }
    }
    
    // Export parent details
    public void exportParentDetails() {
        System.out.println("\n=== Export Parent Details ===");
        
        String filename = "parent_details.txt";
        
        // Add filename to file_list.txt
        try (PrintWriter listWriter = new PrintWriter(new FileWriter("file_list.txt", true))) {
            listWriter.println(filename);
        } catch (IOException e) {
            System.out.println("Error updating file list: " + e.getMessage());
            return;
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("=== Parent Details for All Students ===");
            writer.println("Student ID\tStudent Name\tFather's Name\tMother's Name\tParent's Email\tParent's Phone\tOccupation\tAddress");
            writer.println("--------------------------------------------------------------------------------------");
            
            int count = 0;
            for (Student student : students) {
                // Only include students with at least some parent information
                if (student.getFatherName() != null || student.getMotherName() != null) {
                    writer.printf("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
                        student.getId(),
                        student.getName(),
                        student.getFatherName() != null ? student.getFatherName() : "Not provided",
                        student.getMotherName() != null ? student.getMotherName() : "Not provided",
                        student.getParentEmail() != null ? student.getParentEmail() : "Not provided",
                        student.getParentPhone() != null ? student.getParentPhone() : "Not provided",
                        student.getParentOccupation() != null ? student.getParentOccupation() : "Not provided",
                        student.getParentAddress() != null ? student.getParentAddress() : "Not provided");
                    count++;
                }
            }
            
            writer.println("--------------------------------------------------------------------------------------");
            writer.println("Total Parents: " + count);
            
            System.out.println("Parent details exported successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error exporting parent details to file: " + e.getMessage());
        }
    }

    // Track student attendance
    public void manageAttendance() {
        System.out.println("\n=== Attendance Management ===");
        System.out.println("1. Mark Attendance");
        System.out.println("2. View Attendance Record");
        System.out.print("Enter your choice (1-2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (choice == 1) {
            if (students.isEmpty()) {
                System.out.println("No students found in the system.");
                return;
            }
            
            System.out.println("\n=== Mark Attendance ===");
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            
            System.out.println("\nStudent List:");
            System.out.println("ID\tName\t\tCourse\t\tSemester\tYear");
            System.out.println("----------------------------------------------------------");
            
            for (Student student : students) {
                System.out.printf("%d\t%-20s\t%-15s\t%d\t\t%d\n", 
                    student.getId(), student.getName(), student.getCourse(), 
                    student.getSemester(), student.getYear());
            }
            
            System.out.println("\nMark attendance (Enter student ID followed by P for Present or A for Absent, one per line)");
            System.out.println("Enter 0 when finished");
            
            while (true) {
                System.out.print("Student ID (0 to finish): ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                if (id == 0) {
                    break;
                }
                
                System.out.print("Status (P/A): ");
                String status = scanner.nextLine().toUpperCase();
                
                if (!status.equals("P") && !status.equals("A")) {
                    System.out.println("Invalid status! Please enter P for Present or A for Absent.");
                    continue;
                }
                
                boolean studentFound = false;
                for (Student student : students) {
                    if (student.getId() == id) {
                        studentFound = true;
                        
                        // In a real system, this would save to a database or file
                        System.out.println("Attendance marked for " + student.getName() + ": " + 
                            (status.equals("P") ? "Present" : "Absent") + " on " + date);
                        break;
                    }
                }
                
                if (!studentFound) {
                    System.out.println("Student with ID " + id + " not found!");
                }
            }
            
            System.out.println("Attendance marking completed for " + date);
            
        } else if (choice == 2) {
            System.out.println("\n=== View Attendance Record ===");
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            boolean studentFound = false;
            for (Student student : students) {
                if (student.getId() == id) {
                    studentFound = true;
                    System.out.println("Attendance record for " + student.getName() + ":");
                    
                    // In a real system, this would fetch from a database or file
                    System.out.println("NOTE: This is a demonstration. In a full implementation, actual attendance records would be shown.");
                    System.out.println("Example attendance record:");
                    System.out.println("Date\t\tStatus");
                    System.out.println("------------------------");
                    System.out.println("2023-08-01\tPresent");
                    System.out.println("2023-08-02\tPresent");
                    System.out.println("2023-08-03\tAbsent");
                    System.out.println("2023-08-04\tPresent");
                    
                    break;
                }
            }
            
            if (!studentFound) {
                System.out.println("Student with ID " + id + " not found!");
            }
        } else {
            System.out.println("Invalid choice!");
        }
    }
    
    // Manage student fees
    public void manageFees() {
        System.out.println("\n=== Fee Management ===");
        System.out.println("1. Pay Fees");
        System.out.println("2. View Fee Status");
        System.out.println("3. Generate Fee Receipt");
        System.out.print("Enter your choice (1-3): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (choice == 1) {
            System.out.println("\n=== Pay Fees ===");
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            boolean studentFound = false;
            for (Student student : students) {
                if (student.getId() == id) {
                    studentFound = true;
                    
                    System.out.println("Student: " + student.getName());
                    System.out.println("Course: " + student.getCourse());
                    System.out.println("Semester: " + student.getSemester());
                    System.out.println("Year: " + student.getYear());
                    
                    // In a real system, fee structure would be fetched from a database
                    double tuitionFee = 5000.0;
                    double libraryFee = 500.0;
                    double examFee = 1000.0;
                    double totalFee = tuitionFee + libraryFee + examFee;
                    
                    System.out.println("\nFee Details:");
                    System.out.println("Tuition Fee: $" + tuitionFee);
                    System.out.println("Library Fee: $" + libraryFee);
                    System.out.println("Examination Fee: $" + examFee);
                    System.out.println("-------------------------");
                    System.out.println("Total Fee: $" + totalFee);
                    
                    System.out.print("\nEnter payment amount: $");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    
                    if (amount <= 0) {
                        System.out.println("Invalid amount! Payment amount must be greater than zero.");
                        return;
                    }
                    
                    System.out.print("Enter payment method (Cash/Card/Bank Transfer): ");
                    String method = scanner.nextLine();
                    
                    // Generate a receipt number
                    String receiptNumber = "RCPT" + System.currentTimeMillis();
                    
                    System.out.println("\nPayment Successful!");
                    System.out.println("Receipt Number: " + receiptNumber);
                    System.out.println("Amount Paid: $" + amount);
                    System.out.println("Payment Method: " + method);
                    System.out.println("Date: " + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
                    
                    if (amount < totalFee) {
                        System.out.println("Remaining Balance: $" + (totalFee - amount));
                    } else if (amount > totalFee) {
                        System.out.println("Overpayment: $" + (amount - totalFee));
                    }
                    
                    break;
                }
            }
            
            if (!studentFound) {
                System.out.println("Student with ID " + id + " not found!");
            }
            
        } else if (choice == 2) {
            System.out.println("\n=== View Fee Status ===");
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            boolean studentFound = false;
            for (Student student : students) {
                if (student.getId() == id) {
                    studentFound = true;
                    
                    System.out.println("Fee Status for " + student.getName() + ":");
                    
                    // In a real system, this would fetch from a database
                    System.out.println("NOTE: This is a demonstration. In a full implementation, actual fee records would be shown.");
                    System.out.println("Example fee status:");
                    System.out.println("Semester\tTotal Fee\tPaid\tBalance\tStatus");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("1\t\t$6500.00\t$6500.00\t$0.00\tPaid");
                    System.out.println("2\t\t$6500.00\t$4000.00\t$2500.00\tPartial");
                    
                    break;
                }
            }
            
            if (!studentFound) {
                System.out.println("Student with ID " + id + " not found!");
            }
            
        } else if (choice == 3) {
            System.out.println("\n=== Generate Fee Receipt ===");
            System.out.print("Enter receipt number (e.g. RCPT1234567890): ");
            String receiptNumber = scanner.nextLine();
            
            // In a real system, this would fetch from a database
            System.out.println("\n=== FEE RECEIPT ===");
            System.out.println("Receipt Number: " + receiptNumber);
            System.out.println("Date: " + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
            System.out.println("Student: John Doe (Example)");
            System.out.println("Student ID: 12345");
            System.out.println("Course: Computer Science");
            System.out.println("Semester: 2");
            System.out.println("Year: 1");
            System.out.println("\nFee Details:");
            System.out.println("Tuition Fee: $5000.00");
            System.out.println("Library Fee: $500.00");
            System.out.println("Examination Fee: $1000.00");
            System.out.println("-------------------------");
            System.out.println("Total Fee: $6500.00");
            System.out.println("Amount Paid: $4000.00");
            System.out.println("Remaining Balance: $2500.00");
            System.out.println("Payment Method: Bank Transfer");
            System.out.println("\nAuthorized Signature: __________________");
            
            System.out.println("\nReceipt generated successfully!");
            
        } else {
            System.out.println("Invalid choice!");
        }
    }
    
    // Generate academic reports
    public void generateReports() {
        System.out.println("\n=== Academic Reports ===");
        System.out.println("1. Course Enrollment Summary");
        System.out.println("2. Student Performance Analysis");
        System.out.println("3. Admission Statistics");
        System.out.print("Enter your choice (1-3): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (choice == 1) {
            System.out.println("\n=== Course Enrollment Summary ===");
            
            // Get unique courses and count students in each
            java.util.Map<String, Integer> courseCount = new java.util.HashMap<>();
            for (Student student : students) {
                String course = student.getCourse();
                courseCount.put(course, courseCount.getOrDefault(course, 0) + 1);
            }
            
            if (courseCount.isEmpty()) {
                System.out.println("No courses found in the system.");
                return;
            }
            
            System.out.println("Course\t\t\tNumber of Students");
            System.out.println("------------------------------------");
            
            for (java.util.Map.Entry<String, Integer> entry : courseCount.entrySet()) {
                System.out.printf("%-20s\t%d\n", entry.getKey(), entry.getValue());
            }
            
            System.out.println("------------------------------------");
            System.out.println("Total Courses: " + courseCount.size());
            System.out.println("Total Students: " + students.size());
            
        } else if (choice == 2) {
            System.out.println("\n=== Student Performance Analysis ===");
            
            // Calculate average 10th and 12th percentages
            double totalTenthPercent = 0;
            double totalTwelfthPercent = 0;
            int tenthCount = 0;
            int twelfthCount = 0;
            
            for (Student student : students) {
                if (student.getTenthPercentage() > 0) {
                    totalTenthPercent += student.getTenthPercentage();
                    tenthCount++;
                }
                
                if (student.getTwelfthPercentage() > 0) {
                    totalTwelfthPercent += student.getTwelfthPercentage();
                    twelfthCount++;
                }
            }
            
            double avgTenthPercent = tenthCount > 0 ? totalTenthPercent / tenthCount : 0;
            double avgTwelfthPercent = twelfthCount > 0 ? totalTwelfthPercent / twelfthCount : 0;
            
            System.out.println("Performance Metrics:");
            System.out.println("------------------------------------");
            System.out.println("Total Students: " + students.size());
            System.out.println("Students with 10th details: " + tenthCount);
            System.out.println("Students with 12th details: " + twelfthCount);
            System.out.printf("Average 10th Percentage: %.2f%%\n", avgTenthPercent);
            System.out.printf("Average 12th Percentage: %.2f%%\n", avgTwelfthPercent);
            
            // Count high performers
            int highPerformersTenth = 0;
            int highPerformersTwelfth = 0;
            int highPerformersBoth = 0;
            
            for (Student student : students) {
                boolean highTenth = student.getTenthPercentage() >= 90;
                boolean highTwelfth = student.getTwelfthPercentage() >= 90;
                
                if (highTenth) highPerformersTenth++;
                if (highTwelfth) highPerformersTwelfth++;
                if (highTenth && highTwelfth) highPerformersBoth++;
            }
            
            System.out.println("\nHigh Performers (>=90%):");
            System.out.println("In 10th: " + highPerformersTenth + " students");
            System.out.println("In 12th: " + highPerformersTwelfth + " students");
            System.out.println("In both 10th & 12th: " + highPerformersBoth + " students");
            
        } else if (choice == 3) {
            System.out.println("\n=== Admission Statistics ===");
            
            // Calculate statistics by year
            java.util.Map<Integer, Integer> yearCount = new java.util.HashMap<>();
            for (Student student : students) {
                int year = student.getYear();
                yearCount.put(year, yearCount.getOrDefault(year, 0) + 1);
            }
            
            if (yearCount.isEmpty()) {
                System.out.println("No students found in the system.");
                return;
            }
            
            System.out.println("Year-wise Student Distribution:");
            System.out.println("Year\tNumber of Students");
            System.out.println("------------------------");
            
            for (int year = 1; year <= 4; year++) {
                int count = yearCount.getOrDefault(year, 0);
                System.out.println(year + "\t" + count);
            }
            
            // Get current year admissions
            int currentYearStudents = 0;
            String currentYear = String.valueOf(LocalDate.now().getYear());
            
            for (Student student : students) {
                if (student.getEnrollmentDate().startsWith(currentYear)) {
                    currentYearStudents++;
                }
            }
            
            System.out.println("\nCurrent Year (" + currentYear + ") Admissions: " + currentYearStudents);
            
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // Schedule management
    public void manageSchedule() {
        System.out.println("\n=== Schedule Management ===");
        System.out.println("1. View Class Schedule");
        System.out.println("2. Create Exam Schedule");
        System.out.print("Enter your choice (1-2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (choice == 1) {
            System.out.println("\n=== View Class Schedule ===");
            System.out.println("Enter course name: ");
            String course = scanner.nextLine();
            
            System.out.println("Enter semester (1-8): ");
            int semester = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.println("\nClass Schedule for " + course + " - Semester " + semester + ":");
            System.out.println("NOTE: This is a demonstration. In a full implementation, actual schedules would be shown.");
            
            System.out.println("\nTime\t\tMonday\t\tTuesday\t\tWednesday\tThursday\tFriday");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("09:00-10:00\tMaths\t\tPhysics\t\tChemistry\tMaths\t\tPhysics");
            System.out.println("10:00-11:00\tChemistry\tMaths\t\tPhysics\t\tChemistry\tMaths");
            System.out.println("11:00-12:00\tPhysics\t\tChemistry\tMaths\t\tPhysics\t\tChemistry");
            System.out.println("12:00-01:00\tLunch\t\tLunch\t\tLunch\t\tLunch\t\tLunch");
            System.out.println("01:00-02:00\tComputer\tEnglish\t\tComputer\tEnglish\t\tComputer");
            System.out.println("02:00-03:00\tEnglish\t\tComputer\tEnglish\t\tComputer\tEnglish");
            
        } else if (choice == 2) {
            System.out.println("\n=== Create Exam Schedule ===");
            System.out.println("Enter course name: ");
            String course = scanner.nextLine();
            
            System.out.println("Enter semester (1-8): ");
            int semester = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.println("\nCreating Exam Schedule for " + course + " - Semester " + semester);
            System.out.println("Enter subject names and exam dates. Enter 'done' when finished.");
            
            java.util.List<String> subjects = new ArrayList<>();
            java.util.List<String> dates = new ArrayList<>();
            
            while (true) {
                System.out.print("Subject name (or 'done' to finish): ");
                String subject = scanner.nextLine();
                
                if (subject.equalsIgnoreCase("done")) {
                    break;
                }
                
                System.out.print("Exam date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                
                subjects.add(subject);
                dates.add(date);
                
                System.out.println("Added " + subject + " exam on " + date);
            }
            
            if (subjects.isEmpty()) {
                System.out.println("No subjects added to the exam schedule.");
                return;
            }
            
            System.out.println("\nExam Schedule for " + course + " - Semester " + semester + ":");
            System.out.println("Subject\t\t\tDate");
            System.out.println("-----------------------------------");
            
            for (int i = 0; i < subjects.size(); i++) {
                System.out.printf("%-20s\t%s\n", subjects.get(i), dates.get(i));
            }
            
            System.out.println("\nExam schedule created successfully!");
            
        } else {
            System.out.println("Invalid choice!");
        }
    }
} 