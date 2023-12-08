package com.chengjie.test_10;

import com.chengjie.util.ConnectionUtils;

import java.sql.*;

/**
 * 结果集的元数据
 */
public class testResultset {
    public static void main(String[] args) {
        String sql = "select * from account";
        //String sql = "select accid,accname as name,password from account"; 最后获得的还是accname
        query(sql);
    }

    public static void query(String sql){
        Connection conn = ConnectionUtils.getConn();
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            pst = conn.prepareStatement(sql);
            //查询
            rst = pst.executeQuery();
            //获取元数据对象
            ResultSetMetaData metaData = rst.getMetaData();
            //获取列数
            int columnCount = metaData.getColumnCount();
            System.out.println(columnCount);
            //获取表的名称
            String tableName = metaData.getTableName(1);
            System.out.println("表名:" + tableName);
            for (int i = 0;i < columnCount;i++){
                String columnName = metaData.getColumnName(i + 1);//从1开始
                System.out.println(columnName);
                int columnType = metaData.getColumnType(i + 1);
                System.out.println(columnType);
                //String columnLabel = metaData.getColumnLabel(i + 1);
                //System.out.println(columnLabel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtils.close(conn,pst,rst);
        }
    }
}
