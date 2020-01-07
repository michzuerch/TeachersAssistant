package com.gmail.michzuerch.teachersassistant.backend.data.entity.report;


import com.gmail.michzuerch.teachersassistant.backend.data.entity.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ReportFOP")
public class ReportFOP extends AbstractEntity {
        @NotNull
        private String description;

        @Basic(fetch = FetchType.LAZY)
        private byte[] template;

        @OneToMany(mappedBy = "reportFOP", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<ReportFOPImage> reportFOPImages = new ArrayList<>();

        private ReportFOP(Builder builder) {
                description = builder.description;
                setTemplate(builder.template);
                setReportFOPImages(builder.reportFOPImages);
        }

        public String getBezeichnung() {
                return description;
        }

        public void setBezeichnung(String bezeichnung) {
                this.description = bezeichnung;
        }

        public byte[] getTemplate() {
                return template;
        }

        public void setTemplate(byte[] template) {
                this.template = template;
        }

        public List<ReportFOPImage> getReportFOPImages() {
                return reportFOPImages;
        }

        public void setReportFOPImages(List<ReportFOPImage> reportFOPImages) {
                this.reportFOPImages = reportFOPImages;
        }

        public ReportFOP() {
        }


        public static final class Builder {
                private @NotNull String description;
                private byte[] template;
                private List<ReportFOPImage> reportFOPImages;

                public Builder() {
                }

                public Builder description(@NotNull String val) {
                        description = val;
                        return this;
                }

                public Builder template(byte[] val) {
                        template = val;
                        return this;
                }

                public Builder reportFOPImages(List<ReportFOPImage> val) {
                        reportFOPImages = val;
                        return this;
                }

                public ReportFOP build() {
                        return new ReportFOP(this);
                }
        }
}
