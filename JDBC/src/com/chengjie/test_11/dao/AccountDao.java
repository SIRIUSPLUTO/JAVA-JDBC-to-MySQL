package com.chengjie.test_11.dao;

import com.chengjie.test_10.BaseDao;
import com.chengjie.test_11.entity.Account;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 账户数据库访问层
 */
public class AccountDao extends BaseDaoRE {
    public void save(Account account){
        String sql = "insert into account(accid,accname,password,state,accdate,balance)" +
                     "values(?,?,?,?,?,?)";
        Object[] params = {account.getAccid(),account.getAccname(),account.getPassword(),account.getState(),account.getAccdate(),account.getBalance()};
        int rows = super.update(sql, params);
        System.out.println(rows);
    }

    public Account findAccountByAccname(String accname){
        String sql = "select accid,accname,password,accdate from account where accname=?";
        ResultSet rst = super.query(sql, accname);
        try {
            if (rst.next()) {
                Account account = new Account();
                String name = rst.getString("accname");
                String password = rst.getString("password");
                Integer accid = rst.getInt("accid");
                //封装到实体类对象中
                account.setPassword(password);
                account.setAccname(name);
                account.setAccid(accid);
                return account;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> queryAll(){
        String sql = "select accid,accname,password,accdate,state,balance from account";
        return super.query(sql,Account.class);
    }

    //public List<Account> queryAll(){
        //String sql = "select accid,accname,password,accdate,state,balance from account";
        //List<Account> list = new ArrayList<>();

        //ResultSet rst = super.query(sql);
        //try {
            //while (rst.next()) {
                //把一条记录中所有的列值取出
                //Integer accid = rst.getInt("accid");
                //String accname = rst.getString("accname");
                //String password = rst.getString("password");
                //Float balance = rst.getFloat("balance");
                //Byte state = rst.getByte("state");
                //Date date = rst.getDate("accdate");
                //封装到实体对象中
                //Account account =new Account();
                //account.setAccdate(date);
                //account.setAccid(accid);
                //account.setBalance(balance);
                //account.setPassword(password);
                //account.setState(state);
                //account.setAccname(accname);
                //把实体对象添加到list列表
                //list.add(account);
            //}
        //} catch (Exception e){
            //e.printStackTrace();
        //} finally {
            //super.close();
        //}
        //return list;
    //}
}
