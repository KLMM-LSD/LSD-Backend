package DBLayer;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class DatabaseAccess {

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
    
    // Option 2 Nyere måde at gøre det på
    public static void main(String[] args) throws NamingException, SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(""); //din MySQL user
        dataSource.setPassword(""); //din MySQL password
        dataSource.setServerName(""); //servernavnet
    }
}
