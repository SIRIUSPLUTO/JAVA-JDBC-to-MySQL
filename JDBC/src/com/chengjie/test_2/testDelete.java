package com.chengjie.test_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 删除操作
 */
public class testDelete {
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Cj20040503@";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String sql = "delete from account where accid=2";
            int rows = statement.executeUpdate(sql);
            System.out.println(">>delete>>rows:"+rows);

            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
