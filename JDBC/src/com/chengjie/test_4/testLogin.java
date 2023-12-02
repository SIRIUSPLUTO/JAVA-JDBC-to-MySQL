package com.chengjie.test_4;

import com.chengjie.util.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC应用实例：登录逻辑的实现
 */
public class testLogin {
    public static void main(String[] args) {
        //username = "' or 1=1 or ''='" (sql注入|安全漏洞问题 1.实现非法登录，绕过验证 2.特殊字符处理)
        boolean result= login("cheng","123456");
        System.out.println(result);
    }

    /**
     * 验证用户名或者密码是否匹配
     * @param username
     * @param password
     */
    public static boolean login(String username,String password){
        String sql = "select accid from account where accname='"+username+"' and password='"+password+"'";
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
            if(resultSet.next()){
                System.out.println("===成功!===");
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtils.close(connection,statement,resultSet);
        }

        return false;
    }
}
