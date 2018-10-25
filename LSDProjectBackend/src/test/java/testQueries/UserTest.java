/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testQueries;

import DBLayer.UserQueries;
import entities.User;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Lasse
 */
public class UserTestIT {

    @Test
    public void insertUser() throws SQLException {
        UserQueries uq = new UserQueries();
        int before = uq.countUsers();
        int after;
        
        User lookup;
        User u1 = new User(1, "user", "poobread", "abcdef", "I am a user");
        User u2 = new User(2, "user", "einstein", "dasdas", "I am clever");

        uq.insertUser(u1);
        uq.insertUser(u2);

        after = uq.countUsers();

        assertEquals(before + 2, after);
        
        lookup = uq.getUserByName("einstein");
        assertEquals(2, lookup.userid);
    }
}
