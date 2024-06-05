package com.cj.senior.Dao.impl;

import com.cj.senior.Dao.BankDao;
import com.cj.senior.Dao.BaseDao;

public class BankDaoImpl extends BaseDao implements BankDao {

    @Override
    public int addMoney(Integer id, Integer money) {
        try {
            String sql = "update t_bank set money = money + ? where id = ?";
            return executeUpdate(sql, money, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int subMoney(Integer id, Integer money) {
        try {
            String sql = "update t_bank set money = money - ? where id = ?";
            return executeUpdate(sql, money, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

