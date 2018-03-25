package com.gmail.michzuerch.LehrerVerwaltung.backend.entity.report.fop;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.AbstractEntity;

import javax.persistence.*;

@Entity
public class ReportFOPImage extends AbstractEntity {

    @Column
    private String bezeichnung;

    @ManyToOne
    private ReportFOP reportFOP;

    @Column
    private String mimeType;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Column
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;


    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public ReportFOP getReportFOP() {
        return reportFOP;
    }

    public void setReportFOP(ReportFOP reportFOP) {
        this.reportFOP = reportFOP;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
