import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieDataDelete {
    public static void deleteData(Connection conn) {
        Statement stmt;
        try {
            stmt = conn.createStatement();

            String deleteQuery = "DELETE FROM movie";
            stmt.executeUpdate(deleteQuery);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
