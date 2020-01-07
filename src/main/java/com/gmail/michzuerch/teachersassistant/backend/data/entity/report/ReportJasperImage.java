package com.gmail.michzuerch.teachersassistant.backend.data.entity.report;


import com.gmail.michzuerch.teachersassistant.backend.data.entity.AbstractEntity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity(name = "ReportJasperImage")
public class ReportJasperImage extends AbstractEntity {
    private String description;

    @ManyToOne
    private ReportJasper reportJasper;

    private String mimeType;

    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    private ReportJasperImage(Builder builder) {
        setDescription(builder.description);
        setReportJasper(builder.reportJasper);
        setMimeType(builder.mimeType);
        setImage(builder.image);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ReportJasperImage() {
    }

    public static final class Builder {
        private String description;
        private ReportJasper reportJasper;
        private String mimeType;
        private byte[] image;

        public Builder() {
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder reportJasper(ReportJasper val) {
            reportJasper = val;
            return this;
        }

        public Builder mimeType(String val) {
            mimeType = val;
            return this;
        }

        public Builder image(byte[] val) {
            image = val;
            return this;
        }

        public ReportJasperImage build() {
            return new ReportJasperImage(this);
        }
    }
}