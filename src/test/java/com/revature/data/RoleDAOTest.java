package com.revature.data;

import com.revature.models.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RoleDAOTest {

    public Role role;
    RoleDAO roleDAO;

    @Before
    public void setup() {
        role = new Role("IT");
        roleDAO = new RoleDAO();
    }

    @After
    public void tearDown() {
        assertTrue(roleDAO.delete(role));
    }

    @Test
    public void testInsert() {
        assertTrue(roleDAO.insert(role));
    }

    @Test
    public void testGetByID() {
        testInsert();
        assertNotNull(roleDAO.getRoleByID(role.getId()));
    }

    @Test
    public void testUpdate() {
        testInsert();
        role.setName("Global IT");
        assertNotNull(roleDAO.update(role));
    }

    @Test
    public void testGetAll() {
        testInsert();
//        assertEquals(1,roleDAO.getAll().size());
    }
}