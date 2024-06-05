package com.cj.senior.Dao.impl;

import com.cj.senior.Dao.BaseDao;
import com.cj.senior.Dao.EmployeeDao;
import com.cj.senior.pojo.Employee;

import java.util.List;

public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {

    @Override
    public List<Employee> selectAll() {
        try {
            String sql = "select emp_id as empId, emp_name as empName, emp_salary as empSalary, emp_age as empAge from t_emp";
            return executeQuery(Employee.class, sql, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee selectByEmpId(Integer empId) {
        try {
            String sql = "select emp_id as empId, emp_name as empName, emp_salary as empSalary, emp_age as empAge from t_emp where emp_id = ?";
            return executeQueryBean(Employee.class, sql, empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Employee employee) {
        try {
            String sql = "insert into t_emp(emp_name, emp_salary, emp_age) values(?,?,?)";
            return executeUpdate(sql, employee.getEmpName(), employee.getEmpSalary(), employee.getEmpAge());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Employee employee) {
        try {
            String sql = "update t_emp set emp_salary =? where emp_id = ?";
            return executeUpdate(sql, employee.getEmpSalary(), employee.getEmpId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer empId) {
        try {
            String sql = "delete from t_emp where emp_id = ?";
            return executeUpdate(sql, empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
