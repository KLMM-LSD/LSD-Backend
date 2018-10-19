/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testQueries;

import DBLayer.UserQueries;
import entities.Users;
import java.sql.SQLException;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Micha
 */
public class UserQueriesTest {

    private static UserQueries uq;
    private static Users testUser;
    private static Users testUser2;

    public UserQueriesTest() {

    }

    @BeforeClass
    public static void setUpClass() throws SQLException, ClassNotFoundException {
        uq = new UserQueries();
        uq.setUp();
        testUser = new Users(999, "user", System.currentTimeMillis(), "TestPerson", "testPWD", "Cool Guy");
        testUser2 = new Users(1000, "user", System.currentTimeMillis(), "TestPerson2", "testPWD", "Cool Guy");
        
        uq.deleteUser(999);
        uq.deleteUser(1000);
    }

    @Test
    public void CreateUserTest() throws SQLException {
        int size = uq.sumOfUsers();
        uq.createUser(testUser);
        System.out.println("First Name: " + testUser.getUsername());
        assertTrue(size < uq.sumOfUsers());
    }

    @Test
    public void DeleteUserTest() throws SQLException, ClassNotFoundException {
        uq.createUser(new Users(1000, "user", System.currentTimeMillis(), "TesterPerson", "testPWD", "Cool Guy"));
        testUser2 = uq.getUser(1000);
        assertTrue(testUser2.getUsername().equals("TesterPerson"));
        uq.deleteUser(1000);
        testUser2 = uq.getUser(1000);
        assertNull(testUser2.getUsername());
    }
}
