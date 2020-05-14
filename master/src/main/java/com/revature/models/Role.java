package com.revature.models;

import com.revature.data.EmployeeDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Role {
    private String name;
    private Employee supervisor;
    private int id;

    public Role(String name, Employee supervisor) {
        this.name = name;
        this.supervisor = supervisor;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(ResultSet rs) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        this.setName(rs.getString("role_name".toUpperCase()));
//        this.setSupervisor(employeeDAO.getEmployeeByID(rs.getInt("supervisor".toUpperCase())));
        this.setId(rs.getInt("ID"));
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets supervisor.
     *
     * @return Value of supervisor.
     */
    public Employee getSupervisor() {
        return supervisor;
    }

    /**
     * Sets new supervisor.
     *
     * @param supervisor New value of supervisor.
     */
    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
