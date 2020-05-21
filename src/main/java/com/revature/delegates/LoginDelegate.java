package com.revature.delegates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.services.LoginService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.revature.utility.LoggerSingleton.getLogger;
import static com.revature.utility.ServletUtil.doJson;
import static com.revature.utility.ServletUtil.sendError;


public class LoginDelegate implements FrontControllerDelegate {

    private final ObjectMapper objMapper = new ObjectMapper();


    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getLogger(LoginDelegate.class).debug(req.getMethod() + " request received by LoginService Delegate.");
        HttpSession session = req.getSession();

        String path = (String) req.getAttribute("path");
        getLogger(LoginDelegate.class).debug("LoginService with path " + path);

        if (path == null || path.equals("")) {
            switch (req.getMethod()) {
                case "GET": // get the current logged in user
                    checkLogIn(req, resp);
                    break;
                case "POST": // log in a user
                    Employee e = (Employee) session.getAttribute("employee");
                    if (e != null) {
                        getLoggedInEmployee(resp, e);
                    } else {
                        checkLogIn(req, resp);
                    }
                    break;
                case "DELETE": // log out a user
                    session.invalidate();
                    getLogger(LoginDelegate.class).trace("Employee logged out");
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    break;
            }
        }
    }

    private void getLoggedInEmployee(HttpServletResponse resp, Employee e) throws IOException {
        resp.setStatus(200);
        String employeeString = objMapper.writeValueAsString(e);
        StringBuilder sb = new StringBuilder("{\"employee\":");
        sb.append(employeeString);
        sb.append("}");
        resp.setContentType("application/json");
        resp.getWriter().write(sb.toString());
    }

    private void checkLogIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getLogger(LoginDelegate.class).debug("Employee is logging in");
        JSONObject jsonObj = doJson(req);
        HttpSession session = req.getSession();
        Employee e = (Employee) session.getAttribute("employee");
        if (e != null) {
            getLoggedInEmployee(resp, e);
        } else {
            String username = jsonObj.getString("user");
            String password = jsonObj.getString("pass");
            getLogger(LoginDelegate.class).debug("Username " + username + " and password " + password);
            e = LoginService.login(username, password);
            if (e != null) {
                getLogger(LoginDelegate.class).info("Employee logged in successfully");
                session.setAttribute("employee", e);
                getLoggedInEmployee(resp, e);
            } else {

                getLogger(LoginDelegate.class).error(sendError(resp, 404, "No user found with that username/password combo."));
                session.invalidate();
            }
        }
    }



}
