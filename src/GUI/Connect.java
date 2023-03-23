/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HP
 */
public class Connect {
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=CT276;user=sa;password=sa2008");
//            if(conn != null) {
//                System.out.println("Kết nối thành công");
//            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return conn;
    }
}
