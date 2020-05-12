package com.revature.models;


import java.util.Objects;
import java.util.StringJoiner;

import static com.revature.utility.LoggerSingleton.getLogger;

/**
 * The type Employee.
 */
public class Employee {
    // TODO configure employee
    private int id;
    private String firstName;
    private String lastName;
    private Role role;


    public Employee() {
        this.firstName = "firstName";
        this.lastName = "lastName";
        getLogger(Employee.class).info("Created Employee with default constructor");
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        getLogger(Employee.class).info("Created Employee");
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

    public void setID(int id) {
        this.id = id;
    }

    public enum Role {
        Employee, Customer, Manager
    }

}