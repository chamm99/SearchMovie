import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
    public static void createMovieTable(Connection conn) {
        String createTable = "create table movie (" +
                "id int," +
                "title varchar(100)," +
                "company varchar(50)," +
                "releasedate date," +
                "country varchar(10)," +
                "totalscreen int," +
                "profit numeric(15,2)," +
                "totalnum int," +
                "grade varchar(50)," +
                "primary key (id))";
        try (PreparedStatement stmt = conn.prepareStatement(createTable)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("테이블 생성 오류: "+e.getMessage());
        }System.out.println("---------------------테이블 생성 완료---------------------------");
    }
    public static void createActorTable(Connection conn) {
        String createTable = "create table movie (" +
                "id int," +
                "name varchar(100)," +
                "sex varchar(50)," +
                "birth date," +
                "country varchar(10)," +
                "totalscreen int," +
                "profit numeric(15,2)," +
                "totalnum int," +
                "grade varchar(50)," +
                "primary key (id))";
        try (PreparedStatement stmt = conn.prepareStatement(createTable)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("테이블 생성 오류: "+e.getMessage());
        }System.out.println("---------------------테이블 생성 완료---------------------------");
    }
}
