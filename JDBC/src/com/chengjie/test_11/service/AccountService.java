package com.chengjie.test_11.service;

import com.chengjie.test_11.dao.AccountDao;
import com.chengjie.test_11.entity.Account;

import java.util.List;

/**
 * 账户业务
 */
public class AccountService {
    private AccountDao accountDao = new AccountDao();
    /**
     * 登录业务
     * @param account
     * @return
     */
    public boolean login(Account account){
        Account dbaccount = accountDao.findAccountByAccname(account.getAccname());

        if (dbaccount != null){ //用户名有效
            //匹配密码
            if (dbaccount.getPassword() != null && dbaccount.getPassword().equals(account.getPassword())){ //密码匹配成功，说明是合法用户
                return true;
            }
        }
        return false;
    }

    /**
     * 注册业务
     */
    public void register(Account account){
        accountDao.save(account);
    }

    /**
     * 查询列表
     */
    public List<Account> queryAll(){

        return accountDao.queryAll();
    }
}
