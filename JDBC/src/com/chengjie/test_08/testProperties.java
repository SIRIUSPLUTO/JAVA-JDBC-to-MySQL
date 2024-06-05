package com.chengjie.test_08;

import com.chengjie.util.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 本案例演示:通过读取配置文件获取JDBC参数
 * 1.配置资源文件 DB.properties
 * 2.加载配置文件，读取配置信息
 * 注意:修改的是ConnectionUtils类
 */
public class testProperties {
    public static void main(String[] args) {
        //try-with-resources  自动会在finally中关闭
        try(
        Connection conn = ConnectionUtils.getConn();
        PreparedStatement pst = conn.prepareStatement("select * from account");
        ){
            ResultSet rst = pst.executeQuery();
            while(rst.next()){
                System.out.println(rst.getString("accid") + "==" + rst.getString("accname"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
