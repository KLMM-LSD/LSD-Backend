package DBLayer;

import entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

public class UserQueries {

    private DatabaseAccess access;
    private Connection connection;

    public void setUp() throws SQLException, ClassNotFoundException {
        if (access == null) {
            access = new DatabaseAccess();
        }
        if (connection == null) {
            try {
                connection = access.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(UserQueries.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Opret user i DB ud fra User objekt
    public void createUser(Users user) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users (userid, username, usertype, usertimestamp, userpassword, userabout) VALUES (?,?,?,?,?,?)");
        pstmt.setInt(1, user.getUserid());
        pstmt.setString(2, user.getUsername());
        pstmt.setString(3, user.getUsertype());
        pstmt.setLong(4, user.getUsertimestamp());
        pstmt.setString(5, user.getUserpassword());
        pstmt.setString(6, user.getUserabout());
        pstmt.execute();
    }

    // Get User object ud fra Id
    public Users getUser(int userid) throws ClassNotFoundException, SQLException {
        Users user = new Users(0L);
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM users WHERE userid = " + userid + ";";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            user.setUserid(rs.getInt(1));
            user.setUsertype(rs.getString(2));
            user.setUsername(rs.getString(4));
            user.setUserpassword(rs.getString(5));
            user.setUserabout(rs.getString(6));
        }
        return user;
    }

    // Indtil videre kan man update password, hvis nødvendigt. Den kan udvides til, at kunne opdatere flere felter. 
    public void updateUser(int userid, String password) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("UPDATE Users SET userpassword = ? WHERE userid = " + userid + ";");
        pstmt.setString(1, password);
        pstmt.execute();
    }

    // Deletes user baseret på givet ID
    public void deleteUser(int userid) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Users WHERE userid = ?");
        pstmt.setInt(1, userid);
        pstmt.execute();
    }

    public int getUserIdByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "SELECT userid FROM users WHERE username = " + name + ";";
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return rs.getInt("userid");
        } else {
            return -1; // Username eksisterer ikke i DB. 
        }
    }

    public int sumOfUsers() throws SQLException {
        int count = 0;
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM users;";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            count++;
        }
        return count;
    }

}
