import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;  

public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry scheduleEntry) {
        connection = DBConnection.getConnection();
        try {
            addScheduleEntry = connection.prepareStatement("INSERT INTO app.scheduleentry (semester, courseCode, studentID, status, timestamp) VALUES (?, ?, ?, ?, ?)");
            addScheduleEntry.setString(1, scheduleEntry.getSemester());
            addScheduleEntry.setString(2, scheduleEntry.getCourseCode());
            addScheduleEntry.setString(3, scheduleEntry.getStudentID());
            addScheduleEntry.setString(4, scheduleEntry.getStatus());
            addScheduleEntry.setTimestamp(5, scheduleEntry.getTimestamp());         
            addScheduleEntry.executeUpdate();
            connection.commit(); // Commit the transaction if auto-commit is disabled
            System.out.println("ScheduleEntry added successfully");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }  
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID) {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> scheduleEntries = new ArrayList<>();
        try {
            String sql = "SELECT semester, courseCode, studentID, status, timestamp FROM app.scheduleentry WHERE semester = ? AND studentID = ? ORDER BY timestamp";
            getScheduleByStudent = connection.prepareStatement(sql);
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            resultSet = getScheduleByStudent.executeQuery();
            while (resultSet.next()) {
                String courseCode = resultSet.getString("courseCode");
                String status = resultSet.getString("status");
                Timestamp timeStamp = resultSet.getTimestamp("timestamp");  // Use java.sql.Timestamp
                ScheduleEntry scheduleEntry = new ScheduleEntry(semester, courseCode, studentID, status, timeStamp);
                scheduleEntries.add(scheduleEntry);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return scheduleEntries;
    }
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode) {
        connection = DBConnection.getConnection();
        int studentCount = 0;
        try {
            // SQL query to count students for a specific course and semester
            String sql = "SELECT COUNT(*) AS studentCount FROM app.scheduleentry WHERE semester = ? AND courseCode = ?";
            getScheduledStudentCount = connection.prepareStatement(sql);
            getScheduledStudentCount.setString(1, currentSemester);
            getScheduledStudentCount.setString(2, courseCode);

            // Execute the query
            resultSet = getScheduledStudentCount.executeQuery();
            if (resultSet.next()) {
                // Retrieve the count from the result set
                studentCount = resultSet.getInt("studentCount");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (getScheduledStudentCount != null) getScheduledStudentCount.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentCount;
    }
}
