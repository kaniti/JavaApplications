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
        employees.put(emp.getId(), emp);
        return "created successfully";
    }

    public Employee updateEmployee(Integer id, Employee employee){
        return employees.put(id, employee);
    }

    public Employee removeEmployee(Integer id){
       return employees.remove(id);

    }
}
