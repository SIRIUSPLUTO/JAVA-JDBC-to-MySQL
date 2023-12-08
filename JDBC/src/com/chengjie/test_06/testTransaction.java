package com.chengjie.test_06;

import com.chengjie.util.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 本案例演示事务控制
 * 事务简单来说就是要么全部执行，要么全部都不执行
 * 数据库事务四大特性，习惯性称为ACID特性
 * 1.原子性（Atomicity）:事务作为一个整体被执行，包含在其中的对数据库的操作要么全部执行，要么全部不执行
 * 2.一致性（Consistency）:事务应确保数据库的状态从一个一致状态转变为另一个一致状态。一致状态的含义:数据库中的数据应满足完整性约束
 * 3.隔离性（Isolation）:多个事务并发执行时，一个事务的执行不应影响其他事务的执行
 * 4.持久性（Durability）:已被提交的事务对数据库的修改应该永久保存在数据库中
 */
public class testTransaction {
    public static void main(String[] args) {
        String sql = "update account set balance=balance+? where accname=?";
        Connection conn = ConnectionUtils.getConn();
        PreparedStatement pst = null;
        try {
            //关闭自动提交
            conn.setAutoCommit(false);

            pst = conn.prepareStatement(sql);
            pst.setFloat(1, 500);
            pst.setString(2, "wang");
            int rows = pst.executeUpdate(); //不再自动提交事务

            //主动引发异常
            "".substring(12);

            //给zhang用户减去500
            pst.setFloat(1, -500);
            pst.setString(2, "zhang");
            int i = pst.executeUpdate();

            //提交事务
            conn.commit(); //手动提交事务
            System.out.println(rows + "===" + i);
        } catch (Exception e){
            try {
                //事务回滚
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            ConnectionUtils.close(conn,pst,null);
        }

    }
}
