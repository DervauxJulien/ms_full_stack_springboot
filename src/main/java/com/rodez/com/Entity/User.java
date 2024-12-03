package com.rodez.com.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="\"USER\"")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private int id_user;
    @Column(name="registration")
    private int registration;
    @Column(name="lastname")
    private String lastname;
    @Column(name="firstname")
    private String firstname;

    @Column(name="role_user")
    private String role_user;
    @Column(name="password_user")
    private String password_user;


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int resgistration) {
        this.registration = resgistration;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getRole_user() {
        return role_user;
    }

    public void setRole_user(String role_user) {
        this.role_user = role_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }
}
