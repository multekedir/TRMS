package com.revature.controller;

import com.revature.delegates.FrontControllerDelegate;
import org.apache.catalina.servlets.DefaultServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.revature.utility.LoggerSingleton.getLogger;

// this just gets rid of a warning that appears when you have a class
// implementing Serializable without giving it a serial version ID.
@SuppressWarnings("serial")
@WebServlet("/")
public class FrontController extends DefaultServlet {
    private final RequestDispatcher rd = new RequestDispatcher();

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uriWithoutContext = req.getRequestURI().substring(req.getContextPath().length());
        getLogger(FrontController.class).trace("URI: " + uriWithoutContext);


        FrontControllerDelegate fcd = rd.dispatch(req, resp);

        // if we are getting a static resource, we let the DefaultServlet
        // implementation handle getting that resource (actual html/css/js files)
        if (uriWithoutContext.startsWith("/static")) {
            super.doGet(req, resp);
        } else { // otherwise we are using AJAX
            if (fcd != null) {
                // call the delegate's process method
                fcd.process(req, resp);
            } else {
                // otherwise 404 error because we have nothing for the specified path
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }


        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
}
