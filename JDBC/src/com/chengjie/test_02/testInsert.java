package com.chengjie.test_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 目标：记录插入，入库
 * 准备：在mysql创建表
 * 步骤：1.加载驱动 2.connection 3.statement 4.执行sql 5.关闭，释放资源
 */
public class testInsert {
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Cj20040503@";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            //创建Statement:执行sql
            Statement statement = connection.createStatement();
            //执行sql
            String sql = "insert into account(accname,password,balance,accdate,state)" +
                    "values('cheng','123456',1000,now(),1)";
            //执行sql,返回影响的行数
            int rows = statement.executeUpdate(sql);
            System.out.println(">>>>rows:"+rows);

            //关闭，释放资源
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
