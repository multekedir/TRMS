package com.revature.data;

import com.revature.models.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EmployeeDAOTest {
    //TODO test EmployeeDAO
    Employee employee;
    EmployeeDAO dao = new EmployeeDAO();


    @Before
    public void setup() {
        employee = new Employee("first", "last", "first_last", "password");
        dao = new EmployeeDAO();
    }

    @After
    public void tearDown() {
        assertTrue(dao.delete(employee));
    }

    @Test
    public void testInsert() {
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