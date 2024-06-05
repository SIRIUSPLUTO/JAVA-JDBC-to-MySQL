package com.chengjie.test_12;

import com.chengjie.util.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * dbutils:开源组件,Apache下的轻量级小巧的工具包(组件)
 * 核心功能:查询结果集的封装,自动封装成指定的JavaBean
 */
public class testDbutils {
    public static void main(String[] args) {
        //update();
        //insert();
    }

    /**
     * 执行插入操作完成后，可以获取自动增长列主键值
     */
    public static void insert(){
        Connection connection = ConnectionUtils.getConn();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert account(accname,password,balance,accdate)values(?,?,?,?)";
        Object[] params = {"zf","996",5000,new Date()};

        ScalarHandler<BigInteger> handler = new ScalarHandler<>();
        try {
            //执行插入操作后，可以直接获取主键
            BigInteger id = queryRunner.insert(connection, sql, handler, params);
            System.out.println("主键" + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtils.close(connection,null,null);
        }
    }

    /**
     * dbutils执行更新操作
     */
    public static void update(){
        Connection connection = ConnectionUtils.getConn();
        QueryRunner queryRunner = new QueryRunner();
        //sql语句
        String sql = "insert account(accname,password,balance,accdate)values(?,?,?,?)";
        Object[] params = {"zf","996",5000,new Date()};
        //String sql = "update account set balance=balance-1000 where accid=?";
        //Object[] params = {6};
        //String sql = "delete from account where accid=?";
        //Object[] params = {6};

        try {
            int rows = queryRunner.update(connection, sql, params);
            System.out.println(rows);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtils.close(connection,null,null);
        }
    }
}
