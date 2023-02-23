package com.employee_modelandview_dao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Branch {
    @Id
    private int id;
    private String branch;

    public Branch() {
    }

    public Branch(int id, String branch) {
        this.id = id;
        this.branch = branch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
