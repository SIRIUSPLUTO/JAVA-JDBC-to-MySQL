package com.cj.senior;

import com.cj.senior.Dao.BankDao;
import com.cj.senior.Dao.EmployeeDao;
import com.cj.senior.Dao.impl.BankDaoImpl;
import com.cj.senior.Dao.impl.EmployeeDaoImpl;

import com.cj.senior.pojo.Employee;
import com.cj.senior.util.JDBCUtilV1;
import com.cj.senior.util.JDBCUtilV2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCUtilTest {

    @Test
    public void testGetConnection() {
        Connection connection = JDBCUtilV1.getConnection();
        System.out.println(connection);

        //CRUD

        JDBCUtilV1.release(connection);
    }

    @Test
    public void testJDBCV2() {

        /*
            Connection connection1 = JDBCUtilV1.getConnection();
            Connection connection2 = JDBCUtilV1.getConnection();
            Connection connection3 = JDBCUtilV1.getConnection();

            System.out.println(connection1);
            System.out.println(connection2);
            System.out.println(connection3);
         */

        Connection connection1 = JDBCUtilV2.getConnection();
        Connection connection2 = JDBCUtilV2.getConnection();
        Connection connection3 = JDBCUtilV2.getConnection();

        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);
    }

    @Test
    public void testEmployDaoALL() {

        //创建Dao实现类对象
        EmployeeDao employDao = new EmployeeDaoImpl();

        //调用查询所有的方法
        List<Employee> employeeList = employDao.selectAll();

        for (Employee employee : employeeList) {
            System.out.println("employee = " + employee);
        }
    }

    @Test
    public void testEmployeeDaoSingle() {

        EmployeeDao employDao = new EmployeeDaoImpl();

        //调用根据id查询单个的方法
        Employee employee = employDao.selectByEmpId(1);
        System.out.println("employee = " + employee);
    }

    @Test
    public void testEmployeeDaoInsert() {
        EmployeeDao employDao = new EmployeeDaoImpl();

        //调用添加的方法
        Employee employee = new Employee(null, "Tom", 300.65, 38);
        int insert = employDao.insert(employee);
        System.out.println("insert = " + insert);
    }


    @Test
    public void testEmployeeDaoUpdate() {
        EmployeeDao employDao = new EmployeeDaoImpl();

        //调用更新的方法
        Employee employee = new Employee(8, "Tom", 656.65, 38);
        int update = employDao.update(employee);
        System.out.println("update = " + update);
    }

    @Test
    public void testEmployeeDaoDelete() {
        EmployeeDao employDao = new EmployeeDaoImpl();

        //调用删除的方法
        int delete = employDao.delete(8);
        System.out.println("delete = " + delete);
    }

    @Test
    public void testTransaction() {
        BankDao bankDao = new BankDaoImpl();

        Connection connection = null;
        try {
            //获取连接，将连接的事务提交改为手动提交
            connection = JDBCUtilV2.getConnection();
            connection.setAutoCommit(false);

            //操作减钱
            bankDao.subMoney(1, 100);

            //操作加钱
            bankDao.addMoney(2, 100);

            //提交事务
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        } finally {
            JDBCUtilV2.release();
        }
    }
}
