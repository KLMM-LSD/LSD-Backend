package DBLayer;

import entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserQueries {

    private DatabaseAccess access;
    private Connection connection;

    public void setUp() throws SQLException {
        if (access == null) {
            access = new DatabaseAccess();
        }
        if (connection == null) {
            connection = access.getConnection();
        }
    }

    public void createUser(Users user) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users (userid, username, usertype, usertimestamp, userpassword, userpassword) VALUES (?,?,?,?,?,?)");
        pstmt.setInt(1, user.getUserid());
        pstmt.setString(2, user.getUsername());
        pstmt.setString(3, user.getUsertype());
        pstmt.setLong(4, user.getUsertimestamp());
        pstmt.setString(5, user.getUserpassword());
        pstmt.setString(6, user.getUserabout());
        pstmt.execute();
    }

    public Users getUser(int userid) throws ClassNotFoundException, SQLException {
        Users user = new Users(0L);
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM users WHERE userid = " + userid + ";";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            user.setUserid(rs.getInt(1));
            user.setUsertype(rs.getString(2));
            user.setUsername(rs.getString(3));
            user.setUserpassword(rs.getString(4));
            user.setUserabout(rs.getString(5));
        }
        return user;
    }

    public void updateUser(int userid, String password) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("UPDATE Users SET userpassword = ? WHERE userid = " + userid + ";");
        pstmt.setString(1, password);
        pstmt.execute();
    }

    public void deleteUser(int userid) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Users WHERE userid = ?");
        pstmt.setInt(1, userid);
        pstmt.execute();
    }

}
