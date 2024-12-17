package com.rodez.com.Entity;

import com.rodez.com.Validator.ImmatriculationValidatorInterface;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name="\"USER\"")

public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private int idUser;
    @ImmatriculationValidatorInterface(regex = "^[A,U,P,F]+[0-9]{9}$", message = "Mauvais format d'immatriculation")
    @Column(name="registration")
    private String registration;
    @ImmatriculationValidatorInterface(regex = "^[A-Za-z]+$", message = "Mauvais format de nom")
    @Column(name="lastname")
    private String lastname;
    @ImmatriculationValidatorInterface(regex = "^[A-Za-z]+$", message = "Mauvais format de prénom")
    @Column(name="firstname")
    private String firstname;

    @Column(name="roleUser")
    private String roleUser;
//    @ImmatriculationValidatorInterface(regex = "[a-zA-Z0-9/*@-_?!$]+", message = "Wrong password")
    @NotBlank(message = "Password can't be empty")
    @Column(name="passwordUser")
    private String passwordUser;


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String resgistration) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return passwordUser;
    }

    @Override
    public String getUsername() {
        return firstname + lastname;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return null;
    }




//    @Override
//    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
//    }
}
