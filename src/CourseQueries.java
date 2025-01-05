import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseQueries{

    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourseCodes;
    private static ResultSet resultSet;

    public static void addCourse(CourseEntry course) {
        connection = DBConnection.getConnection();
        try {
            System.out.println("Adding course: " + course.getCourseCode() + ", " + course.getDescription());
            addCourse = connection.prepareStatement("INSERT INTO app.courseentry (courseCode, description) VALUES (?, ?)");
            addCourse.setString(1, course.getCourseCode());
            addCourse.setString(2, course.getDescription());
            addCourse.executeUpdate();
            connection.commit(); // Commit the transaction if auto-commit is disabled
            System.out.println("Course added successfully: " + course.getCourseCode());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

   public static ArrayList<String> getAllCourseCodes() {
    connection = DBConnection.getConnection();
        ArrayList<String> courses = new ArrayList<String>();
        try
        {
            getAllCourseCodes = connection.prepareStatement("select courseCode from app.courseEntry order by courseCode");
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

}
