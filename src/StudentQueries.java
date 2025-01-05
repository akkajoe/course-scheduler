/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author anush
 */
public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student) {
        connection = DBConnection.getConnection();
        try {           
            addStudent = connection.prepareStatement("INSERT INTO app.studententry (studentID, firstName, lastName) VALUES (?, ?, ?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
            connection.commit(); // Commit the transaction if auto-commit is disabled
            System.out.println("Student added successfully: " + student.getFirstName());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<StudentEntry> getAllStudents() {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try
        {
            getAllStudents = connection.prepareStatement("SELECT studentID, firstName, lastName FROM app.studentEntry ORDER BY studentID");
            resultSet = getAllStudents.executeQuery();

            while(resultSet.next())
            {
                String studentID = resultSet.getString("studentID");
                String lastName = resultSet.getString("lastName");
                String firstName = resultSet.getString("firstName");
                
                StudentEntry student = new StudentEntry(studentID, firstName, lastName);
                students.add(student);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;
    }
}

