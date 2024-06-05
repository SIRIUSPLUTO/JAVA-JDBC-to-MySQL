package com.chengjie.test_05;

import com.chengjie.util.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 使用预处理来实现登录逻辑，验证解决注入安全问题
 */
public class testLoginREtwo {
    public static void main(String[] args) {
        String username = "' or 1=1 or ''='";
        boolean result = login(username,"1234567890");
        System.out.println(result);
    }

    public static boolean login(String username,String password){
        //区别1：sql换成占位符
        String sql = "select * from account where accname=? and password=?";
        Connection connection = ConnectionUtils.getConn();

        try {
            //区别2：提前传入sql 进行预处理
            PreparedStatement pst = connection.prepareStatement(sql);
            //区别3：在执行前，进行占位符赋值
            //给占位符赋值
            pst.setString(1,username);
            pst.setString(2,password);

            //区别4：执行时，不再传入sql
            //执行 返回结果集
            ResultSet rst = pst.executeQuery();
            if (rst.next()){
                System.out.println("成功!");
                return true;
            }

            //ConnectionUtils.close(conn,pst,rst);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
