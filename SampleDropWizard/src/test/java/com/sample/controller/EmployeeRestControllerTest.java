package com.sample.controller;


import com.sample.dao.EmployeeDAO;
import com.sample.model.Employee;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


/**
 * Unit tests for {@link EmployeeRestController}.
 */
@ExtendWith(DropwizardExtensionsSupport.class)
public class EmployeeRestControllerTest {
    public Employee employee;
    private ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
    public static final ResourceExtension RESOURCES = ResourceExtension.builder()
            .addResource(new EmployeeRestController())
            .build();

    @InjectMocks
    EmployeeRestController employeeRestController;
    @Mock
    EmployeeDAO employeeDAO;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @AfterEach
    public void tearDown() {
        reset(employeeDAO);
    }

    @Test
    public void testCreateEmployee() {
        final Response response = RESOURCES.target("/employees/createEmployees")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    @Test
    public void testGetEmployeeById(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("raja");
        employee.setLastName("sekhar");
        employee.setEmail("rajasekhar@gmail.com");
        when(employeeDAO.getEmployee(anyInt())).thenReturn(employee);
        Response res = employeeRestController.getEmployeeById(1);
        assertThat(res.getStatusInfo()).isEqualTo(Response.Status.OK);
    }

    @Test
    public void testGetAllEmployees(){
        List<Employee> empList = new ArrayList<Employee>();
        Employee e1 = new Employee(1,"vxcvc","xcvcxv","dsffg@fgdf.com");
        Employee e2 = new Employee(2,"sdfds","truyut","jkhjk@fgdf.com");
        Employee e3 = new Employee(3,"fghgf","rtert","sdfs@fgdf.com");

        empList.add(e1);
        empList.add(e2);
        empList.add(e3);

        when(employeeDAO.getEmployees()).thenReturn(empList);
        Response response = employeeRestController.getAllEmployees();

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }

   @Test
    public void testUpdateEmployeeById(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("abc");
        employee.setLastName("adsfgsdf");
        employee.setEmail("sdf.sadf@gmail.com");
        when(employeeDAO.updateEmployee(1, employee)).thenReturn(employee);
        Response response = employeeRestController.updateEmployeeById(2,"dsrtert","wewerew","sdf.rwe@gmail.com");
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }

    @Test
    public void testRemoveEmployeeById(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("raja");
        employee.setLastName("sekhar");
        employee.setEmail("rajasekhar@gmail.com");
        when(employeeDAO.removeEmployee(anyInt())).thenReturn(employee);
        Response response = employeeRestController.removeEmployeeById(1);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }


}
