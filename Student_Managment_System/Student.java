public class Student {
    private int id;
    private String name;
    private int age;
    private String course;
    private String email;
    private String phoneNumber;
    private String enrollmentDate;
    private int semester;
    private int year;
    
    // 10th marksheet details
    private String tenthSchoolName;
    private String tenthBoard;
    private int tenthYearOfPassing;
    private double tenthPercentage;
    
    // 12th marksheet details
    private String twelfthSchoolName;
    private String twelfthBoard;
    private int twelfthYearOfPassing;
    private double twelfthPercentage;
    
    // Parent details
    private String fatherName;
    private String motherName;
    private String parentEmail;
    private String parentPhone;
    private String parentOccupation;
    private String parentAddress;

    public Student(int id, String name, int age, String course, String email, String phoneNumber, String enrollmentDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.enrollmentDate = enrollmentDate;
        this.semester = 1; // Default for new students
        this.year = 1; // Default for new students
    }
    
    // Constructor with all fields
    public Student(int id, String name, int age, String course, String email, String phoneNumber, String enrollmentDate,
                  String tenthSchoolName, String tenthBoard, int tenthYearOfPassing, double tenthPercentage,
                  String twelfthSchoolName, String twelfthBoard, int twelfthYearOfPassing, double twelfthPercentage,
                  String fatherName, String motherName, String parentEmail, String parentPhone, 
                  String parentOccupation, String parentAddress, int semester, int year) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.enrollmentDate = enrollmentDate;
        this.tenthSchoolName = tenthSchoolName;
        this.tenthBoard = tenthBoard;
        this.tenthYearOfPassing = tenthYearOfPassing;
        this.tenthPercentage = tenthPercentage;
        this.twelfthSchoolName = twelfthSchoolName;
        this.twelfthBoard = twelfthBoard;
        this.twelfthYearOfPassing = twelfthYearOfPassing;
        this.twelfthPercentage = twelfthPercentage;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.parentEmail = parentEmail;
        this.parentPhone = parentPhone;
        this.parentOccupation = parentOccupation;
        this.parentAddress = parentAddress;
        this.semester = semester;
        this.year = year;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    // 10th marksheet getters and setters
    public String getTenthSchoolName() {
        return tenthSchoolName;
    }

    public void setTenthSchoolName(String tenthSchoolName) {
        this.tenthSchoolName = tenthSchoolName;
    }

    public String getTenthBoard() {
        return tenthBoard;
    }

    public void setTenthBoard(String tenthBoard) {
        this.tenthBoard = tenthBoard;
    }

    public int getTenthYearOfPassing() {
        return tenthYearOfPassing;
    }

    public void setTenthYearOfPassing(int tenthYearOfPassing) {
        this.tenthYearOfPassing = tenthYearOfPassing;
    }

    public double getTenthPercentage() {
        return tenthPercentage;
    }

    public void setTenthPercentage(double tenthPercentage) {
        this.tenthPercentage = tenthPercentage;
    }
    
    // 12th marksheet getters and setters
    public String getTwelfthSchoolName() {
        return twelfthSchoolName;
    }

    public void setTwelfthSchoolName(String twelfthSchoolName) {
        this.twelfthSchoolName = twelfthSchoolName;
    }

    public String getTwelfthBoard() {
        return twelfthBoard;
    }

    public void setTwelfthBoard(String twelfthBoard) {
        this.twelfthBoard = twelfthBoard;
    }

    public int getTwelfthYearOfPassing() {
        return twelfthYearOfPassing;
    }

    public void setTwelfthYearOfPassing(int twelfthYearOfPassing) {
        this.twelfthYearOfPassing = twelfthYearOfPassing;
    }

    public double getTwelfthPercentage() {
        return twelfthPercentage;
    }

    public void setTwelfthPercentage(double twelfthPercentage) {
        this.twelfthPercentage = twelfthPercentage;
    }
    
    // Parent details getters and setters
    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getParentOccupation() {
        return parentOccupation;
    }

    public void setParentOccupation(String parentOccupation) {
        this.parentOccupation = parentOccupation;
    }

    public String getParentAddress() {
        return parentAddress;
    }

    public void setParentAddress(String parentAddress) {
        this.parentAddress = parentAddress;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-20s | %-4d | %-15s | %-25s | %-15s | %-12s |",
            id, name, age, course, email, phoneNumber, enrollmentDate);
    }
    
    // Method to display detailed information including marksheets and parent details
    public void displayDetailedInfo() {
        System.out.println("\n=== Student Detailed Information ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course: " + course);
        System.out.println("Semester: " + semester);
        System.out.println("Year: " + year);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Enrollment Date: " + enrollmentDate);
        
        System.out.println("\n=== 10th Marksheet Details ===");
        System.out.println("School Name: " + (tenthSchoolName != null ? tenthSchoolName : "Not provided"));
        System.out.println("Board: " + (tenthBoard != null ? tenthBoard : "Not provided"));
        System.out.println("Year of Passing: " + (tenthYearOfPassing > 0 ? tenthYearOfPassing : "Not provided"));
        System.out.println("Percentage: " + (tenthPercentage > 0 ? tenthPercentage + "%" : "Not provided"));
        
        System.out.println("\n=== 12th Marksheet Details ===");
        System.out.println("School Name: " + (twelfthSchoolName != null ? twelfthSchoolName : "Not provided"));
        System.out.println("Board: " + (twelfthBoard != null ? twelfthBoard : "Not provided"));
        System.out.println("Year of Passing: " + (twelfthYearOfPassing > 0 ? twelfthYearOfPassing : "Not provided"));
        System.out.println("Percentage: " + (twelfthPercentage > 0 ? twelfthPercentage + "%" : "Not provided"));
        
        System.out.println("\n=== Parent Details ===");
        System.out.println("Father's Name: " + (fatherName != null ? fatherName : "Not provided"));
        System.out.println("Mother's Name: " + (motherName != null ? motherName : "Not provided"));
        System.out.println("Parent's Email: " + (parentEmail != null ? parentEmail : "Not provided"));
        System.out.println("Parent's Phone: " + (parentPhone != null ? parentPhone : "Not provided"));
        System.out.println("Parent's Occupation: " + (parentOccupation != null ? parentOccupation : "Not provided"));
        System.out.println("Parent's Address: " + (parentAddress != null ? parentAddress : "Not provided"));
    }
} 