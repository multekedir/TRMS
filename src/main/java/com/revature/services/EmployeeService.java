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

        Employee result = employeeDAO.getUserByUserName(username);
        if (result == null) {
            getLogger(EmployeeService.class).debug("Adding Employee");
            Employee employee = new Employee(username, firstName, lastName, password,
                    addRole(role),
                    addDepartment(department));


            getLogger(EmployeeService.class).debug("Role ->" + role);
            getLogger(EmployeeService.class).debug("Department ->" + department);
            return employeeDAO.insert(employee) ? employee : null;
        }
        getLogger(EmployeeService.class).debug("No need to add Employee");
        return result;
    }

    public static Employee addEmployee(Employee employee) {
        assert (employee != null);
        Employee result = employeeDAO.getUserByUserName(employee.getUsername());
        if (result == null) {
            getLogger(EmployeeService.class).debug("Adding Employee");
            employee.setRole(addRole(employee.getRole()));
            employee.setDepartment(addDepartment(employee.getDepartment()));
            return employeeDAO.insert(employee) ? employee : null;
        }
        getLogger(EmployeeService.class).debug("No need to add Employee");
        return result;

    }


    public static Department addDepartment(Department department) {

        getLogger(EmployeeService.class).debug("Adding Department");
        Department dbResultDepartment = departmentDAO.filterWithName(department.getName());
        if (dbResultDepartment != null) {
            return dbResultDepartment;
        }


        return departmentDAO.insert(department) ? department : null;

    }

    public static Department addDepartmentHead(Department department, Employee employee) {

        getLogger(EmployeeService.class).debug("Adding Department Manager");
        Department dbResultDepartment = addDepartment(department);
        dbResultDepartment.setHead(addEmployee(employee));

        return departmentDAO.update(dbResultDepartment);

    }

    public static Role addSupervisor(Role role, Employee employee) {

        getLogger(EmployeeService.class).debug("Adding Supervisor");
        Role dbResultRole = addRole(role);
        dbResultRole.setSupervisor(addEmployee(employee));

        return roleDAO.update(dbResultRole);

    }


    public static Role addRole(Role role) {
        getLogger(EmployeeService.class).debug("Adding Role");
        Role dbResultRole = roleDAO.filterWithName(role.getName());
        if (dbResultRole != null) {
            return dbResultRole;
        }
        return roleDAO.insert(role) ? role : null;
    }

    public static Employee getEmployeeByID(int id) {
        return employeeDAO.getEmployeeByID(id);
    }


}
