package com.revature.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/")
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String htmlResponse = "<html><h3>Hello " + name + "<h3></html>";
        PrintWriter writer = resp.getWriter();
        String filePath = req.getServletContext().getRealPath("index.html");
        String contents = new String(Files.readAllBytes(Paths.get(filePath)));
        writer.write(contents);
    }


}

