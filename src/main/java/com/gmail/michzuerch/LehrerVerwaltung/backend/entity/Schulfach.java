package com.gmail.michzuerch.LehrerVerwaltung.backend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Schulfach extends AbstractEntity {

    @Column
    private String bezeichnung;

    @ManyToOne
    private Schueler schueler;

    @OneToMany(mappedBy = "schulfach", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schulnote> schulnotes = new ArrayList<>();

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public List<Schulnote> getSchulnotes() {
        return schulnotes;
    }

    public void setSchulnotes(List<Schulnote> schulnotes) {
        this.schulnotes = schulnotes;
    }

    public Schueler getSchueler() {
        return schueler;
    }

    public void setSchueler(Schueler schueler) {
        this.schueler = schueler;
    }
}
