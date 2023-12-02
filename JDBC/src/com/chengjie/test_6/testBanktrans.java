package com.chengjie.test_6;

import com.chengjie.util.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 本类实现:模拟银行转账业务逻辑
 */
public class testBanktrans {
    public static void main(String[] args) throws SQLException {
        String sql = "update account set balance=balance+? where accname=?";
        Connection conn = ConnectionUtils.getConn();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setFloat(1,500);
        pst.setString(2,"wang");
        int rows = pst.executeUpdate();

        //主动引发异常
        "".substring(12);

        //给zhang用户减去500
        pst.setFloat(1,-500);
        pst.setString(2,"zhang");
        int i = pst.executeUpdate();

        System.out.println(rows + "===" + i);
        ConnectionUtils.close(conn,pst,null);
    }
}
