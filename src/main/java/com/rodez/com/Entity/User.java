package com.rodez.com.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="\"USER\"")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private int idUser;
    @Column(name="registration")
    private int registration;
    @Column(name="lastname")
    private String lastname;
    @Column(name="firstname")
    private String firstname;

    @Column(name="roleUser")
    private String roleUser;
    @Column(name="passwordUser")
    private String passwordUser;


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }
}
