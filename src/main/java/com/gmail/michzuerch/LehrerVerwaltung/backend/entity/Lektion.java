package com.gmail.michzuerch.LehrerVerwaltung.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Lektion extends AbstractEntity {
    @Column
    @NotNull
    private String bezeichnung;

    @Column
    @NotNull
    private LocalDateTime start;

    @Column
    @NotNull
    private LocalDateTime ende;

    @ManyToOne
    private Schulfach schulfach;

    @ManyToOne
    private Lehrer lehrer;

    @ManyToOne
    private Schulraum schulraum;

    @ManyToOne
    private Klasse klasse;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnde() {
        return ende;
    }

    public void setEnde(LocalDateTime ende) {
        this.ende = ende;
    }

    public Schulfach getSchulfach() {
        return schulfach;
    }

    public void setSchulfach(Schulfach schulfach) {
        this.schulfach = schulfach;
    }

    public Lehrer getLehrer() {
        return lehrer;
    }

    public void setLehrer(Lehrer lehrer) {
        this.lehrer = lehrer;
    }

    public Schulraum getSchulraum() {
        return schulraum;
    }

    public void setSchulraum(Schulraum schulraum) {
        this.schulraum = schulraum;
    }

    public Klasse getKlasse() {
        return klasse;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }
}
