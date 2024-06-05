package com.cj.senior.Dao;

import com.cj.senior.util.JDBCUtilV2;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//将共性的数据库的操作代码封装到BaseDao中
public class BaseDao {

    //通用的增删改方法
    public int executeUpdate(String sql, Object... params) throws Exception {
        Connection connection = JDBCUtilV2.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        int row = preparedStatement.executeUpdate();

        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JDBCUtilV2.release();
        }

        return row;
    }

    //通用查询方法
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws Exception {
        Connection connection = JDBCUtilV2.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();

        //获取结果集中的与元数据对象
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<T> list = new ArrayList<>();
        while (resultSet.next()) {
            //通过反射创建对象
            T t = clazz.newInstance();
            //遍历循环当前行的列
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);

                //获取到列的value值，这个值就是t这个对象中的某一个属性
                //获取当前拿到的列的名字 = 对象的属性名
                String fieldName = metaData.getColumnLabel(i);
                //通过类对象和fieldName获取到要封装对象的属性
                Field field = clazz.getDeclaredField(fieldName);
                //突破封装的private
                field.setAccessible(true);
                field.set(t, value);
            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JDBCUtilV2.release();
        }

        return list;
    }

    //在上面查询的集合结果中获取第一个结果，简化了获取单行单列、单行多列的获取
    public <T> T executeQueryBean(Class<T> clazz, String sql, Object... params) throws Exception {
        List<T> list = this.executeQuery(clazz, sql, params);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}

