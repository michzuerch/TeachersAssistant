package com.gmail.michzuerch.LehrerVerwaltung.backend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Schulraum extends AbstractEntity {

    @Column
    private String bezeichnung;

    @ManyToOne
    private Schule schule;

    @OneToMany(mappedBy = "schulraum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lektion> lektions = new ArrayList<>();

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

    public List<Lektion> getLektions() {
        return lektions;
    }

    public void setLektions(List<Lektion> lektions) {
        this.lektions = lektions;
    }
}
