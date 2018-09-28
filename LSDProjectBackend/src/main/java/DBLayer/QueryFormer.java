package DBLayer;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryFormer {

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

    public static void createUser(Connection con, String dbName, User user) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO user (userName, password) VALUES ( ?, ?);");
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getPassword());
        pstmt.execute();
    }
}
