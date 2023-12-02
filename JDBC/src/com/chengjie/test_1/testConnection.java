package com.chengjie.test_1;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 目标：创建连接 打通连接即可
 * 四个参数：1.driver 2.url 3.username 4.password
 * 五个步骤：1.加载驱动 2.创建连接 3.创建statement 4.执行sql 5.关闭，释放资源
 */
public class testConnection {
    public static void main(String[] args) {
        //mysql5写法:com.mysql.jdbc.Driver
        //mysql8写法:com.mysql.cj.jdbc.Driver
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Cj20040503@";

        try {
            //加载驱动
            Class.forName(driver);
            //创建连接
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("连接成功!"+connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
