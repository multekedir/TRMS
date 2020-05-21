package com.revature.data;

public class DAOFactory {
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final DepartmentDAO departmentDAO = new DepartmentDAO();
    private static final RoleDAO roleDAO = new RoleDAO();
    private static final FormDAO formDAO = new FormDAO();


    public static EmployeeDAO geEmployeeDAO() {
        return employeeDAO;
    }

    public static DepartmentDAO getDepartmentDAO() {
        return departmentDAO;
    }

    public static RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public static FormDAO getFormDAO() {
        return formDAO;
    }
}
