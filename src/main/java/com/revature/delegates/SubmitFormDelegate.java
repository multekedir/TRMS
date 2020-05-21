package com.revature.delegates;

import com.revature.models.Employee;
import com.revature.models.Form;
import com.revature.services.EmployeeService;
import com.revature.services.FormService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.revature.utility.LoggerSingleton.getLogger;
import static com.revature.utility.ServletUtil.doJson;

public class SubmitFormDelegate implements FrontControllerDelegate {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getLogger(LoginDelegate.class).debug(req.getMethod() + " request received by SubmitFormDelegate.");


        String path = (String) req.getAttribute("path");
        getLogger(SubmitFormDelegate.class).debug("SubmitFormDelegate with path " + path);

        if (path.equals("add")) {
            switch (req.getMethod()) {
                case "POST": // submit form
                    getLogger(SubmitFormDelegate.class).debug("submtting form");
                    submitForm(req, resp);
                    break;
                default:
                    getLogger(SubmitFormDelegate.class).error("METHOD_NOT_ALLOWED");
                    resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    break;
            }
        }
    }

    private void submitForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jsonObj = doJson(req);
        System.out.println("Hello" + jsonObj.toString());
        String description = jsonObj.getString("description");
        Double amount = jsonObj.getDouble("amount");
        int id = jsonObj.getInt("ID");
        Employee e = EmployeeService.getEmployeeByID(id);
        getLogger(SubmitFormDelegate.class).debug("Action performed by" + e);
        Form form = new Form(amount, e, description);
        FormService.addForm(new Form(amount, e, description));
        getLogger(SubmitFormDelegate.class).debug("form submitted");
        resp.getWriter().write(form.toString());
    }

}
