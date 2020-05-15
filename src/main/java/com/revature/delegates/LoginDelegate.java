package com.revature.delegates;

import com.revature.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.revature.utility.LoggerSingleton.getLogger;

//import org.apache.log4j.Logger;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.revature.beans.Person;
//import com.revature.services.PersonService;
//import com.revature.services.PersonServiceImpl;
//import com.revature.utils.JsonParseUtil;
//import com.revature.utils.LogUtil;

public class LoginDelegate implements FrontControllerDelegate {



    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        getLogger(LoginDelegate.class).debug(req.getMethod() + " request received by LoginService Delegate.");
//        HttpSession session = req.getSession();
//
//        String path = (String) req.getAttribute("path");
//        getLogger(LoginDelegate.class).debug("LoginService with path " + path);
//
//       if (path == null || path.equals("")) {
//            switch(req.getMethod()) {
//                    case "GET": // get the current logged in user
//                    checkLogIn(req, resp);
//                    break;
//                case "POST": // log in a user
//                    Employee e = (Employee) session.getAttribute("employee");
//                    if (e != null) {
//                        getLoggedInPerson(resp, e);
//                    } else {
//                        checkLogIn(req, resp);
//                    }
//                    break;
//                case "DELETE": // log out a user
//                    session.invalidate();
//                    getLogger(LoginDelegate.class).trace("Person logged out");
//                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
//                    break;
//                default:
//                    resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//                    break;
//            }
//       } else if (path.contains("register")) {
//            if (req.getMethod() == "POST") {
//                log.trace("Registering a user");
//                Person p = JsonParseUtil.parseJsonInput(req.getInputStream(), Person.class, resp);
//                if (p != null) {
//                    try {
//                        p.setId(pServ.addPerson(p));
//                        session.setAttribute("person", p);
//                        resp.setStatus(201); // created
//                        resp.getWriter().write(objMapper.writeValueAsString(p));
//                    } catch (Exception e) {
//                        LogUtil.logException(e, LoginDelegate.class);
//                        resp.sendError(409); // conflict
//                    }
//                } else {
//                    log.trace("No person data submitted");
//                    resp.sendError(400); // bad request
//                }
//            } else {
//
//            }
//        }
    }

    private void getLoggedInEmployee(HttpServletResponse resp, Employee p) throws IOException {
//        resp.setStatus(200);
//        String personString = objMapper.writeValueAsString(p);
//        StringBuilder sb = new StringBuilder("{\"person\":");
//        sb.append(personString);
//        sb.append("}");
//        resp.getWriter().write(sb.toString());
    }

    private void checkLogIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getLogger(LoginDelegate.class).debug("Person is logging in");

        HttpSession session = req.getSession();

        Employee e = (Employee) session.getAttribute("person");
        if (e != null) {
            getLoggedInEmployee(resp, e);
        } else {
            String username = req.getParameter("user");
            String password = req.getParameter("pass");
            getLogger(LoginDelegate.class).debug("Username " + username + " and password " + password);
//            p = pServ.getEmployeeByUsernameAndPassword(username, password);
//            if (p != null) {
//                log.trace("Employee logged in successfully");
//                session.setAttribute("person", p);
//                getLoggedInEmployee(resp, p);
//            } else {
//                resp.sendError(404, "No user found with that username/password combo.");
//            }
        }
    }

}
