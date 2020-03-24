package com.sample.dao;

import com.sample.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeDAO {
    public static HashMap<Integer, Employee> employees = new HashMap<>();
    static{

    }

    public List<Employee> getEmployees(){
        return new ArrayList<Employee>(employees.values());
    }

    public Employee getEmployee(Integer id){
        return employees.get(id);
    }

    public String createEmployee(Employee emp){
        employees.put(1, emp);
        return "created successfully";
    }

    public String updateEmployee(Integer id, Employee employee){
        employees.put(id, employee);
        return "Employee Updated Successfully";
    }

    public Employee removeEmployee(Integer id){
       return employees.remove(id);

    }
}
