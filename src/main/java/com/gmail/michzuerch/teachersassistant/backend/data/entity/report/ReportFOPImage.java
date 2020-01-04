package com.gmail.michzuerch.teachersassistant.backend.data.entity.report;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.AbstractEntity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity(name = "ReportFOPImage")
public class ReportFOPImage extends AbstractEntity {
    private String bezeichnung;

    @ManyToOne
    private ReportFOP reportFOP;

    private String mimeType;

    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    public ReportFOPImage() {
    }

    private ReportFOPImage(Builder builder) {
        bezeichnung = builder.bezeichnung;
        reportFOP = builder.reportFOP;
        mimeType = builder.mimeType;
        image = builder.image;
    }

    public static final class Builder {
        private String bezeichnung;
        private ReportFOP reportFOP;
        private String mimeType;
        private byte[] image;

        public Builder() {
        }

        public Builder bezeichnung(String val) {
            bezeichnung = val;
            return this;
        }

        public Builder reportFOP(ReportFOP val) {
            reportFOP = val;
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

        public ReportFOPImage build() {
            return new ReportFOPImage(this);
        }
    }
}
