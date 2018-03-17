package com.gmail.michzuerch.LehrerVerwaltung.backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Schule extends AbstractEntity {
    @Column
    @NotNull
    @NotBlank
    private String bezeichung;

    @Column
    private String ort;

    @OneToMany(mappedBy = "schule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Klasse> klasses = new ArrayList<>();

    @OneToMany(mappedBy = "schule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lehrer> lehrers = new ArrayList<>();

    public String getBezeichung() {
        return bezeichung;
    }

    public void setBezeichung(String bezeichung) {
        this.bezeichung = bezeichung;
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
