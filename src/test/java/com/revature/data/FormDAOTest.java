package com.revature.data;

import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.models.Form;
import com.revature.models.Role;
import com.revature.services.EmployeeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.revature.data.DAOFactory.getFormDAO;

public class FormDAOTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInsert() {
        Employee employee = EmployeeService.addEmployee("formtest", "form", "test", "password", new Role("IT"),
                new Department("Manager"));
        Form form = new Form(22.22, employee.getId(), "testing");
        getFormDAO().insert(form);
    }
}
