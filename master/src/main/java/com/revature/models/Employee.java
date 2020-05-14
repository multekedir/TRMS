package com.revature.models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.StringJoiner;

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


    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        getLogger(Employee.class).debug("Set Employee first & last name");
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
        this.username = (rs.getString("user_name".toUpperCase()));
        this.password = (rs.getString("password".toUpperCase()));
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
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("firstName = " + firstName)
                .add("lastName = " + lastName)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee that = (Employee) o;

        return Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
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

    /**
     * Sets new username.
     *
     * @param username New value of username.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}