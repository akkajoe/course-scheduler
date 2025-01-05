import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anush
 */
public class ClassQueries {
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getClassSeats;
    private static ResultSet resultSet;
    
    public static void addClass(ClassEntry classEntry) {
        connection = DBConnection.getConnection();
        try {
            addClass = connection.prepareStatement("INSERT INTO app.classentry (semester, courseCode, seats) VALUES (?, ?, ?)");
            addClass.setString(1, classEntry.getSemester());
            addClass.setString(2, classEntry.getCourseCode());
            addClass.setInt(3, classEntry.getSeats());
            addClass.executeUpdate();
            connection.commit(); // Commit the transaction if auto-commit is disabled
            System.out.println("class added successfully");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }  
    
    public static ArrayList<String> getAllCourseCodes(String semester) {
    connection = DBConnection.getConnection();
    @SuppressWarnings("Convert2Diamond")
        ArrayList<String> courses = new ArrayList<String>();
        try
        {
            getAllCourseCodes = connection.prepareStatement("SELECT courseCode FROM app.classentry WHERE semester = ? ORDER BY courseCode");
            getAllCourseCodes.setString(1, semester);
            resultSet = getAllCourseCodes.executeQuery();
            
            while(resultSet.next())
            {
                courses.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courses;
}
    @SuppressWarnings("CallToPrintStackTrace")
    public static ArrayList<Integer> getClassSeats(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        ArrayList<Integer> classes = new ArrayList<>();

        try {
            getClassSeats = connection.prepareStatement(
                "SELECT seats FROM app.classentry WHERE semester = ? AND courseCode = ? ORDER BY seats"
            );
            getClassSeats.setString(1, semester);
            getClassSeats.setString(2, courseCode);

            // Execute the query
            resultSet = getClassSeats.executeQuery();

            // Add each seat count to the ArrayList
            while (resultSet.next()) {
                classes.add(resultSet.getInt("seats")); 
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return classes;
    }
    
    public static void updateSeatCount(String semester, String courseCode, int newSeatCount) {
    connection = DBConnection.getConnection();
    
    try {
        // Prepare the SQL statement to update the seat count
        PreparedStatement updateSeats = connection.prepareStatement(
            "UPDATE app.classentry SET seats = ? WHERE semester = ? AND courseCode = ?"
        );
        updateSeats.setInt(1, newSeatCount);
        updateSeats.setString(2, semester);
        updateSeats.setString(3, courseCode);
        
        // Execute the update
        updateSeats.executeUpdate();
        
        // Commit the transaction if auto-commit is disabled
        connection.commit();
        
        System.out.println("Seat count updated successfully.");
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
    }
}
}

