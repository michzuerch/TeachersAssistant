package com.gmail.michzuerch.teachersassistant.backend.data.entity.report;


import com.gmail.michzuerch.teachersassistant.backend.data.entity.AbstractEntity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity(name = "ReportCSSImage")
public class ReportCSSImage extends AbstractEntity {
    private String description;

    @ManyToOne
    private ReportCSS reportCSS;

    private String mimeType;

    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    private ReportCSSImage(Builder builder) {
        setDescription(builder.description);
        setReportCSS(builder.reportCSS);
        setMimeType(builder.mimeType);
        setImage(builder.image);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReportCSS getReportCSS() {
        return reportCSS;
    }

    public void setReportCSS(ReportCSS reportCSS) {
        this.reportCSS = reportCSS;
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

    public ReportCSSImage() {
    }

    public static final class Builder {
        private String description;
        private ReportCSS reportCSS;
        private String mimeType;
        private byte[] image;

        public Builder() {
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder reportCSS(ReportCSS val) {
            reportCSS = val;
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

        public ReportCSSImage build() {
            return new ReportCSSImage(this);
        }
    }
}