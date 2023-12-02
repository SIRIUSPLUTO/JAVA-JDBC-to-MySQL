package com.chengjie.test_4;

import com.chengjie.util.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testLoginREone {
    public static void main(String[] args) {
        //username = "' or 1=1 or ''='" (sql注入|安全漏洞问题 1.实现非法登录，绕过验证 2.特殊字符处理)
        String username = "cheng";
        boolean result= login(username,"123456");
        System.out.println(result);
    }

    /**
     * 验证用户名或者密码是否匹配
     * 解决方案：1.先通过用户名查密码，然后在和传入的密码匹配
     *         2.使用预处理
     * @param username
     * @param password
     */
    public static boolean login(String username,String password){
        //先通过用户名查密码,然后在匹配密码
        String sql = "select password from account where accname='"+username+"'";
        System.out.println(">>>>sql:"+sql);

        //获取连接
        Connection connection = ConnectionUtils.getConn();
        //创建Statement
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //判断是否有记录
            if(resultSet.next()){ //用户名存在
                //取出密码
                String passwd = resultSet.getString("password");
                if(passwd != null && passwd.equals(password)){
                    System.out.println("===成功!===");
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtils.close(connection,statement,resultSet);
        }

        return false;
    }
}

