/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;

/**
 *
 * @author Lasse
 */
public class TestConnection {

	private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/lsd?zeroDateTimeBehaviour=convertToNull&serverTimezone=UTC";
	private static final String AUTH_USERNAME = "root";
	private static final String AUTH_PASSWORD = "root";

	@Test
	public void whatever() throws SQLException {
		Connection con = DriverManager.getConnection(CONNECTION_STRING, AUTH_USERNAME, AUTH_PASSWORD);
		Statement stmt = con.createStatement();

		String query = "SELECT * FROM users";
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
			System.out.println(rs.getString("username"));
		}
		
		con.close();
	}
}
