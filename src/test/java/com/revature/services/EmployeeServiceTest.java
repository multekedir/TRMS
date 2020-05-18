package com.revature.services;

import com.revature.data.DAOFactory;
import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.models.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @After
    public void tearDown() throws Exception {
        assertTrue(DAOFactory.geEmployeeDAO().delete(employee));
    }

    @Test
    public void testAddEmployee() {
        employee = EmployeeService.addEmployee("first", "last", "first_last", "password", role, department);
        assertNotNull(employee);

    }

    @Test
    public void testGetEmployeeBYID() {
        employee = EmployeeService.addEmployee("first_1", "last_1", "firstlast", "password", role, department);

        assertEquals(employee, EmployeeService.getEmployeeByID(employee.getId()));
    }
}