package com.revature.services;

import com.revature.data.DAOFactory;
import com.revature.data.EmployeeDAO;
import com.revature.models.Employee;

import static com.revature.utility.LoggerSingleton.getLogger;

public class LoginService {

    static private final EmployeeDAO employeeDAO = DAOFactory.geEmployeeDAO();


    /**
     * Login user.
     *
     * @param username the username
     * @param password the password
     * @return the user
     */
    public static Employee login(String username, String password) {
        Employee user = employeeDAO.getUserByUserName(username);
        if (user == null) {
            getLogger(LoginService.class).info("Not a valid username");
        } else {
            if (user.checkPassword(password)) {
                getLogger(LoginService.class).info("login was successful.");
                return user;
            }
            getLogger(LoginService.class).info("Not a valid Password");
        }
        return null;
    }

    /**
     * Register user.
     *
     * @param employee the user
     * @return the user employee
     */
    public static Employee register(Employee employee) {
        getLogger(LoginService.class).info("Registering user");
        return EmployeeService.addEmployee(employee);
    }

}
