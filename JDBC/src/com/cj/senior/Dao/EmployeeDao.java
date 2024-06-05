package com.cj.senior.Dao;

import com.cj.senior.pojo.Employee;

import java.util.List;

/**
 * EmployeeDao对应t_emp表的增删改查操作
 */
public interface EmployeeDao {

    //查询所有数据
    List<Employee> selectAll();

    //根据empID查询单个数据
    Employee selectByEmpId(Integer empId);

    //新增一条数据
    int insert(Employee employee);

    //更新一条数据
    int update(Employee employee);

    //根据empID删除一条数据
    int delete(Integer empId);
}
