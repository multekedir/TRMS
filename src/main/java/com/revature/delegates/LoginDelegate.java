package com.revature.delegates;

import com.revature.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import org.apache.log4j.Logger;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.revature.beans.Person;
//import com.revature.services.PersonService;
//import com.revature.services.PersonServiceImpl;
//import com.revature.utils.JsonParseUtil;
//import com.revature.utils.LogUtil;

public class LoginDelegate implements FrontControllerDelegate {

//    private PersonService pServ = new PersonServiceImpl();
    // jackson library marshals and unmarshals data to/from JSON
//    private ObjectMapper objMapper = new ObjectMapper();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        log.trace(req.getMethod() + " request received by Login Delegate.");
//        HttpSession session = req.getSession();
//
//        String path = (String) req.getAttribute("path");
//        log.trace("Login with path " + path);
//
//        if (path == null || path.equals("")) {
//            switch(req.getMethod()) {
//                case "GET": // get the current logged in user
//                    checkLogIn(req, resp);
//                    break;
//                case "POST": // log in a user
//                    Person p = (Person) session.getAttribute("person");
//                    if (p != null) {
//                        getLoggedInPerson(resp, p);
//                    } else {
//                        checkLogIn(req, resp);
//                    }
//                    break;
//                case "DELETE": // log out a user
//                    session.invalidate();
//                    log.trace("Person logged out");
//                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
//                    break;
//                default:
//                    resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//                    break;
//            }
//        } else if (path.contains("register")) {
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

    private void getLoggedInPerson(HttpServletResponse resp, Employee p) throws IOException {
//        resp.setStatus(200);
//        String personString = objMapper.writeValueAsString(p);
//        StringBuilder sb = new StringBuilder("{\"person\":");
//        sb.append(personString);
//        sb.append("}");
//        resp.getWriter().write(sb.toString());
    }

    private void checkLogIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        log.trace("Person is logging in");
//
//        HttpSession session = req.getSession();
//
//        Person p = (Person) session.getAttribute("person");
//        if (p != null) {
//            getLoggedInPerson(resp, p);
//        } else {
//            String username = req.getParameter("user");
//            String password = req.getParameter("pass");
//            log.debug("Username " + username + " and password " + password);
//            p = pServ.getPersonByUsernameAndPassword(username, password);
//            if (p != null) {
//                log.trace("Person logged in successfully");
//                session.setAttribute("person", p);
//                getLoggedInPerson(resp, p);
//            } else {
//                resp.sendError(404, "No user found with that username/password combo.");
//            }
//        }
    }

}
