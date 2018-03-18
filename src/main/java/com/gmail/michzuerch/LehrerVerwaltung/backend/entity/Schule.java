package com.gmail.michzuerch.LehrerVerwaltung.backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Schule extends AbstractEntity {
    @Column
    private String bezeichnung;

    @Column
    private String ort;

    @OneToMany(mappedBy = "schule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Klasse> klasses = new ArrayList<>();

    @OneToMany(mappedBy = "schule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lehrer> lehrers = new ArrayList<>();

    public Schule() {
    }

    public Schule(String bezeichnung, String ort) {
        this.bezeichnung = bezeichnung;
        this.ort = ort;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public List<Klasse> getKlasses() {
        return klasses;
    }

    public void setKlasses(List<Klasse> klasses) {
        this.klasses = klasses;
    }

    public List<Lehrer> getLehrers() {
        return lehrers;
    }

    public void setLehrers(List<Lehrer> lehrers) {
        this.lehrers = lehrers;
    }
}
