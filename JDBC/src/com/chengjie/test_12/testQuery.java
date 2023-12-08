package com.chengjie.test_12;

import com.chengjie.test_11.entity.Account;
import com.chengjie.util.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * dbutils:查询
 */
public class testQuery {
    public static void main(String[] args) {
        //queryOne();
        //queryList();
    }

    /**
     * 查询列表
     */
    public static void queryList(){
        Connection connection = ConnectionUtils.getConn();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select accid,accname,password,accdate,state,balance from account account";

        BeanListHandler<Account> listHandler = new BeanListHandler<>(Account.class);
        try {
            List<Account> list = queryRunner.query(connection, sql, listHandler);
            for (Account account : list) {
                System.out.println(account);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtils.close(connection,null,null);
        }
    }

    public static void queryOne(){
        Connection connection = ConnectionUtils.getConn();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select accid,accname,password,accdate,state,balance from account where accid=?";

        //实例化BeanHandler,指定封装的实体类
        BeanHandler<Account> rsh = new BeanHandler<>(Account.class);
        try {
            Account account = queryRunner.query(connection,sql,rsh,3);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtils.close(connection,null,null);
        }
    }
}
