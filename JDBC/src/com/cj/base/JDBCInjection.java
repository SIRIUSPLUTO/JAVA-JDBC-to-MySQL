package com.cj.base;

import java.sql.*;
import java.util.Scanner;

public class JDBCInjection {
    public static void main(String[] args) throws Exception {
        //注册驱动(可省略)

        //获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=Cj20040503@");

        //获取执行SQL语句的对象
        Statement statement = connection.createStatement();

        System.out.println("请输入要查询的员工姓名：");
        Scanner scanner = new Scanner(System.in);
        String Name = scanner.nextLine();

        //编写SQL语句并执行,接受返回的结果集
        //姓名输入abc' or '1' = '1成功实现注入攻击
        String sql = "SELECT * FROM t_emp WHERE emp_name = '" + Name + "'";
        ResultSet resultSet = statement.executeQuery(sql);

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
        statement.close();
        connection.close();
    }
}
