package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.backup.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BackupSchule implements Serializable {
    private String bezeichnung;
    private String ort;
    private Set<BackupKlasse> backupKlasses = new HashSet<>();
    private Set<BackupLehrer> backupLehrers = new HashSet<>();

    public BackupSchule() {
    }

    public BackupSchule(String bezeichnung, String ort) {
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

    public Set<BackupKlasse> getBackupKlasses() {
        return backupKlasses;
    }

    public void setBackupKlasses(Set<BackupKlasse> backupKlasses) {
        this.backupKlasses = backupKlasses;
    }

    public Set<BackupLehrer> getBackupLehrers() {
        return backupLehrers;
    }

    public void setBackupLehrers(Set<BackupLehrer> backupLehrers) {
        this.backupLehrers = backupLehrers;
    }
}
