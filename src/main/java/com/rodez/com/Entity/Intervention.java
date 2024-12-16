package com.rodez.com.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@Entity
@Table(name="INTERVENTION")
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idIntervention")
    private int idIntervention;
    // date de validation de la demande  non pas de l'intervention
    private Timestamp date;
    private Timestamp creationDate;
    private Timestamp realisationDate;
    private Timestamp affectationDate;
    private String priority;
    private String status = "en attente";
    @NotBlank(message = "Une intervention doit avoir une description")
    private String description;

    @ManyToOne
    @JoinColumn(name="idIntervenant")
    private User idIntervenant;

    @ManyToOne
    @JoinColumn(name="idUser")
    private User idUser;
    @OneToOne
    @JoinColumn(name="idLocation")
    @NotNull(message = "La localisation ne doit pas être null")
    private Location idLocation;




    public User getIdIntervenant() {
        return idIntervenant;
    }

    public void setIdIntervenant(User idIntervenant) {
        this.idIntervenant = idIntervenant;
    }
    public int getIdIntervention() {
        return idIntervention;
    }

    public void setIdIntervention(int idIntervention) {
        this.idIntervention = idIntervention;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getRealisationDate() {
        return realisationDate;
    }

    public void setRealisationDate(Timestamp realisationDate) {
        this.realisationDate = realisationDate;
    }

    public Timestamp getAffectationDate() {
        return affectationDate;
    }

    public void setAffectationDate(Timestamp affectationDate) {
        this.affectationDate = affectationDate;
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

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Location getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Location idLocation) {
        this.idLocation = idLocation;
    }
}
