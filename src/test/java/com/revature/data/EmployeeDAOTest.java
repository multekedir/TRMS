package com.revature.data;

import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.models.Role;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EmployeeDAOTest {
    //TODO test EmployeeDAO
    Employee employee;
    EmployeeDAO dao = new EmployeeDAO();


    @BeforeClass
    public static void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInsert() {
        Employee employee;
        Role role = new Role("manager");
        Department department = new Department("IT");
        employee = new Employee("multezem", "kedir", "testuser", "password", role, department);
        employee.setDepartment(department);
        employee.setRole(role);
        assertTrue(dao.insert(employee));
    }

    @Test
    public void getEmployeeByID() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}