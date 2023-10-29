import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchMovie {
    public static void searchByKeyword() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("원하는 제목 키워드를 입력하시오.\n사용자 입력: ");
        String keyword = sc.nextLine();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/movie?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "0000");

            String query = "select * from movie where title like ? order by id";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + keyword + "%");

            rs = pstmt.executeQuery();
            System.out.println("제목에 <"+keyword+"> 포함된 영화에 대한 검색 결과...\n");

            while (rs.next()) {
                System.out.println("|"+rs.getInt("id") + "|" +
                        rs.getString("title") + "|" +
                        rs.getString("company") + "|" +
                        rs.getDate("releasedate") + "|" +
                        rs.getString("country") + "|\n" +
                        rs.getInt("totalscreen") + "|" +
                        rs.getBigDecimal("profit") + "|" +
                        rs.getInt("totalnum") + "|" +
                        rs.getString("grade") +"\n");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void searchByTotalNum() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("원하는 관객수를 입력하시오.\n사용자 입력: ");
        int keyword = sc.nextInt();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/movie?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "0000");

            String query = "select * from movie where totalnum > ? order by totalnum desc";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, keyword);

            rs = pstmt.executeQuery();
            System.out.println("관객수 <"+keyword+">명 이상인 영화에 대한 검색 결과...\n");

            while (rs.next()) {
                System.out.println("|"+rs.getInt("id") + "|" +
                        rs.getString("title") + "|" +
                        rs.getString("company") + "|" +
                        rs.getDate("releasedate") + "|" +
                        rs.getString("country") + "|\n" +
                        rs.getInt("totalscreen") + "|" +
                        rs.getBigDecimal("profit") + "|" +
                        rs.getInt("totalnum") + "|" +
                        rs.getString("grade")+"\n");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void searchByReleaseDate() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("특정 날짜 혹은 두 개의 날짜를 입력하시오(yyyy-mm-dd).\n사용자 입력: ");
        String date = sc.nextLine();
        String[] values = date.split(",");
        if (values.length == 1) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/movie?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "0000");

                String query = "select * from movie where releasedate = ? order by id";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, values[0]);

                rs = pstmt.executeQuery();
                System.out.println("개봉일 <" + values[0] + ">의 영화에 대한 검색 결과...\n");

                while (rs.next()) {
                    System.out.println("|"+rs.getInt("id") + "|" +
                            rs.getString("title") + "|" +
                            rs.getString("company") + "|" +
                            rs.getDate("releasedate") + "|" +
                            rs.getString("country") + "|\n" +
                            rs.getInt("totalscreen") + "|" +
                            rs.getBigDecimal("profit") + "|" +
                            rs.getInt("totalnum") + "|" +
                            rs.getString("grade")+"\n");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
     else if (values.length == 2) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/movie?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "0000");

            String query = "select * from movie where releasedate between ? and ? order by releasedate";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, values[0]);
            pstmt.setString(2, values[1]);

            rs = pstmt.executeQuery();
            System.out.println("개봉일 <" + values[0] + " ~ " + values[1] + ">의 영화에 대한 검색 결과...\n");

            while (rs.next()) {
                System.out.println("|"+rs.getInt("id") + "|" +
                        rs.getString("title") + "|" +
                        rs.getString("company") + "|" +
                        rs.getDate("releasedate") + "|" +
                        rs.getString("country") + "|\n" +
                        rs.getInt("totalscreen") + "|" +
                        rs.getBigDecimal("profit") + "|" +
                        rs.getInt("totalnum") + "|" +
                        rs.getString("grade")+"\n");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}}
