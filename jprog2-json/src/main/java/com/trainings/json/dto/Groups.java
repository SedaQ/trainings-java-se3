package com.trainings.json.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Seda
 */
public class Groups {

    private String name;
    private List<Roles> roles = new ArrayList<>();

    public Groups(){}

    public Groups(String name, List<Roles> roles) {
        this.name = name;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
