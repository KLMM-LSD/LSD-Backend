package DBLayer;

import entities.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserQueries {

    /*
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
        PreparedStatement pstmt = DatabaseAccess.getConnection().prepareStatement("INSERT INTO users (userid, username, usertype, usertimestamp, userpassword, userabout) VALUES (?,?,?,?,?,?)");
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
     */
    
    public User getUserByName(String name) throws SQLException
    {
        Connection con = HikariCPDataSource.getConnection();
        String query = "SELECT * FROM users WHERE username = \"" + name + "\"";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        User ret = null;
        
        while (rs.next()) {
            Integer userid = rs.getInt("userid");
            String usertype = rs.getString("usertype");
            long usertimestamp = rs.getLong("usertimestamp");
            String username = rs.getString("username");
            String userpassword = rs.getString("userpassword");
            String userabout = rs.getString("userabout");
            ret = new User(userid, usertype, usertimestamp, username, userpassword, userabout);
        }
        
        con.close();
        return ret;
    }
    
    public int countUsers() throws SQLException {
        Connection con = HikariCPDataSource.getConnection();
        String query = "SELECT COUNT(*) FROM users";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        int ret = -1;

        while (rs.next()) {
            ret = rs.getInt(1);
        }

        con.close();
        return ret;
    }

}
