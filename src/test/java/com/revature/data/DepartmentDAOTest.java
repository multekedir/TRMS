package com.revature.data;

import com.revature.models.Department;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DepartmentDAOTest {
    public Department department;
    DepartmentDAO departmentDAO;

    @Before
    public void setup() {
        department = new Department("Computer Science");
        departmentDAO = new DepartmentDAO();
    }

//    @After
//    public void tearDown() {
//        assertTrue(departmentDAO.delete(department));
//    }

    @Test
    public void testInsert() {
        assertTrue(departmentDAO.insert(department));
    }


    @Test
    public void testGetByID() {
        testInsert();
        assertNotNull(departmentDAO.getDepartmentByID(department.getId()));
    }

    @Test
    public void testUpdate() {
        testInsert();
        department.setName("Global IT");
        assertNotNull(departmentDAO.update(department));
    }

    @Test
    public void testGetAll() {
        testInsert();

    }
}
