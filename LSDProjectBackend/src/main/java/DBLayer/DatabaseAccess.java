//package DBLayer;
//
//import com.mysql.cj.jdbc.MysqlDataSource;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import javax.naming.NamingException;
//
//public class DatabaseAccess {
//
//    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/lsd?zeroDateTimeBehaviour=convertToNull&serverTimezone=UTC";
//    private static final String AUTH_USERNAME = "root";
//    private static final String AUTH_PASSWORD = "root";
//
//    static MysqlDataSource dataSource;
//    static Connection conn;
//
//    public DatabaseAccess() throws SQLException {
//        conn = DriverManager.getConnection(CONNECTION_STRING, AUTH_USERNAME, AUTH_PASSWORD);
//    }
//
//    public static Connection getConnection() throws SQLException {
//        if (conn != null) {
//            return conn;
//        } else {
//            conn = dataSource.getConnection();
//            return conn;
//        }
//    }
//
//    /*
//    public static void main(String[] args) throws NamingException, SQLException {
//        Connection conn = null;
//        
//        conn = DriverManager.getConnection(CONNECTION_STRING, AUTH_USERNAME, AUTH_PASSWORD);
//        if (conn != null) {
//            System.out.println("Connected");
//        }
//    }
//     */
//    public static void main() {
//        System.out.println("hey");
//    }
//
//}
