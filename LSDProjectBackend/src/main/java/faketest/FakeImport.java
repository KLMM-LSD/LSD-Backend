/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faketest;

import DBLayer.UserQueries;
import entities.User;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Lasse
 */
public class FakeImport {
    
    /* phyxx was there twice, removed */

    public static void main(String[] args) throws IOException, SQLException {
        UserQueries uq = new UserQueries();
        Connection con = uq.getConnection();
        
        con.setAutoCommit(false);
        
        FileInputStream fstream = new FileInputStream("C:\\Users\\Lasse\\Desktop\\skolestest\\users.csv\\copy.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        long before, after;
        int i = 0;

        before = System.currentTimeMillis();

        String strLine;
        while ((strLine = br.readLine()) != null) {
            String[] split = strLine.split(",");
            User u = new User(-1, "user", split[0], split[1]);

            /* uq.insertUser(u); */
            uq.insertUserWithConnection(con, u);
            System.out.println(i++);
            
            if (i % 10000 == 0)
                con.commit();
        }
        br.close();
        con.commit();
        con.close();

        after = System.currentTimeMillis();

        System.out.println((after - before) + " ms to insert");

        //System.out.printf("Time taken: %ld ms\n", (after - before));
    }
}
