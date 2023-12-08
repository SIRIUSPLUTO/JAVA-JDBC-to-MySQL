package com.chengjie.test_03;

import java.sql.*;

/**
 * 目标：查询账户表中所有记录并打印输出
 * 结果集
 */
public class testSelect {
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Cj20040503@";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            //执行sql,返回结果集ResultSet
            String sql = "select accid,accname,password,accdate from account";
            ResultSet resultSet = statement.executeQuery(sql);
            //遍历结果集  默认游标指向第一行之前
            while (resultSet.next()){
                //获取当前游标指向的记录数据
                int accid = resultSet.getInt(1); //1表示列的位置，从1开始
                String accname = resultSet.getString(2);
                String accpasswd = resultSet.getString(3);
                Date date = resultSet.getDate(4);
                System.out.println(accid+"==="+accname+"==="+password+"==="+date);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

