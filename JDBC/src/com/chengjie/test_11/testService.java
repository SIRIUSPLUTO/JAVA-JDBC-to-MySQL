package com.chengjie.test_11;

import com.chengjie.test_11.entity.Account;
import com.chengjie.test_11.service.AccountService;

import java.util.Date;
import java.util.List;

/**
 * 调用业务
 */
public class testService {
    public static void main(String[] args) {
        AccountService service = new AccountService();

        Account account = new Account();
        //account.setAccid(5);
        account.setAccname("cj");
        //account.setBalance(0f);
        account.setPassword("111111");
        //account.setAccdate(new Date());
        //注册
        //service.register(account);
        //测试登录
        //boolean login = service.login(account);
        //System.out.println(login);

        //账户列表
        List<Account> accounts = service.queryAll();
        for (Account account1 : accounts) {
            System.out.println(account1);
        }
    }
}
