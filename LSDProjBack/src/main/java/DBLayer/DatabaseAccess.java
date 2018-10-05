package DBLayer;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.NamingException;

public class DatabaseAccess {

    MysqlDataSource dataSource;
    Connection conn;

    public DatabaseAccess() throws SQLException {
        String URL = "jdbc:mysql://localhost:3360/lsd?zeroDateTimeBehaviour=convertToNull&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        
        conn = DriverManager.getConnection(URL, user, password);
    }

    public Connection getConnection() throws SQLException {
        if (conn != null) {
            return conn;
        } else {
            conn  = dataSource.getConnection();
            return conn;
        }
    }

// Option 2 Nyere måde at gøre det på
    public static void main(String[] args) throws NamingException, SQLException {
        Connection conn = null;
        String URL = "jdbc:mysql://localhost:3360/lsd?zeroDateTimeBehaviour=convertToNull&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        
        conn = DriverManager.getConnection(URL, user, password);
        if (conn != null) {
            System.out.println("Connected");
        }
    }

}
