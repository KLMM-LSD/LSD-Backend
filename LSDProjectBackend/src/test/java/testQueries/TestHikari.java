/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testQueries;

import DBLayer.HikariCPDataSource;
import DBLayer.UserQueries;
import entities.Users;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Lasse
 */
public class TestHikari {

    @Test
    public void getsix() throws SQLException
    {
        UserQueries uq = new UserQueries();
        
        assertEquals(uq.countUsers(), 6);
    }
    
    @Test
    public void getPoobread() throws SQLException
    {
        UserQueries uq = new UserQueries();
        Users u = uq.getUserByName("poobread");
        Assert.assertNotNull(u);
        
        assertEquals(u.getUsername(), "poobread");
        assertTrue(u.getUserid() == 5);
        assertEquals(u.getUsertype(), "user");
    }
    
}
