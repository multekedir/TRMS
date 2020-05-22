package com.revature.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Form {
    static Status status;
    private int id;
    private double amount;
    private int submittedBY;
    private String description;


    /**
     * @param amount
     * @param submittedBY
     * @param description
     */
    public Form(double amount, int submittedBY, String description) {
        this.amount = amount;
        this.submittedBY = submittedBY;
        this.description = description;
    }

    /**
     * @param rs
     */
    public Form(ResultSet rs) throws SQLException {
        this.setSubmittedBY(rs.getInt("submitted_by".toUpperCase()));
        this.setAmount(rs.getDouble("AMOUNT"));
        this.setDescription(rs.getString("DESCRIPTION"));
        String s = rs.getString("waiting_for".toUpperCase());
        status = "null".equals(s) ? null : Status.valueOf(s);
        this.setId(rs.getInt("ID"));
    }

    /**
     * Gets status.
     *
     * @return Value of status.
     */
    public static String getStatus() {
        return String.valueOf(status);
    }

    /**
     * @return
     */
    public String benCOApprove() {
        status = Status.HEAD;
        return String.valueOf(status);
    }

    /**
     * @return
     */
    public String headApprove() {
        status = Status.APPROVED;
        return String.valueOf(status);
    }

    /**
     * @return
     */
    public String DENY() {
        status = Status.DENIED;
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
     * @return
     */
    public String supervisorApprove() {
        status = Status.BENCO;
        return String.valueOf(status);
    }

    /**
     * Gets submittedBY.
     *
     * @return Value of submittedBY.
     */
    public int getSubmittedBY() {
        return submittedBY;
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
     * Sets new submittedBY.
     *
     * @param submittedBY New value of submittedBY.
     */
    public void setSubmittedBY(int submittedBY) {
        this.submittedBY = submittedBY;
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

    public enum Status {
        SUPERVISOR,
        BENCO,
        HEAD,
        APPROVED,
        DENIED
    }
}
