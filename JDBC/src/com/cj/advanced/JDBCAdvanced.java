package com.cj.advanced;

import com.cj.advanced.pojo.Employee;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAdvanced {
    //ORM
    @Test
    public void testORM() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Cj20040503@");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_emp WHERE emp_id = ?");

        preparedStatement.setInt(1, 1);

        ResultSet resultSet = preparedStatement.executeQuery();

        Employee employee = null;

        //ORM
        if (resultSet.next()) {
            employee = new Employee();

            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");

            //为对象的属性赋值
            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
        }

        System.out.println(employee);

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    //ORM
    @Test
    public void testORMList() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Cj20040503@");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_emp");

        ResultSet resultSet = preparedStatement.executeQuery();

        Employee employee = null;

        List<Employee> employeeList = new ArrayList<>();

        while (resultSet.next()) {
            employee = new Employee();

            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");

            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);

            //将每次循环封装的一行数据的对象存储在集合里
            employeeList.add(employee);
        }

        for (Employee emp : employeeList) {
            System.out.println(emp);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    //主键回显
    @Test
    public void testReturnPK() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Cj20040503@");

        //预编译SQL语句，告知PreparedStatement，返回新增数据的主键列的值
        String sql = "INSERT INTO t_emp (emp_name, emp_salary, emp_age) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        Employee employee = new Employee(null, "jack", 123.45, 29);
        preparedStatement.setString(1, employee.getEmpName());
        preparedStatement.setDouble(2, employee.getEmpSalary());
        preparedStatement.setInt(3, employee.getEmpAge());

        int result = preparedStatement.executeUpdate();
        ResultSet resultSet = null;

        if (result > 0) {
            System.out.println("Successful!");

            //获取当前新增数据的主键列，回显到Java中employee对象的empId属性上
            //返回的主键值，是一个单行单列的结果存储在ResultSet里
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int empId = resultSet.getInt(1);
                employee.setEmpId(empId);
            }
            System.out.println(employee);
        } else {
            System.out.println("Failed!");
        }

        if (resultSet != null) {
            resultSet.close();
        }
        preparedStatement.close();
        connection.close();
    }

    //批量操作
    @Test
    public void testMoreInsert() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Cj20040503@");

        String sql = "INSERT INTO t_emp (emp_name, emp_salary, emp_age) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //获取当前行代码执行的时间（单位：毫秒）
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            //为占位符赋值
            preparedStatement.setString(1, "marry" + i);
            preparedStatement.setDouble(2, 100.0 + i);
            preparedStatement.setInt(3, 20 + i);

            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "毫秒");

        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testBatch() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true", "root", "Cj20040503@");

        String sql = "INSERT INTO t_emp (emp_name, emp_salary, emp_age) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //获取当前行代码执行的时间（单位：毫秒）
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            //为占位符赋值
            preparedStatement.setString(1, "marry" + i);
            preparedStatement.setDouble(2, 100.0 + i);
            preparedStatement.setInt(3, 20 + i);

            preparedStatement.addBatch();
        }

        //执行批量操作
        preparedStatement.executeBatch();

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "毫秒");

        preparedStatement.close();
        connection.close();
    }

}

