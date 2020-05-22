package com.revature.services;

import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.models.Role;
import org.junit.Before;
import org.junit.Test;

import static com.revature.services.EmployeeService.getSubordinates;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeServiceTest {

    Employee employee;
    Department department;
    Role role;


    @Before
    public void setUp() throws Exception {
        employee = new Employee("first", "last", "first_last", "password");
        role = new Role("IT");
        department = new Department("Software");
    }

//    @After
//    public void tearDown() throws Exception {
//        assertTrue(DAOFactory.geEmployeeDAO().delete(employee));
//        assertTrue(DAOFactory.getDepartmentDAO().delete(department));
//        assertTrue(DAOFactory.getRoleDAO().delete(role));
//    }

    @Test
    public void testAddEmployee() {
        employee = EmployeeService.addEmployee("first", "last", "first_last", "password", role, department);
        assertNotNull(employee);

    }

    @Test
    public void testGetEmployeeBYID() {
        employee = EmployeeService.addEmployee("first", "last", "first_last", "password", role, department);

        assertEquals(employee, EmployeeService.getEmployeeByID(employee.getId()));
    }

    @Test
    public void testAddDepartmentHead() {
        employee = new Employee("first", "last", "first_last", "password", role, department);
        EmployeeService.addDepartmentHead(department, employee);
//        assertEquals(employee, EmployeeService.getEmployeeByID(employee.getId()));
    }

    @Test
    public void testAddSupertHead() {
        employee = new Employee("first", "last", "first_last", "password", role, department);
        EmployeeService.addSupervisor(role, employee);
//        assertEquals(employee, EmployeeService.getEmployeeByID(employee.getId()));
    }

    @Test
    public void testGetSubordinates() {
        System.out.println(getSubordinates(164));
    }
}
