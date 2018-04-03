package com.gmail.michzuerch.LehrerVerwaltung.backend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Lehrer extends AbstractEntity {
    @Column
    private String nachname;

    @Column
    private String vorname;

    @ManyToOne
    private Schule schule;

    @OneToMany(mappedBy = "lehrer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lektion> lektions = new ArrayList<>();

    public Lehrer() {
    }

    public Lehrer(String vorname, String nachname) {
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Schule getSchule() {
        return schule;
    }

    public void setSchule(Schule schule) {
        this.schule = schule;
    }

    public List<Lektion> getLektions() {
        return lektions;
    }

    public void setLektions(List<Lektion> lektions) {
        this.lektions = lektions;
    }
}
