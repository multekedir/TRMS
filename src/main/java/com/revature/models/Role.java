package com.revature.models;

public class Role {
    private String name;
    private String supervisor;

    public Role(String name, String supervisor) {
        this.name = name;
        this.supervisor = supervisor;
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
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * Sets new supervisor.
     *
     * @param supervisor New value of supervisor.
     */
    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}
