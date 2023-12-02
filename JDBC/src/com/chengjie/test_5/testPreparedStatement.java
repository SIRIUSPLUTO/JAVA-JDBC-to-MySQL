package com.chengjie.test_5;

import com.chengjie.util.ConnectionUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 * 演示：PreparedStatement用法
 * 预处理
 * 1.防止sql注入
 * 2.特殊字符处理
 * 3.提高执行效率
 */
public class testPreparedStatement {
    public static void main(String[] args) {
        //testInsert();
        //testUpdate();
        //testDelete();
    }

    /**
     * 使用预处理插入记录
     * sql中使用占位符|在执行sql之前给占位符赋值
     */
    public static void testInsert(){
        String sql = "insert into account(accname,password,balance,accdate,state)" +
                    "values(?,?,?,?,?)";
        //获取连接
        Connection conn = ConnectionUtils.getConn();
        PreparedStatement pst = null;
        //创建PreparedStatement  注意：需要提前传入sql
        try {
            pst = conn.prepareStatement(sql); //预编译处理
            //在执行之前，先给占位符赋值
            pst.setString(1,"曹操");
            pst.setString(2,"12345678");
            pst.setFloat(3,2000f);
            pst.setDate(4,new Date(System.currentTimeMillis()));
            pst.setInt(5,1);
            //执行sql
            int rows = pst.executeUpdate();//注意：没有sql
            System.out.println(rows);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtils.close(conn,pst,null);
        }
    }

    /**
     * 修改
     */
    public static void testUpdate(){
        String sql = "update account set password=? where accid=?";
        //获取连接
        Connection conn = ConnectionUtils.getConn();
        PreparedStatement pst = null;
        //创建PreparedStatement  注意：需要提前传入sql
        try {
            pst = conn.prepareStatement(sql); //预编译处理
            //在执行之前，先给占位符赋值
            pst.setString(1,"999999");
            pst.setInt(2,3);
            //执行sql
            int rows = pst.executeUpdate();//注意：没有sql
            System.out.println(rows);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtils.close(conn,pst,null);
        }
    }

    /**
     * 删除
     */
    public static void testDelete(){
        String sql = "delete from account where accid=?";
        //获取连接
        Connection conn = ConnectionUtils.getConn();
        PreparedStatement pst = null;
        //创建PreparedStatement  注意：需要提前传入sql
        try {
            pst = conn.prepareStatement(sql); //预编译处理
            //在执行之前，先给占位符赋值
            pst.setInt(1,3);
            //执行sql
            int rows = pst.executeUpdate();//注意：没有sql
            System.out.println(rows);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtils.close(conn,pst,null);
        }
    }
}
