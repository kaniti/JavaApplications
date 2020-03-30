package com.sample.controller;


import com.sample.dao.EmployeeDAO;
import com.sample.model.Employee;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


/**
 * Unit tests for {@link EmployeeRestController}.
 */
@ExtendWith(DropwizardExtensionsSupport.class)
public class EmployeeRestControllerTest {
    //public static ManagedDataSource dataSource;
    private ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
    public static final ResourceExtension RESOURCES = ResourceExtension.builder()
            .addResource(new EmployeeRestController())
            .build();

    @InjectMocks
    EmployeeRestController employeeRestController;
    @Mock
    EmployeeDAO employeeDAO;

    private Employee employee;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employee = new Employee();
        employee.setId(1);
        employee.setFirstName("raja");
        employee.setLastName("sekhar");
        employee.setEmail("rajasekhar@gmail.com");
    }
    @AfterEach
    public void tearDown() {
        reset(employeeDAO);
    }

    @Test
    public void testCreateEmployee() throws URISyntaxException {

        employeeDAO.createEmployee(employee);
        Assert.assertNotNull(Integer.valueOf(1));
        Assert.assertEquals(Integer.valueOf(1),employee.getId());
        Assert.assertEquals(String.valueOf("raja"), employee.getFirstName());
        Assert.assertEquals(String.valueOf("sekhar"), employee.getLastName());
        Assert.assertEquals(String.valueOf("rajasekhar@gmail.com"), employee.getEmail());

        when(employeeDAO.createEmployee(employee)).thenReturn(String.valueOf("Error"));
        Response response = employeeRestController.createEmployee(1,"raja","sekhar","rajasekhar@gmail.com");
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.NOT_FOUND);


    /*    when(employeeDAO.createEmployee(employee)).thenReturn(String.valueOf("created successfully"));
        Response res = employeeRestController.createEmployee(1,"raja","sekhar","rajasekhar@gmail.com");
        assertThat(res.getStatusInfo()).isEqualTo(Response.Status.OK);*/
    }
    @Test
    public void testGetEmployeeById(){
        when(employeeDAO.getEmployee(ArgumentMatchers.anyInt())).thenReturn(employee);
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
       Employee emp = new Employee();
       emp.setId(5);
       emp.setFirstName("shiva");
       emp.setLastName("kaniti");
       emp.setEmail("shiva.kaniti@gmail.com");
       employeeDAO.updateEmployee(5, emp);

       Assert.assertNotNull(Integer.valueOf(5));
       Assert.assertEquals(Integer.valueOf(5),emp.getId());
       Assert.assertEquals(String.valueOf("shiva"), emp.getFirstName());
       Assert.assertEquals(String.valueOf("kaniti"), emp.getLastName());
       Assert.assertEquals(String.valueOf("shiva.kaniti@gmail.com"), emp.getEmail());

        when(employeeDAO.updateEmployee(1, employee)).thenReturn(String.valueOf("Employee with "+employee.getId()+" not Existed"));
        Response response = employeeRestController.updateEmployeeById(1,"abc","sekhar","raja.kaniti@gmail.com");
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.NOT_FOUND);

    /*   when(employeeDAO.updateEmployee(1, employee)).thenReturn(String.valueOf("updated successfully"));
       Response res = employeeRestController.updateEmployeeById(1,"abc","sekhar","raja.kaniti@gmail.com");
       assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);*/

    }

    @Test
    public void testRemoveEmployeeById(){
        when(employeeDAO.removeEmployee(ArgumentMatchers.anyInt())).thenReturn(String.valueOf("deleted successfully"));
        Response response = employeeRestController.removeEmployeeById(1);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }


}
