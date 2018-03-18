package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.backup.dto;

import java.io.Serializable;

public class BackupSchueler implements Serializable {
    private String vorname;
    private String nachname;

    public BackupSchueler() {
    }

    public BackupSchueler(String vorname, String nachname) {
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
}
