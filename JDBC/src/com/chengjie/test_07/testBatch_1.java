package com.chengjie.test_07;

import com.chengjie.util.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 本案例演示批处理:单条记录插入和批量处理方式插入性能对比
 * String sql = "insert into account(accname,password,balance,state,accdate)values(?,?,?,?,now())";
 */
public class testBatch_1 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); //开始时间

        //插入十万条记录
        //test_1(100000);
        //test_2(100000);

        long endTime = System.currentTimeMillis(); //结束时间

        System.out.println("耗时" + (endTime - startTime));
    }

    /**
    * count:插入的记录
    * 普通的单条插入
    */
    public static void test_1(int count){
        String sql = "insert into account(accname,password,balance,state,accdate)values(?,?,?,?,now())";
        Connection conn = ConnectionUtils.getConn();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            for(int i = 0;i < count;i++){
                pst.setString(1,"name"+i);
                pst.setString(2,"password"+i);
                pst.setFloat(3,1000);
                pst.setInt(4,1);
                pst.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtils.close(conn,pst,null);
        }
    }

    /**
     * count:插入的记录
     * 使用批处理的插入
     * 注意:内存溢出问题
     */
    public static void test_2(int count){
        String sql = "insert into account(accname,password,balance,state,accdate)values(?,?,?,?,now())";
        Connection conn = ConnectionUtils.getConn();
        PreparedStatement pst = null;

        try {
            //关闭自动提交
            conn.setAutoCommit(false);

            pst = conn.prepareStatement(sql);
            for(int i = 0;i < count;i++){
                pst.setString(1,"name"+i);
                pst.setString(2,"password"+i);
                pst.setFloat(3,1000);
                pst.setInt(4,1);
                //添加的记录先放入缓存
                pst.addBatch();
                //每6000次执行一次
                if ((i+1) % 6000 == 0){
                    pst.executeBatch();
                    conn.commit();
                }
            }
            //批量执行
            pst.executeBatch();
            //提交事务
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtils.close(conn,pst,null);
        }
    }
}






