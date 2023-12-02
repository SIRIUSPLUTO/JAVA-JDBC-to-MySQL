package com.chengjie.test_9;

import com.chengjie.util.ConnectionUtils;

import java.sql.CallableStatement;
import java.sql.Connection;

/**
 * 本案例演示:JDBC调用存储过程
 * 1.调用无参无输入的存储过程
 * 2.有入参无出参存储过程
 * 3.有入参和出参存储过程
 * 4.查询结果的存储过程
 */
public class testCallprocedure {
    public static void main(String[] args) {
        //callNOParamProcedure();
        //paramProcedure();
    }

    /**
     * 通过存储过程实现转账，传入三个参数
     */
    public static void paramProcedure(){
        try(Connection connection = ConnectionUtils.getConn()){
            CallableStatement cst = connection.prepareCall("call transfer(?,?,?)");
            cst.setFloat(1,500);
            cst.setInt(2,1); //转出
            cst.setInt(3,2); //入账

            int i = cst.executeUpdate();
            System.out.println(i);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
    * 调用无参存储过程
    * 目标:创建和拷贝账户表
    */
    public static void callNOParamProcedure(){
        try(Connection connection = ConnectionUtils.getConn()){
            //创建执行存储过程的声明对象
            CallableStatement cs = connection.prepareCall("CALL create_back");
            //执行
            int i = cs.executeUpdate();
            System.out.println(i);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

