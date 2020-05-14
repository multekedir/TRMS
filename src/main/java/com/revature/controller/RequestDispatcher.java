package com.revature.controller;

import com.revature.delegates.FrontControllerDelegate;
import com.revature.delegates.LoginDelegate;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// within the front controller design pattern,
// this is our "handler"/"dispatcher"
public class RequestDispatcher {
    private final Logger log = Logger.getLogger(RequestDispatcher.class);
    private final Map<String, FrontControllerDelegate> delegateMap;

    {
        // instance block
        delegateMap = new HashMap<String, FrontControllerDelegate>();

        // map.put(key, value)
        delegateMap.put("login", new LoginDelegate());
//        delegateMap.put("cat", new CatDelegate());
        delegateMap.put("index", (req, resp) -> {
            req.getRequestDispatcher("/static/index.html").forward(req, resp);
        });
        delegateMap.put("", (req, resp) -> {
            req.getRequestDispatcher("/static/index.html").forward(req, resp);
        });
        delegateMap.put("register", (req, resp) -> {
            req.getRequestDispatcher("/static/register.html").forward(req, resp);
        });
        delegateMap.put("viewcats", (req, resp) -> {
            req.getRequestDispatcher("/static/viewcats.html").forward(req, resp);
        });
        delegateMap.put("mycats", (req, resp) -> {
            req.getRequestDispatcher("/static/mycats.html").forward(req, resp);
        });
    }

    public FrontControllerDelegate dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("Getting request delegate for " + req.getRequestURI());

        addCorsHeader(req.getRequestURI(), resp);
        if ("OPTIONS".equals(req.getMethod())) {
            // empty delegate
            return (r1, r2) -> {
            };
        }

        StringBuilder uriString = new StringBuilder(req.getRequestURI());
        // this just gets rid of the first part of the URL so that we
        // are left with the part that we want
        uriString.replace(0, req.getContextPath().length() + 1, "");

        // if there is a / in the leftover part of the URL
        if (uriString.indexOf("/") != -1) {
            req.setAttribute("path", uriString.substring(uriString.indexOf("/") + 1));
            uriString.replace(uriString.indexOf("/"), uriString.length(), "");
        }

        return delegateMap.get(uriString.toString());
    }

    private void addCorsHeader(String requestURI, HttpServletResponse resp) {
        log.trace("Adding CORS headers");
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.addHeader("Vary", "Origin");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.addHeader("Access-Control-Max-Age", "1728000");
        resp.addHeader("Produces", "application/json");
    }
}
