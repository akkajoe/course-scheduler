import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement getAllClassDescriptions;
    private static ResultSet resultSet;
    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester) {
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> classDescriptions = new ArrayList<>();
        try {   
            String sql = "SELECT c.courseCode, c.description, cl.semester, cl.seats "
                       + "FROM app.courseEntry c JOIN app.classEntry cl "
                       + "ON c.courseCode = cl.courseCode "
                       + "WHERE cl.semester = ?";
            getAllClassDescriptions = connection.prepareStatement(sql);
            getAllClassDescriptions.setString(1, semester);
            resultSet = getAllClassDescriptions.executeQuery();

            while(resultSet.next()) {
                String courseCode = resultSet.getString("courseCode");
                String courseDescription = resultSet.getString("description");
                int seats = resultSet.getInt("seats");

                ClassDescription classDescription = new ClassDescription(courseCode, courseDescription, seats);
                classDescriptions.add(classDescription);
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return classDescriptions;
    }
}
