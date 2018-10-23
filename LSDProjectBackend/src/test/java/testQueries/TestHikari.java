///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package testQueries;
//
//import DBLayer.HikariCPDataSource;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import org.junit.Test;
//
///**
// *
// * @author Lasse
// */
//public class TestHikari {
//
//    @Test
//    public void whatever() throws SQLException {
//        Connection con = HikariCPDataSource.getConnection();
//        Statement stmt = con.createStatement();
//        String query = "SELECT * FROM users";
//        
//        ResultSet rs = stmt.executeQuery(query);
//        while (rs.next()) {
//            System.out.println(rs.getString("username"));
//        }
//    }
//}
