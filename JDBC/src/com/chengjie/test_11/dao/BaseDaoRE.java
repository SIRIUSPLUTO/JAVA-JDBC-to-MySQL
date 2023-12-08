package com.chengjie.test_11.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dao:DataBase Access Object
 *
 * 操作数据库的底层类
 * 1.更新操作，包含insert、update、delete
 * 2.查询操作，为select
 */
public class BaseDaoRE {
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
     * 自动封装:把ResultSet结果集中记录，自动提取并封装，放入list
     * @param sql
     * @param cls
     * @param params
     * @return
     * @param <T>
     */
    public <T> List<T> query(String sql,Class<T> cls,Object...params){
        rst = query(sql, params);
        //最终目标
        List<T> list = new ArrayList<>();
        
        try {
            //元数据
            ResultSetMetaData metaData = rst.getMetaData();
            //列数
            int colsnum = metaData.getColumnCount();
            while (rst.next()) {
                //实例化实体类
                T obj = cls.getDeclaredConstructor().newInstance();
                for (int i = 0; i < colsnum; i++) {
                    try {
                        //列名
                        String colsname = metaData.getColumnLabel(i + 1);
                        //根据列名取出当前列的值
                        Object val = rst.getObject(colsname);
                        //使用属性名拼接出对应的setter方法的名字
                        String setterName = "set" + colsname.substring(0, 1).toUpperCase() + colsname.substring(1);
                        //反射获取属性对象
                        Field prop = cls.getDeclaredField(colsname);
                        Class<?> type = prop.getType();

                        if (val.getClass() == LocalDateTime.class) {
                            LocalDateTime dateTime = (LocalDateTime) val;
                            dateTime.
                        }
                        //反射出对应的方法对象
                        Method setterMethod = cls.getDeclaredMethod(setterName, type);
                        //执行方法
                        setterMethod.invoke(obj, val);
                    } catch (Exception ex){

                    }
                }
                //把封装好的对象放入list
                list.add(obj);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
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
