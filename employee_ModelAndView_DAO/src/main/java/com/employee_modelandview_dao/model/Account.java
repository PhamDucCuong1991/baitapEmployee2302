package com.employee_modelandview_dao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    private int id;
    private String username;
    private String password;
    private String avata;

    public Account() {
    }

    public Account(int id, String username, String password, String avata) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avata = avata;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvata() {
        return avata;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }
}
