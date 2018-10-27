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

    private static final String INSERT_USER_QUERY = "INSERT INTO users (usertype, username, userpassword) VALUES (?, ?, ?)";
    private static final String COUNT_USERS_QUERY = "SELECT COUNT(*) FROM users";
    private static final String GET_USER_BY_NAME = "SELECT * FROM users WHERE username = ?";
    private static final String DELETE_USER_BY_NAME = "DELETE FROM users where username = ?";

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

            ret = new User(userid, usertype, username, userpassword);
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

        st.setString(1, u.usertype);
        st.setString(2, u.username);
        st.setString(3, u.userpassword);

        st.execute();

        con.close();
    }
    
    public void insertUserWithConnection(Connection con, User u) throws SQLException {
        PreparedStatement st = con.prepareStatement(INSERT_USER_QUERY);

        st.setString(1, u.usertype);
        st.setString(2, u.username);
        st.setString(3, u.userpassword);

        st.execute();
    }
    
    public Connection getConnection() throws SQLException {
        Connection ret = HikariCPDataSource.getConnection();
        return ret;
    }
    public void removeUser(String username) throws SQLException{
        Connection con = HikariCPDataSource.getConnection();
        PreparedStatement st = con.prepareStatement(DELETE_USER_BY_NAME);
        st.setString(1, username);
        st.execute();
    }

}
