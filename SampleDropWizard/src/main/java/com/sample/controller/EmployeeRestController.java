package com.sample.controller;

import com.sample.dao.EmployeeDAO;
import com.sample.model.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.net.URISyntaxException;
import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeRestController {

    EmployeeDAO empDao = new EmployeeDAO();
    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Integer id) {
        System.out.println("==================get Employee by ID==============================");
        Employee employee = empDao.getEmployee(id);
        if (employee != null)
            return Response.ok(employee).build();
        else
            return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/getAllEmployees")
    public Response getAllEmployees(){
        List<Employee> employeeList = empDao.getEmployees();
        if (employeeList.size() > 0)
            return Response.ok(employeeList).build();
        else
            return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    @Path("/createEmployees")
    public Response createEmployee(@HeaderParam("id") Integer id, @HeaderParam("first_name") String fName,@HeaderParam("last_name") String lName, @HeaderParam("email") String email) throws URISyntaxException {
        System.out.println("=====================Create Employees===========================");
        Employee employee = new Employee();
        if (id != null)
            employee.setId(id);
            if(fName != null)
                employee.setFirstName(fName);
            if(lName != null)
                employee.setLastName(lName);
            if(email != null)
                employee.setEmail(email);
        String message  = empDao.createEmployee(employee);
        if(message != null){
            return Response.ok(id+"Employee's got created successfully!!!").build();
        }else{
            return Response.status(Status.NOT_FOUND).build();
        }

    }

    @PUT
    @Path("/{id}")
    public Response updateEmployeeById(@PathParam("id") Integer id, @HeaderParam("first_name") String fName,@HeaderParam("last_name") String lName, @HeaderParam("email") String email) {
        System.out.println("====================Update Employee details============================");
        Employee employee = new Employee();
            employee.setId(id);
            if(fName != null)
                employee.setFirstName(fName);
            if(lName != null)
                employee.setLastName(lName);
            if(email != null)
                employee.setEmail(email);

               Employee emp = empDao.updateEmployee(id, employee);
        if (employee != null)
            return Response.ok(employee.getId()+"Updated successfully!!").build();
        else
            return Response.status(Status.NOT_FOUND).build();

    }

    @DELETE
    @Path("/{id}")
    public Response removeEmployeeById(@PathParam("id") Integer id) {
        System.out.println("==================remove Employee By ID===================================");
            Employee employee = empDao.removeEmployee(id);
        if (employee != null)
            return Response.ok(employee.getId()+"deleted successfully!!").build();
        else
            return Response.status(Status.NOT_FOUND).build();


    }
}
