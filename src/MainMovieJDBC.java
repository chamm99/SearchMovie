import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainMovieJDBC {
    public static void main(String[] args){
        Connection conn = null;
        Statement stmt;
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/movie?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "4423125");
            stmt = conn.createStatement();
            String filePath = "/Users/cham/documents/github/searchmovie/src/movie_data.txt";
            while (true) {
                System.out.print(
                        "+==========================================================+\n" +
                                "|                 (0) 종료                                  |\n" +
                                "                  (1) 릴레이션 생성 및 데이터 추가\n" +
                                "                  (2) 제목을 이용한 검색\n" +
                                "                  (3) 관객수를 이용한 검색\n" +
                                "|                 (4) 개봉일을 이용한 검색                      |\n" +
                                "+==========================================================+\n" +
                                "원하는 번호를 입력하시오.\n사용자 입력: "
                );

                int order = sc.nextInt();

                switch (order) {
                    case 0:
                        System.out.println("종료하는중...");
                        sc.close();
                        return;
                    case 1:
                        DropTable.dropMovieTable(conn);
                        CreateTable.createMovieTable(conn);
                        InsertData.insertMovieData(conn, filePath);
                        stmt.close();
                        break;
                    case 2:
                        SearchMovie.searchByKeyword(conn);
                        stmt.close();
                        break;
                    case 3:
                        SearchMovie.searchByTotalNum(conn);
                        stmt.close();
                        break;
                    case 4:
                        SearchMovie.searchByReleaseDate(conn);
                        stmt.close();
                        break;
                    default:
                        System.out.println("유효한 번호를 입력하세요. (0~4 입력)");
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
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
