package DBLayer;

import entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryFormer {

    private static final DatabaseAccess access = new DatabaseAccess();
    private static final Connection connection = access.getConnection();

    // Eksemple på at lave queries for User tabellen
    public static void viewUserTable(Connection con, String dbName) throws SQLException {
        Statement stmt = null;
        String query = "QUERY_EXAMPLE";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String userName = rs.getString("USER_NAME");
                //....
                System.out.println(userName + "\t");
            }
        } catch (SQLException e) {
            System.out.println("Got an SQLException: " + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void createUser(Connection con, String dbName, Users user) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO user (username, usertype ,userpassword) VALUES ( ?,?,?);");
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getUsertype());
        pstmt.setString(3, user.getUserpassword());
        pstmt.execute();
    }

    public static void main(String[] args) throws SQLException {
        Users user = new Users("user", "Hans", "123");
        createUser(connection, "lsd", user);
    }
}
