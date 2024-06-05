package com.cj.base;

import java.sql.*;
import java.util.Scanner;

public class JDBCPrepared {
    public static void main(String[] args) throws Exception {
        //注册驱动(可省略)

        //获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=Cj20040503@");

        //获取执行SQL语句的对象
        //PreparedStatement是Statement接口的子接口，用于预编译SQL语句，可以有效防止SQL注入攻击，并且可以复用提高数据库性能。
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_emp WHERE emp_name = ?");

        System.out.println("请输入要查询的员工姓名：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        //为?占位符赋值并执行SQL语句,接受返回的结果集
        preparedStatement.setString(1, name);
        //ResultSet用于表示从数据库中执行查询语句所返回的结果集。
        ResultSet resultSet = preparedStatement.executeQuery();

        //处理结果：便利结果集
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }

        //释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
