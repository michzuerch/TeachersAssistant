package com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.report.jasper;


import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.AbstractEntity;

import javax.persistence.*;

@Entity
public class ReportJasperImage extends AbstractEntity {

    @Column
    private String bezeichnung;

    @ManyToOne
    private ReportJasper reportJasper;

    @Column
    private String mimeType;

    @Column
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @Transient
    private int size;


    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public ReportJasper getReportJasper() {
        return reportJasper;
    }

    public void setReportJasper(ReportJasper reportJasper) {
        this.reportJasper = reportJasper;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getSize() {
        if (getImage() == null) return 0;
        return image.length;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
