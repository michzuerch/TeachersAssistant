package com.gmail.michzuerch.LehrerVerwaltung.backend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Schueler extends AbstractEntity {
    @Column
    private String vorname;

    @Column
    private String nachname;

    @ManyToOne
    private Klasse klasse;

    @OneToMany(mappedBy = "schueler", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schulfach> schulfaches = new ArrayList<>();


    public Schueler() {
    }

    public Schueler(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Klasse getKlasse() {
        return klasse;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }

    public List<Schulfach> getSchulfaches() {
        return schulfaches;
    }

    public void setSchulfaches(List<Schulfach> schulfaches) {
        this.schulfaches = schulfaches;
    }
}
