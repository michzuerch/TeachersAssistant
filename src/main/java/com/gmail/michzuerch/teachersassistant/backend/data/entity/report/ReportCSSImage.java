package com.gmail.michzuerch.teachersassistant.backend.data.entity.report;


import com.gmail.michzuerch.teachersassistant.backend.data.entity.AbstractEntity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity(name = "ReportCSSImage")
public class ReportCSSImage extends AbstractEntity {
    private String bezeichnung;

    @ManyToOne
    private ReportCSS reportCSS;

    private String mimeType;

    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    public ReportCSSImage() {
    }

    private ReportCSSImage(Builder builder) {
        bezeichnung = builder.bezeichnung;
        reportCSS = builder.reportCSS;
        mimeType = builder.mimeType;
        image = builder.image;
    }


    public static final class Builder {
        private String bezeichnung;
        private ReportCSS reportCSS;
        private String mimeType;
        private byte[] image;

        public Builder() {
        }

        public Builder bezeichnung(String val) {
            bezeichnung = val;
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