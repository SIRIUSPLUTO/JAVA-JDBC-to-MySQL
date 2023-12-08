package com.chengjie.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 功能：创建连接和关闭连接
 */
public class ConnectionUtils {
    private static String username = "root";
    private static String password = "Cj20040503@";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/test";

    static {
        InputStream is = ConnectionUtils.class.getResourceAsStream("/DB.properties");
        Properties p = new Properties();
        try {
            p.load(is);

            System.out.println("--->"+p);
            url = p.getProperty("url");
            driver = p.getProperty("driver");
            username = p.getProperty("username");
            password = p.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     * @return
     */
    public static Connection getConn(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭连接，释放资源
     * @param conn
     * @param st
     * @param rst
     */
    public static void close (Connection conn, Statement st, ResultSet rst){
        if(rst != null){
            try {
                rst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }if(st != null){
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }if(conn != null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
