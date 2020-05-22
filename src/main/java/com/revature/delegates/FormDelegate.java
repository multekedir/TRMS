package com.revature.delegates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Form;
import com.revature.services.FormService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static com.revature.services.FormService.filterUsingEmployee;
import static com.revature.utility.LoggerSingleton.getLogger;
import static com.revature.utility.ServletUtil.doJson;

public class FormDelegate implements FrontControllerDelegate {
    private final ObjectMapper objMapper = new ObjectMapper();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getLogger(LoginDelegate.class).debug(req.getMethod() + " request received by FormDelegate.");


        String path = (String) req.getAttribute("path");
        getLogger(FormDelegate.class).debug("FormDelegate with path " + path);

        if (path.equals("add")) {
            switch (req.getMethod()) {
                case "POST": // submit form
                    getLogger(FormDelegate.class).debug("Submitting form");
                    submitForm(req, resp);
                    break;
                default:
                    getLogger(FormDelegate.class).error("METHOD_NOT_ALLOWED");
                    resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    break;
            }
        } else if (path.contains("pending")) {
            switch (req.getMethod()) {
                case "GET": // get pending forms
                    int id = Integer.parseInt(path.replace("pending/", ""));
                    sendMyForms(req, resp, id);
                    break;
                default:
                    getLogger(FormDelegate.class).error("METHOD_NOT_ALLOWED");
                    resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    break;
            }
        }
    }

    private void submitForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getLogger(FormDelegate.class).debug("New requests");
        JSONObject jsonObj = doJson(req);
        String description = jsonObj.getString("description");
        Double amount = jsonObj.getDouble("amount");
        int id = jsonObj.getInt("ID");
        getLogger(FormDelegate.class).debug("Action performed by" + id);
        Form form = new Form(amount, id, description);
        FormService.addForm(form);
        getLogger(FormDelegate.class).debug("form submitted");
        resp.setContentType("application/json");
        resp.getWriter().write(objMapper.writeValueAsString(form));
    }

    private void sendMyForms(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {
        getLogger(FormDelegate.class).debug("Getting requests");
        getLogger(FormDelegate.class).debug("Getting the list of Pending forms for " + id);
        Set<Form> pendingForms = filterUsingEmployee(id);
        String pendingFormsJSON = objMapper.writeValueAsString(pendingForms);
        getLogger(FormDelegate.class).debug(pendingFormsJSON);
        resp.getWriter().write(pendingFormsJSON);
    }

}
