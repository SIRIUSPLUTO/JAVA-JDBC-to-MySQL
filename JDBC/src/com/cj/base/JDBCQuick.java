package com.cj.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCQuick {
    public static void main(String[] args) throws Exception {
        //注册驱动
        //从JDK6开始不再需要显式调用class.forName()来加载JDBC驱动程序，只要在类路径中集成了对应的jar文件，会自动在初始化时注册驱动程序。
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //DriverManager.registerDriver(new Driver());

        //获取连接对象
        //String url = "jdbc:mysql://IP地址:端口号/数据库名?参数键值对1&参数键值对2";
        //本地连接可以用：String url = "jdbc:mysql:///test";
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "Cj20040503@";
        //Connection接口还负责管理事务，Connection接口提供了commit()和rollback()方法来提交或回滚事务。
        Connection connection = DriverManager.getConnection(url, user, password);

        //获取执行SQL语句的对象
        //Statement接口用于执行SQL语句并于数据库交互，包括增删改查等操作。
        //但是Statement接口在执行SQL语句时，会产生SQL注入攻击问题。所以当使用Statement执行动态构建的SQL查询时，往往需要将查询条件与SQL语句拼接在一起，直接将参数和SQL语句一并生成，让SQL的查询条件始终为true得到结果。
        Statement statement = connection.createStatement();

        //编写SQL语句并执行,接受返回的结果集
        String sql = "SELECT * FROM t_emp";
        ResultSet resultSet = statement.executeQuery(sql);

        //处理结果：便利结果集
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }

        //释放资源(先开后关原则)
        resultSet.close();
        statement.close();
        connection.close();
    }
}
