package com.mac.model;

import javax.persistence.*;

@Entity
@Table(name = "user") // redundant
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Auto_Increment
    private int id;
    private String username;
    private String password;

    public User() {
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
}
