package com.revature.services;

import com.revature.data.DAOFactory;
import com.revature.data.EmployeeDAO;
import com.revature.data.FormDAO;
import com.revature.models.Form;

import java.util.Set;

import static com.revature.utility.LoggerSingleton.getLogger;

public class FormService {

    static private final EmployeeDAO employeeDAO = DAOFactory.geEmployeeDAO();
    static private final FormDAO formDAO = DAOFactory.getFormDAO();

    public static Form addForm(Form form) {
        getLogger(FormService.class).debug("Adding Form ->" + form);
        assert (form != null);
        return formDAO.insert(form) ? form : null;
    }

    public static Set<Form> filterUsingStatus(Form.Status status) {
        getLogger(FormService.class).debug("Filtering using status");
        Set<Form> result = formDAO.filter("waiting_for", String.valueOf(status));
        return result;
    }

    public static Set<Form> filterUsingEmployee(int id) {
        getLogger(FormService.class).debug("Looking for Requests Approved Super ");
        Set<Form> result = formDAO.filter("submitted_by", id);
        return result;
    }

}
