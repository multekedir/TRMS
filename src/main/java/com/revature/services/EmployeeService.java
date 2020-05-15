package com.revature.services;

import com.revature.data.DAOFactory;
import com.revature.data.DepartmentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.RoleDAO;
import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.models.Role;

import static com.revature.utility.LoggerSingleton.getLogger;

public class EmployeeService {
    static private final EmployeeDAO employeeDAO = DAOFactory.geEmployeeDAO();
    static private final DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAO();
    static private final RoleDAO roleDAO = DAOFactory.getRoleDAO();


    public static Employee addEmployee(String username, String firstName, String lastName, String password, Role role,
                                       Department department) {

        Department dbResultDepartment = departmentDAO.filterWithName(department.getName());

        Role dbResultRole = roleDAO.filterWithName(role.getName());

        if (dbResultDepartment == null) {
            department = addDepartment(department);
        } else {
            department = dbResultDepartment;
        }
        if (dbResultRole == null) {
            role = addRole(role);
        } else {
            role = dbResultRole;
        }

        getLogger(EmployeeService.class).debug("Adding Employee");
        getLogger(EmployeeService.class).debug("Role ->" + role);
        getLogger(EmployeeService.class).debug("Department ->" + department);
        Employee employee = new Employee(username, firstName, lastName, password, role, department);
        return employeeDAO.insert(employee) ? employee : null;
    }


    public static Department addDepartment(Department department) {
        getLogger(EmployeeService.class).debug("Adding Department");
        return departmentDAO.insert(department) ? department : null;

    }

    public static Role addRole(Role role) {
        getLogger(EmployeeService.class).debug("Adding Role");
        return roleDAO.insert(role) ? role : null;
    }

    public static Employee getEmployeeByID(int id) {
        return employeeDAO.getEmployeeByID(id);
    }


}
