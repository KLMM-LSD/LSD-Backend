package DBLayer;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

public class DatabaseAccess {

    MysqlDataSource dataSource;
    Connection conn;

    public DatabaseAccess() {
        dataSource = new MysqlDataSource();
        dataSource.setUser("root"); //din MySQL user
        dataSource.setPassword("1234"); //din MySQL password
        dataSource.setServerName("jdbc:mysql://localhost/lsd");
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
        //servernavnet
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root"); //din MySQL user
        dataSource.setPassword("1234"); //din MySQL password
        dataSource.setServerName("jdbc:mysql://localhost/lsd");
    }

}

// Option 1 gammel måde at gøre det på
// måske sådan i stedet -> https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
//    public static void main(String[] args) {
//        String url = "";
//        String username = "";
//        String password = "";
//        
//        System.out.println("Connecting database...");
//        
//        try(Connection connection = DriverManager.getConnection(url, username, username)){
//            System.out.println("Database connected");
//        } catch (SQLException e) {
//            throw new IllegalStateException("Cannot connect the database!", e);
//        }
//    }
