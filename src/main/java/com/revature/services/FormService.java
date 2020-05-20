package com.revature.services;

import com.revature.data.DAOFactory;
import com.revature.data.EmployeeDAO;
import com.revature.data.FormDAO;
import com.revature.models.Form;

import static com.revature.utility.LoggerSingleton.getLogger;

public class FormService {

    static private final EmployeeDAO employeeDAO = DAOFactory.geEmployeeDAO();
    static private final FormDAO formDAO = DAOFactory.getFormDAO();

    public static Form addEmployee(Form form) {
        assert (form != null);

        getLogger(FormService.class).debug("Adding Form");

        return formDAO.insert(form) ? form : null;

    }

}
