package com.gmail.michzuerch.LehrerVerwaltung.backend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Klasse extends AbstractEntity {

    @Column
    private String bezeichnung;

    @ManyToOne
    private Schule schule;

    @OneToMany(mappedBy = "klasse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schueler> schuelers = new ArrayList<>();


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

    public List<Schueler> getSchuelers() {
        return schuelers;
    }

    public void setSchuelers(List<Schueler> schuelers) {
        this.schuelers = schuelers;
    }
}
