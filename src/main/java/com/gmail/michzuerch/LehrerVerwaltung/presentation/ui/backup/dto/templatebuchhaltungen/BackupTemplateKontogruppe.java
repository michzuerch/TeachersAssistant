package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.backup.dto.templatebuchhaltungen;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by michzuerch on 16.11.15.
 */
public class BackupTemplateKontogruppe {
    private String bezeichnung;
    private String kontonummer;
    private Long id;
    private Set<BackupTemplateKonto> backupTemplateKontos = new HashSet<>();

    public BackupTemplateKontogruppe() {
    }

    public BackupTemplateKontogruppe(String bezeichnung, String kontonummer) {
        this.bezeichnung = bezeichnung;
        this.kontonummer = kontonummer;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<BackupTemplateKonto> getBackupTemplateKontos() {
        return backupTemplateKontos;
    }

    public void setBackupTemplateKontos(Set<BackupTemplateKonto> backupTemplateKontos) {
        this.backupTemplateKontos = backupTemplateKontos;
    }
}
