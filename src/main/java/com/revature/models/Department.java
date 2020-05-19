
package com.revature.models;

import com.revature.data.DAOFactory;
import com.revature.data.EmployeeDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Department {
    private int id;
    private String name;
    private Employee head;
    private int headsTableID;

    public Department() {
    }

    public Department(String name, Employee head) {
        this.name = name;
        this.head = head;
    }

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", head=" + head +
                ", headsTableID=" + headsTableID +
                '}';
    }

    public Department(ResultSet rs) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        this.setName(rs.getString("name".toUpperCase()));
        //this.setHead(employeeDAO.getEmployeeByID(rs.getInt("manager_id".toUpperCase())));
        this.setId(rs.getInt("ID"));
    }

    public void setupManger(ResultSet rs) throws SQLException {
        Department department = DAOFactory.getDepartmentDAO().getDepartmentByID(rs.getInt("department".toUpperCase()));
        this.name = department.getName();
        this.id = department.getId();
        this.setHead(DAOFactory.geEmployeeDAO().getEmployeeByID(rs.getInt("manager".toUpperCase())));
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

    /**
     * Gets headsTableID.
     *
     * @return Value of headsTableID.
     */
    public int getHeadsTableID() {
        return headsTableID;
    }

    /**
     * Sets new headsTableID.
     *
     * @param headsTableID New value of headsTableID.
     */
    public void setHeadsTableID(int headsTableID) {
        this.headsTableID = headsTableID;
    }

    public boolean hasHead() {
        System.out.println(this.head);
        return this.head != null;
    }
}

