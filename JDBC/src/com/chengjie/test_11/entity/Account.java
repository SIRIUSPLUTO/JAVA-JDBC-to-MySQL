package com.chengjie.test_11.entity;

import java.util.Date;

/**
 * JavaBean:实体类，与表对应
 */
public class Account {
    private Integer accid;
    private String accname;
    private String password;
    private Float balance;
    private Date accdate;
    private Byte state;

    /**
     * 获取
     *
     * @return accid
     */
    public Integer getAccid() {
        return accid;
    }

    /**
     * 设置
     *
     * @param accid
     */
    public void setAccid(Integer accid) {
        this.accid = accid;
    }

    /**
     * 获取
     *
     * @return accname
     */
    public String getAccname() {
        return accname;
    }

    /**
     * 设置
     *
     * @param accname
     */
    public void setAccname(String accname) {
        this.accname = accname;
    }

    /**
     * 获取
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     *
     * @return balance
     */
    public Float getBalance() {
        return balance;
    }

    /**
     * 设置
     *
     * @param balance
     */
    public void setBalance(Float balance) {
        this.balance = balance;
    }

    /**
     * 获取
     *
     * @return accdate
     */
    public Date getAccdate() {
        return accdate;
    }

    /**
     * 设置
     *
     * @param accdate
     */
    public void setAccdate(Date accdate) {
        this.accdate = accdate;
    }

    /**
     * 获取
     *
     * @return state
     */
    public Byte getState() {
        return state;
    }

    /**
     * 设置
     *
     * @param state
     */
    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accid=" + accid +
                ", accname='" + accname + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", accdate=" + accdate +
                ", state=" + state +
                '}';
    }
}