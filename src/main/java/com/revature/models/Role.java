package com.revature.models;

import com.revature.data.DAOFactory;
import com.revature.data.EmployeeDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Role {
    private String name;
    private Employee supervisor;
    private int id;
    private int headsTableID;
    private int supervisorsTableID;

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

    public Role() {

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

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", supervisor=" + supervisor +
                ", id=" + id +
                '}';
    }

    public void setupManger(ResultSet rs) throws SQLException {
        Role department = DAOFactory.getRoleDAO().getRoleByID(rs.getInt("role".toUpperCase()));
        this.name = department.getName();
        this.id = department.getId();
        this.setSupervisor(DAOFactory.geEmployeeDAO().getEmployeeByID(rs.getInt("supervisor".toUpperCase())));
        this.setId(rs.getInt("ID"));
    }

    public int getHeadsTableID() {
        return this.headsTableID;
    }

    public void setHeadsTableID(int headsTableID) {
        this.headsTableID = headsTableID;
    }

    public int getSupervisorsTableID() {
        return this.supervisorsTableID;
    }

    public void setSupervisorsTableID(int supervisorsTableID) {
        this.supervisorsTableID = supervisorsTableID;
    }

    public boolean hasSuper() {
        return this.supervisor != null;
    }
}
