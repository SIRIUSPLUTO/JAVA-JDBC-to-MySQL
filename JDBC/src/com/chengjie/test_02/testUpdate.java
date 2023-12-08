package com.chengjie.test_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * DML：insert update delete 更新操作---executeUpdate()
 * DQL：select               查询操作---executeQuery()
 * Statement
 * 目标：记录更新
 */
public class testUpdate {
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Cj20040503@";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String sql = "update account set accname='jie',password='666666'where accid=2";
            int rows = statement.executeUpdate(sql);
            System.out.println(">>update>>rows:"+rows);

            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
