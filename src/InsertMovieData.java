import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertMovieData {
    public static void insertMovieData(Connection conn, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.substring(1, line.length()).split("\\|");

                try {
                    int id = Integer.parseInt(data[0]);
                    String title = data[1];
                    String company = data[2];
                    Date releaseDate = Date.valueOf(data[3]);
                    String country = data[4];
                    int totalScreen = Integer.parseInt(data[5]);
                    BigDecimal profit = new BigDecimal(data[6]);
                    int totalNum = Integer.parseInt(data[7]);
                    String grade = data[8];

                    String sql = "insert into movie (id, title, company, releasedate, country, totalscreen, profit, totalnum, grade) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setInt(1, id);
                        pstmt.setString(2, title);
                        pstmt.setString(3, company);
                        pstmt.setDate(4, releaseDate);
                        pstmt.setString(5, country);
                        pstmt.setInt(6, totalScreen);
                        pstmt.setBigDecimal(7, profit);
                        pstmt.setInt(8, totalNum);
                        pstmt.setString(9, grade);
                        pstmt.executeUpdate();
                    }

                } catch (SQLException | NumberFormatException e) {
                    System.out.println("데이터 삽입 오류: "+e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: "+e.getMessage());
        }System.out.println("---------------------데이터 추가 완료---------------------------");
    }
}
