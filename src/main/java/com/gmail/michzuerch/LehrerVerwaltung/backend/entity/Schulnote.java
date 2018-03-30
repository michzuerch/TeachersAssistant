package com.gmail.michzuerch.LehrerVerwaltung.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Schulnote extends AbstractEntity {
    @Column
    private String bezeichnung;

    @ManyToOne
    private Schulfach schulfach;

    @Column
    private LocalDateTime localDateTime;

    @Column
    private float note;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Schulfach getSchulfach() {
        return schulfach;
    }

    public void setSchulfach(Schulfach schulfach) {
        this.schulfach = schulfach;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
