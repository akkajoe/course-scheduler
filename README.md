# Course Scheduler

This project is a Course Scheduler application that allows administrators to manage courses, students, and class schedules. Students can view available classes and their schedules.

## Classes

### ScheduleEntry
Represents an entry in the schedule.

```java
public class ScheduleEntry {
    private String semester;
    private String courseCode;
    private String studentID;
    private String status;
    private Timestamp timestamp;

    public ScheduleEntry(String semester, String courseCode, String studentID, String status, Timestamp timestamp) {
        // ...existing code...
    }

    // Getters for the fields
    public String getSemester() { /* ... */ }
    public String getCourseCode() { /* ... */ }
    public String getStudentID() { /* ... */ }
    public String getStatus() { /* ... */ }
    public Timestamp getTimestamp() { /* ... */ }
}
```

### MultiTableQueries
Handles queries involving multiple tables.

```java
public class MultiTableQueries {
    // ...existing code...

    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester) {
        // ...existing code...
    }
}
```

### MainFrame
The main GUI frame for the application.

```java
public class MainFrame extends javax.swing.JFrame {
    // ...existing code...

    // Methods to rebuild combo boxes
    public void rebuildSemesterComboBoxes() { /* ... */ }
    public void rebuildAddCourseComboBox() { /* ... */ }
    public void rebuildSelectClassComboBox() { /* ... */ }
    public void rebuildSelectStudentComboBox() { /* ... */ }
    public void rebuilddisplayScheduleSelectStudentComboBox() { /* ... */ }

    // Methods to get selected student entry and ID
    public StudentEntry getSelectedStudentEntry() { /* ... */ }
    public String getSelectedStudentID() { /* ... */ }

    // Method to get schedule status
    public String getScheduleStatus(String currentSemester, String courseCode) { /* ... */ }

    // Event handlers for buttons
    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) { /* ... */ }
    private void addSemesterSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) { /* ... */ }
    private void addCourseSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) { /* ... */ }
    private void addStudentSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) { /* ... */ }
    private void addClassSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) { /* ... */ }
    private void displayClassesButtonActionPerformed(java.awt.event.ActionEvent evt) { /* ... */ }
    private void changeSemesterButtonActionPerformed(java.awt.event.ActionEvent evt) { /* ... */ }
    private void scheduleClassSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) { /* ... */ }
    private void displayStudentScheduleButtonActionPerformed(java.awt.event.ActionEvent evt) { /* ... */ }

    // Method to check data
    private void checkData() { /* ... */ }
}
```

### DBConnection
Handles the database connection.

```java
public class DBConnection {
    private static Connection connection;
    private static final String user = "java";
    private static final String password = "java";
    private static final String database = "jdbc:derby://localhost:1527/CourseSchedulerAnushckaJoshi996315202";

    public static Connection getConnection() {
        // ...existing code...
    }
}
```

### CourseQueries
Handles queries related to courses.

```java
public class CourseQueries {
    // ...existing code...

    public static void addCourse(CourseEntry course) {
        // ...existing code...
    }

    public static ArrayList<String> getAllCourseCodes() {
        // ...existing code...
    }
}
```

### CourseEntry
Represents a course entry.

```java
public class CourseEntry {
    private String courseCode;
    private String description;

    public CourseEntry(String courseCode, String description) {
        // ...existing code...
    }

    // Getters for the fields
    public String getCourseCode() { /* ... */ }
    public String getDescription() { /* ... */ }
}
```

### ClassQueries
Handles queries related to classes.

```java
public class ClassQueries {
    // ...existing code...

    public static void addClass(ClassEntry classEntry) {
        // ...existing code...
    }

    public static ArrayList<String> getAllCourseCodes(String semester) {
        // ...existing code...
    }

    public static ArrayList<Integer> getClassSeats(String semester, String courseCode) {
        // ...existing code...
    }

    public static void updateSeatCount(String semester, String courseCode, int newSeatCount) {
        // ...existing code...
    }
}
```

### ClassEntry
Represents a class entry.

```java
public class ClassEntry {
    private String semester;
    private String courseCode;
    private int seats;

    public ClassEntry(String semester, String courseCode, int seats) {
        // ...existing code...
    }

    // Getters for the fields
    public String getSemester() { /* ... */ }
    public String getCourseCode() { /* ... */ }
    public int getSeats() { /* ... */ }
}
```

### ClassDescription
Represents a class description.

```java
public class ClassDescription {
    private String courseCode;
    private String description;
    private int seats;

    public ClassDescription(String courseCode, String description, int seats) {
        // ...existing code...
    }

    // Getters for the fields
    public String getCourseCode() { /* ... */ }
    public String getDescription() { /* ... */ }
    public int getSeats() { /* ... */ }
}
```

## How to Run

1. Ensure you have a Java Development Kit (JDK) installed.
2. Set up a Derby database with the necessary tables.
3. Compile and run the `MainFrame` class to start the application.

```sh
javac MainFrame.java
java MainFrame
```
