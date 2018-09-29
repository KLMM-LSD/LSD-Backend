package DBLayer;

import entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryFormer {

    private static DatabaseAccess access;
    private static Connection connection;

    public static void setUp() throws SQLException {
        if (access == null) {
            access = new DatabaseAccess();
        }
        if (connection == null) {
            connection = access.getConnection();
        }
    }

    public static void createUser(Connection con, String dbName, Users user) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO users (username, usertype, usertimestamp, userpassword) VALUES (?,?,?,?)");
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getUsertype());
        pstmt.setLong(3, user.getUsertimestamp());
        pstmt.setString(4, user.getUserpassword());
        pstmt.execute();
    }

    public static void main(String[] args) throws SQLException {
        setUp();
        Users user = new Users("user", 0l, "Hans", "123");
        createUser(connection, "lsd", user);
    }
}
