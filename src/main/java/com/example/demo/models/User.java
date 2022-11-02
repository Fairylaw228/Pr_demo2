package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    public User(String login, String password, int phone, float checks, boolean accesss) {
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.checks = checks;
        this.accesss = accesss;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login, password;
    private int phone;
    private float checks;
    private boolean accesss;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public float getChecks() {
        return checks;
    }

    public void setChecks(float checks) {
        this.checks = checks;
    }

    public boolean getAccesss() {
        return accesss;
    }

    public void setAccesss(boolean accesss) {
        this.accesss = accesss;
    }
}
