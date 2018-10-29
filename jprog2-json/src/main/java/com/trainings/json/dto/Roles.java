package com.trainings.json.dto;

/**
 *
 * @author Pavel Seda
 */
public class Roles {

    private String role;

    public Roles(){}

    public Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "role='" + role + '\'' +
                '}';
    }
}
