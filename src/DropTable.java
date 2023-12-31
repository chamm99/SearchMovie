import java.sql.*;

public class DropTable {
    public static void dropMovieTable(Connection conn) {
        String dropMovie = "drop table if exists movie";
        try (PreparedStatement stmt = conn.prepareStatement(dropMovie)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("테이블 제거 오류: "+e.getMessage());
        }
    }
}