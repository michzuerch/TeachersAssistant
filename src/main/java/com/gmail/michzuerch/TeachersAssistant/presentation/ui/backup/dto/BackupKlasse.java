package com.gmail.michzuerch.TeachersAssistant.presentation.ui.backup.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BackupKlasse implements Serializable {

    private String bezeichnung;
    private Set<BackupSchueler> backupSchuelers = new HashSet<>();

    public BackupKlasse() {
    }

    public BackupKlasse(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Set<BackupSchueler> getBackupSchuelers() {
        return backupSchuelers;
    }

    public void setBackupSchuelers(Set<BackupSchueler> backupSchuelers) {
        this.backupSchuelers = backupSchuelers;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}
