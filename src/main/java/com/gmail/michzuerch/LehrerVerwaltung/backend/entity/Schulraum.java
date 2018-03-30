package com.gmail.michzuerch.LehrerVerwaltung.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Schulraum extends AbstractEntity {

    @Column
    private String bezeichnung;

    @ManyToOne
    private Schule schule;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Schule getSchule() {
        return schule;
    }

    public void setSchule(Schule schule) {
        this.schule = schule;
    }
}
