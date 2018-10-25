package DBLayer;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Lasse
 */
public class UserQueries {

    private static final String INSERT_USER_QUERY = "INSERT INTO users (userid, usertype, username, userpassword, userabout) VALUES (?, ?, ?, ?, ?)";
    private static final String COUNT_USERS_QUERY = "SELECT COUNT(*) FROM users";
    private static final String GET_USER_BY_NAME = "SELECT * FROM users WHERE username = ?";

    public User getUserByName(String name) throws SQLException {
        Connection con = HikariCPDataSource.getConnection();
        PreparedStatement st = con.prepareStatement(GET_USER_BY_NAME);
        
        st.setString(1, name);
        
        ResultSet rs = st.executeQuery();
        User ret = null;

        while (rs.next()) {
            int userid = rs.getInt("userid");
            String usertype = rs.getString("usertype");
            String username = rs.getString("username");
            String userpassword = rs.getString("userpassword");
            String userabout = rs.getString("userabout");

            ret = new User(userid, usertype, username, userpassword, userabout);
        }
        con.close();

        return ret;
    }

    public int countUsers() throws SQLException {
        Connection con = HikariCPDataSource.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(COUNT_USERS_QUERY);
        int ret = -1;

        while (rs.next()) {
            ret = rs.getInt(1);
        }

        con.close();
        return ret;
    }

    public void insertUser(User u) throws SQLException {
        Connection con = HikariCPDataSource.getConnection();
        PreparedStatement st = con.prepareStatement(INSERT_USER_QUERY);

        st.setInt(1, u.userid);
        st.setString(2, u.usertype);
        st.setString(3, u.username);
        st.setString(4, u.userpassword);
        st.setString(5, u.userabout);

        st.execute();

        con.close();
    }

}
