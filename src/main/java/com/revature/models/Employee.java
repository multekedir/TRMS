package com.revature.models;


import com.revature.data.DAOFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static com.revature.utility.LoggerSingleton.getLogger;

/**
 * The type Employee.
 */
public class Employee {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private Department department;


    public Employee(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        getLogger(Employee.class).debug("Created new employee");
    }

    public Employee(String username, String firstName, String lastName, String password, Role role, Department department) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.department = department;
    }

    public Employee(ResultSet rs) throws SQLException {
        this.setFirstName(rs.getString("first_name".toUpperCase()));
        this.setLastName(rs.getString("last_name".toUpperCase()));
        this.username = (rs.getString("username".toUpperCase()));
        this.password = (rs.getString("password".toUpperCase()));

        this.role = DAOFactory.getRoleDAO().getRoleByID(rs.getInt("role".toUpperCase()));
        this.department = DAOFactory.getDepartmentDAO().getDepartmentByID(rs.getInt("department".toUpperCase()));
        this.setId(rs.getInt("ID"));
    }

    /**
     * Print name.
     */
    public void printName() {
        System.out.println(String.format("Employee[firstName = %s, lastName = %s]", this.getFirstName(),
                this.getLastName()));
    }

    /**
     * Gets lastName.
     *
     * @return Value of lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets new lastName.
     *
     * @param lastName New value of lastName.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets firstName.
     *
     * @return Value of firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets new firstName.
     *
     * @param firstName New value of firstName.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get full name string.
     *
     * @return the string
     */
    String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return username.equals(employee.username) &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName);
    }

    public int getID() {
        return id;
    }

    /**
     * Gets password.
     *
     * @return Value of password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets new password.
     *
     * @param password New value of password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets department.
     *
     * @return Value of department.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets new department.
     *
     * @param department New value of department.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Gets role.
     *
     * @return Value of role.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets new role.
     *
     * @param role New value of role.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets username.
     *
     * @return Value of username.
     */
    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", department=" + department +
                '}';
    }

    /**
     * Sets new username.
     *
     * @param username New value of username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}