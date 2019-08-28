package com.example.demo.entity;

import java.io.Serializable;

/**
 * Created by demo on 2019/8/3 - 9:17
 * version:1.0.0
 */
public class User implements Serializable {
    private final static long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;

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
}
