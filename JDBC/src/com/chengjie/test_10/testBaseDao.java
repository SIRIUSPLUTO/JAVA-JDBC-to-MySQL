package com.chengjie.test_10;

import java.sql.ResultSet;
import java.util.Date;

public class testBaseDao {
    public static void main(String[] args) {
        String sql ="select accid,accname,password from account";
        BaseDao baseDao = new BaseDao();
        ResultSet rst = baseDao.query(sql);
        try {
            while (rst.next()) {
                String accid = rst.getString("accid");
                String accname = rst.getString("accname");
                String password = rst.getString("password");
                System.out.println(accid + "===" + accname + "===" + password);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            baseDao.close();
        }
    }

    public static void test(String[] args) {
        String sql = "insert into account(accid,accname,password,balance,accdate)values(?,?,?,?,?)";
        Object[] params = {4,"mihuyou","123456",3000,new Date()};

        BaseDao baseDao = new BaseDao();
        baseDao.setAutoCommit(false);
        int rows_1 = baseDao.update(sql,params);
        int rows_2 = baseDao.update(sql,params);
        //提交
        baseDao.commit();
        System.out.println(rows_1);
        System.out.println(rows_2);
    }
}
