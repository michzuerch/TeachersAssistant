package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.backup.dto.templatebuchhaltungen;


/**
 * Created by michzuerch on 16.11.15.
 */
public class BackupTemplateMehrwertsteuercode {
    private Long id;
    private String bezeichnung;
    private String code;
    private Double prozent;
    private boolean verkauf;
    private Long konto;

    public BackupTemplateMehrwertsteuercode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getProzent() {
        return prozent;
    }

    public void setProzent(Double prozent) {
        this.prozent = prozent;
    }

    public boolean isVerkauf() {
        return verkauf;
    }

    public void setVerkauf(boolean verkauf) {
        this.verkauf = verkauf;
    }

    public Long getKonto() {
        return konto;
    }

    public void setKonto(Long konto) {
        this.konto = konto;
    }
}
