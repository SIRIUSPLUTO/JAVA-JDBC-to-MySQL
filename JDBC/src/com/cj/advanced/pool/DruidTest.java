package com.cj.advanced.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidTest {

    /**
     *  硬编码：将连接池的配置信息和Java代码耦合在一起
     *  1、创建DruidDataSource连接池对象
     *  2、设置连接池的配置信息 [必须 | 非必须]
     *  3、通过连接池获取连接对象
     *  4、回收连接 [不是释放连接，而是将连接归还给连接池，给其他线程复用]
     */
    @Test
    public void testHardCodeDruid() throws Exception {
        DruidDataSource druidDataSource = new DruidDataSource();

        //2.1、必须设置的配置
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("Cj20040503@");
        //2.2、非必须设置的配置
        druidDataSource.setInitialSize(10);
        druidDataSource.setMaxActive(20);

        Connection connection = druidDataSource.getConnection();
        System.out.println(connection);

        //基于connection进行CRUD


        connection.close();
    }

    @Test
    public void testResourcesDruid() throws Exception {
        //创建properties集合，用于存储外部配置文件的key和value值
        Properties properties = new Properties();
        
        //读取外部配置文件，获取输入流，并加载到properties集合中
        InputStream inputStream = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");
        properties.load(inputStream);
        
        //基于properties集合构建DruidDataSource连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        //通过连接池获取连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        //开发CRUD

        
        //回收连接
        connection.close();
    }
}
