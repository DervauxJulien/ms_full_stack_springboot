package com.rodez.com.Entity;

import com.rodez.com.Validator.ImmatriculationValidatorInterface;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="\"USER\"")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private int idUser;
    @NotNull(message = "Immatriculation obligatoire")

    @ImmatriculationValidatorInterface(regex = "[A,U,P,F][0-9]{9}")
    @Column(name="registration")
    private Integer registration;
    @NotBlank(message = "Nom obligatoire")
    @Column(name="lastname")
    private String lastname;
    @NotBlank(message = "Prénom obligatoire")
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

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer resgistration) {
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
