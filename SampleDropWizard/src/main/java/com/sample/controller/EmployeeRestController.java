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

    private EmployeeDAO empDao = new EmployeeDAO();

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Integer id) {
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
        if(id != null){
            Employee employee = new Employee();
            employee.setId(id);
            if(fName != null)
                employee.setFirstName(fName);
            if(lName != null)
                employee.setLastName(lName);
            if(email != null)
                employee.setEmail(email);
            String message  = empDao.createEmployee(employee);
            if("created successfully".equals(message)){
                return Response.ok(" Employee with id: "+id+" got created successfully!!!").build();
            }else{
                return Response.status(Status.NOT_FOUND).build();
            }
        }else{
            return Response.status(Status.NOT_FOUND).build();
        }


    }

    @PUT
    @Path("/{id}")
    public Response updateEmployeeById(@PathParam("id") Integer id, @HeaderParam("first_name") String fName,@HeaderParam("last_name") String lName, @HeaderParam("email") String email) {
        Employee employee = new Employee();
            employee.setId(id);
            if(fName != null)
                employee.setFirstName(fName);
            if(lName != null)
                employee.setLastName(lName);
            if(email != null)
                employee.setEmail(email);

               String msg = empDao.updateEmployee(id, employee);
        if ("updated successfully".equals(msg))
            return Response.ok("Employee with ID: "+employee.getId()+" got updated successfully!!").build();
        else
            return Response.status(Status.NOT_FOUND).build();

    }

    @DELETE
    @Path("/{id}")
    public Response removeEmployeeById(@PathParam("id") Integer id) {
        String msg = empDao.removeEmployee(id);
        if ("deleted successfully".equals(msg))
            return Response.ok("Employee with ID: "+id+" got deleted successfully!!").build();
        else
            return Response.status(Status.NOT_FOUND).build();


    }
}
