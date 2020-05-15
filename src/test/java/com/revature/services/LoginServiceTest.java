package com.revature.services;

import com.revature.data.DAOFactory;
import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.models.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginServiceTest {

    Employee employee;
    Department department;
    Role role;

    @Before
    public void setUp() throws Exception {
        employee = new Employee("first", "last", "first_last", "password");
        employee.setDepartment(new Department("HR"));
        employee.setRole(new Role("IT"));
        role = new Role("IT");
        department = new Department("Software");
    }

    @After
    public void tearDown() throws Exception {
        assertTrue(DAOFactory.geEmployeeDAO().delete(employee));
    }

    @Test
    public void register() {
        assertNotNull(LoginService.register(employee));
    }

    @Test
    public void testLogin() {
        LoginService.register(employee);
        assertEquals(employee, LoginService.login("first_last", "password"));
    }

    @Test
    public void testLoginBadUsername() {
        LoginService.register(employee);
        assertNull(LoginService.login("badusername", "password"));
    }

    @Test
    public void testLoginBadPassword() {
        LoginService.register(employee);
        assertNull(LoginService.login("username", "badpassword"));
    }


}