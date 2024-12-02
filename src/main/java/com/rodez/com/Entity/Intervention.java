package com.rodez.com.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="INTERVENTION")
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_intervention;
    // date de validation de la demande  non pas de l'intervention
    private Timestamp date;
    private Timestamp creation_date;
    private Timestamp realisation_date;
    private Timestamp affectation_date;
    private String priority;
    private String status;
    private String description;
    @ManyToOne
    @JoinColumn(name="id_user")
    private User id_user;
    @OneToOne
    @JoinColumn(name="id_location")
    private Location id_location;

    public int getId_intervention() {
        return id_intervention;
    }

    public void setId_intervention(int id_intervention) {
        this.id_intervention = id_intervention;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Timestamp creation_date) {
        this.creation_date = creation_date;
    }

    public Timestamp getRealisation_date() {
        return realisation_date;
    }

    public void setRealisation_date(Timestamp realisation_date) {
        this.realisation_date = realisation_date;
    }

    public Timestamp getAffectation_date() {
        return affectation_date;
    }

    public void setAffectation_date(Timestamp affectation_date) {
        this.affectation_date = affectation_date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public Location getId_location() {
        return id_location;
    }

    public void setId_location(Location id_location) {
        this.id_location = id_location;
    }
}
