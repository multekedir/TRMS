package com.revature.models;

import com.revature.data.EmployeeDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Department {
    private int id;
    private String name;
    private Employee head;

    public Department(String name, Employee head) {
        this.name = name;
        this.head = head;
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(ResultSet rs) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        this.setName(rs.getString("role_name".toUpperCase()));
        this.setHead(employeeDAO.getEmployeeByID(rs.getInt("manager_id".toUpperCase())));
        this.setId(rs.getInt("ID"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getHead() {
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

