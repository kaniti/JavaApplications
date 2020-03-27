package com.sample.dao;

import com.sample.model.Employee;
import com.sample.util.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> getEmployees(){
        List<Employee> listOfEmployees = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try{
            con = JDBCConnection.getConnection();
            stmt = con.createStatement();
            resultSet = stmt.executeQuery("select * from employee");
            while(resultSet.next()){
                Employee emp = new Employee();
                emp.setId(Integer.parseInt(resultSet.getString("emp_id")));
                emp.setFirstName(resultSet.getString("first_name"));
                emp.setLastName(resultSet.getString("last_name"));
                emp.setEmail(resultSet.getString("email_id"));
                listOfEmployees.add(emp);
            }
        }catch (SQLException | ClassNotFoundException ex){
            return listOfEmployees;
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
            }catch(SQLException e){
                System.out.println("Exception occured during resultset closing");
            }

            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch(SQLException e){
                System.out.println("Exception occured during Statement closing");
            }

            try{
                if(con != null){
                    con.close();
                }
            }catch(SQLException e){
                System.out.println("Exception occured during Connection closing");
            }

        }
        return listOfEmployees;
    }

    public Employee getEmployee(Integer id){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        Employee emp = null;
        try{
            con = JDBCConnection.getConnection();
            String query = "select * from employee where emp_id=?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1,id);
            resultSet = stmt.executeQuery();
            while(resultSet.next()){
                emp = new Employee();
                emp.setId(Integer.parseInt(resultSet.getString("emp_id")));
                emp.setFirstName(resultSet.getString("first_name"));
                emp.setLastName(resultSet.getString("last_name"));
                emp.setEmail(resultSet.getString("email_id"));
            }
        }catch (SQLException | ClassNotFoundException ex){
            emp = null;
            System.out.println("Exception Occured while fetching the employee data!!");
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
            }catch(SQLException e){
                System.out.println("Exception occured during resultset closing");
            }
            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch(SQLException e){
                System.out.println("Exception occured during Statement closing");
            }

            try{
                if(con != null){
                    con.close();
                }
            }catch(SQLException e){
                System.out.println("Exception occured during Connection closing");
            }
        }
        return emp;
    }

    public String createEmployee(Employee emp) {
        Connection con = null;
        PreparedStatement prestmt = null;
        try{
            con = JDBCConnection.getConnection();
            String query = "insert into employee (emp_id,first_name,last_name,email_id) values (?,?,?,?)";
            prestmt = con.prepareStatement(query);
            prestmt.setInt(1,emp.getId());
            prestmt.setString(2,emp.getFirstName());
            prestmt.setString(3,emp.getLastName());
            prestmt.setString(4,emp.getEmail());
            int rowInserted = prestmt.executeUpdate();
            if(rowInserted > 0)
                return "created successfully";
            else
                return "Error";
        }catch (SQLException | ClassNotFoundException ex){
            return "Error";
        }finally{
            try{
                if(prestmt != null){
                    prestmt.close();
                }
            }catch(SQLException e){
                System.out.println("Exception occured during Statement close");
            }
            try{
                if(con != null){
                    con.close();
                }
            }catch(SQLException e){
                System.out.println("Exception occured during Connection close");
            }

        }

    }

    public String updateEmployee(Integer id, Employee emp){
        Connection con = null;
        PreparedStatement prestmt = null;
        ResultSet resultSet  =null;

        try{
            con = JDBCConnection.getConnection();

            String getEmpIdQuery = "select * from employee where emp_id=?";
            prestmt = con.prepareStatement(getEmpIdQuery);
            prestmt.setInt(1,id);
            resultSet = prestmt.executeQuery();
            while(resultSet.next()){
                String query = "update employee set first_name =?,last_name =?,email_id=? where emp_id=?";
                prestmt = con.prepareStatement(query);
                prestmt.setInt(4,id);
                if(emp.getFirstName() != null)
                    prestmt.setString(1,emp.getFirstName());
                else
                    prestmt.setString(1,resultSet.getString("first_name"));

                if(emp.getLastName() != null)
                    prestmt.setString(2,emp.getLastName());
                else
                    prestmt.setString(2,resultSet.getString("last_name"));
                if(emp.getEmail() != null)
                    prestmt.setString(3,emp.getEmail());
                else
                    prestmt.setString(3,resultSet.getString("email_id"));

                int rowInserted = prestmt.executeUpdate();
                if(rowInserted > 0)
                    return "updated successfully";
                else
                    return "Employee with "+id+ " not Existed";

            }
        }catch (SQLException | ClassNotFoundException ex){
            return "Employee with "+id+ " not Existed";
        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println("Exception occured during Statement close");
            }
            try {
                if (prestmt != null) {
                    prestmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Exception occured during Statement close");
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Exception occured during Connection close");
            }

        }
        return "Employee with "+id+ " not Existed";
    }

    public String removeEmployee(Integer id){
        Connection con = null;
        PreparedStatement prestmt = null;
        try{
            con = JDBCConnection.getConnection();
            String query = "delete from employee where emp_id=?";
            prestmt = con.prepareStatement(query);
            prestmt.setInt(1,id);

            int rowInserted = prestmt.executeUpdate();
            if(rowInserted > 0)
                return "deleted successfully";
            else
                return "Employee with "+id+ " not Existed";
        }catch (SQLException | ClassNotFoundException ex){
            return "Employee with "+id+ " not Existed";
        }finally{
            try{
                if(prestmt != null){
                    prestmt.close();
                }
            }catch(SQLException e){
                System.out.println("Exception occured during Statement close");
            }
            try{
                if(con != null){
                    con.close();
                }
            }catch(SQLException e){
                System.out.println("Exception occured during Connection close");
            }
        }
    }
}
