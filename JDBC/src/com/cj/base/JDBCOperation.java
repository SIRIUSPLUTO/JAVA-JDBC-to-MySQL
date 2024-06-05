package com.cj.base;

import org.junit.Test;

import java.sql.*;

public class JDBCOperation {

    @Test
    //查询单行单列
    public void testQuerySingleRowAndColumn() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Cj20040503@");

        PreparedStatement preparedStatement = connection.prepareStatement("select COUNT(*) as count from t_emp");

        ResultSet resultSet = preparedStatement.executeQuery();

//        while (resultSet.next()) {
//            int count = resultSet.getInt("count");
//            System.out.println(count);
//        }
        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            System.out.println(count);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    //查询单行多列
    @Test
    public void testQuerySingleRow() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Cj20040503@");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_emp where emp_id = ?");

        preparedStatement.setInt(1, 5);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    //查询多行多列
    @Test
    public void testQueryMultipleRows() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Cj20040503@");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_emp where emp_age > ?");

        preparedStatement.setInt(1, 25);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    //插入数据
    @Test
    public void testInsert() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Cj20040503@");

        PreparedStatement preparedStatement = connection.prepareStatement("insert into t_emp(emp_name, emp_salary, emp_age) values(?,?,?)");

        preparedStatement.setString(1, "rose");
        preparedStatement.setDouble(2, 345.67);
        preparedStatement.setInt(3, 28);

        int result = preparedStatement.executeUpdate();

        //根据受影响行数判断是否插入成功
        if (result > 0) {
            System.out.println("插入成功!");
        } else {
            System.out.println("插入失败!");
        }

        preparedStatement.close();
        connection.close();
    }

    //更新数据
    @Test
    public void testUpdate() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Cj20040503@");

        PreparedStatement preparedStatement = connection.prepareStatement("update t_emp set emp_salary = ? where emp_id = ?");

        preparedStatement.setDouble(1, 888.88);
        preparedStatement.setInt(2, 6);

        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("更新成功!");
        } else {
            System.out.println("更新失败!");
        }

        preparedStatement.close();
        connection.close();
    }

    //删除数据
    @Test
    public void testDelete() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Cj20040503@");

        PreparedStatement preparedStatement = connection.prepareStatement("delete from t_emp where emp_id = ?");

        preparedStatement.setInt(1, 6);

        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("删除成功!");
        } else {
            System.out.println("删除失败!");
        }

        preparedStatement.close();
        connection.close();
    }
}








































