package com.revature.models;

import com.revature.services.EmployeeService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Form {
    Status status;
    private int id;
    private double amount;
    private Employee submittedBY;
    private String description;


    /**
     * @param amount
     * @param submittedBY
     * @param description
     */
    public Form(double amount, Employee submittedBY, String description) {
        this.amount = amount;
        this.submittedBY = submittedBY;
        this.description = description;
    }

    /**
     * @param rs
     */
    public Form(ResultSet rs) throws SQLException {
        this.setSubmittedBY(EmployeeService.getEmployeeByID(rs.getInt("submitted_by".toUpperCase())));
        this.setAmount(rs.getDouble("AMOUNT"));
        this.setDescription("description".toUpperCase());
        this.setStatus(Status.valueOf(rs.getString("waiting_for".toUpperCase())));
        this.setId(rs.getInt("ID"));
    }

    /**
     * @return
     */
    public String benCOApprove() {
        status = Status.BENCO;
        return String.valueOf(status);
    }

    /**
     * @return
     */
    public String headApprove() {
        status = Status.HEAD;
        return String.valueOf(status);
    }

    /**
     * Gets amount.
     *
     * @return Value of amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets new amount.
     *
     * @param amount New value of amount.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets description.
     *
     * @return Value of description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets new description.
     *
     * @param description New value of description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets submittedBY.
     *
     * @return Value of submittedBY.
     */
    public Employee getSubmittedBY() {
        return submittedBY;
    }

    /**
     * Sets new submittedBY.
     *
     * @param submittedBY New value of submittedBY.
     */
    public void setSubmittedBY(Employee submittedBY) {
        this.submittedBY = submittedBY;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets new id.
     *
     * @param id New value of id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets status.
     *
     * @return Value of status.
     */
    public String getStatus() {
        return String.valueOf(status);
    }

    /**
     * Sets new status.
     *
     * @param status New value of status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
                ", amount=" + amount +
                ", submittedBY=" + submittedBY +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    private enum Status {
        BENCO,
        HEAD,
    }
}
