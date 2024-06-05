package com.chengjie.test_10;

import java.sql.*;
import java.util.Arrays;

/**
 * Dao:DataBase Access Object
 *
 * 操作数据库的底层类
 * 1.更新操作，包含insert、update、delete
 * 2.查询操作，为select
 */
public class BaseDao {
    private String username = "root";
    private String password = "Cj20040503@";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/test";

    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rst;

    private boolean isAutoCommit = true;

    //设置是否自动提交
    public void setAutoCommit(boolean autoCommit) {
        isAutoCommit = autoCommit;
    }

    /**
     * 提交的方法也是单独出来
     */
    public void commit(){
        try {
            if (connection != null && connection.isClosed()){
                connection.commit();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 创建连接
     * @return
     */
    public Connection getConnection(){
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(driver);
                connection = DriverManager.getConnection(url,username,password);
            }
            return connection;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行更新操作
     * @param sql
     * @return
     */
    public int update(String sql,Object... params){
        System.out.println("SQL:" + sql);
        System.out.println("Params:" + Arrays.toString(params));
        //连接、声明、执行、返回(执行行数)、关闭
        connection = getConnection();
        try {
            //设置是否自动提交
            connection.setAutoCommit(isAutoCommit);

            pst = connection.prepareStatement(sql);
            //给占位符赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }
            int i = pst.executeUpdate();
            return i;
        } catch (SQLException throwables) {
            if (isAutoCommit==false){
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throwables.printStackTrace();
        } finally {
            close();
        }
        return -1;
    }

    /**
     * 执行查询操作
     * @param sql
     * @return
     */
    public ResultSet query(String sql,Object... params){
        System.out.println("SQL:" + sql);
        System.out.println("Params:" + Arrays.toString(params));
        //连接、声明、执行、返回(结果集)、关闭
        connection = getConnection();
        try {
            pst = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i+1,params[i]);
                }
            }
            rst = pst.executeQuery();
            return rst;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //close();
        }
        return null;
    }

    /**
     * 关闭连接
     */
    public void close(){
        try{
            if (rst != null){
                rst.close();
                rst = null;
            }
            if (pst != null){
                pst.close();
                pst = null;
            }
            if (connection != null){
                connection.close();
                connection = null;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
