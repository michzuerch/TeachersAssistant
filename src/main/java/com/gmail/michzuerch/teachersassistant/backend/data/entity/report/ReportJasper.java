package com.gmail.michzuerch.teachersassistant.backend.data.entity.report;


import com.gmail.michzuerch.teachersassistant.backend.data.entity.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ReportJasper")
public class ReportJasper extends AbstractEntity {
    @NotNull
    @Size(min = 1, max = 50)
    private String bezeichnung;

    private String filename;

    @Basic(fetch = FetchType.LAZY)
    private byte[] templateSource;

    @Basic(fetch = FetchType.LAZY)
    private byte[] templateCompiled;

    @OneToMany(mappedBy = "reportJasper", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReportJasperImage> reportJasperImages = new ArrayList<>();

    public ReportJasper() {
    }

    private ReportJasper(Builder builder) {
        bezeichnung = builder.bezeichnung;
        filename = builder.filename;
        templateSource = builder.templateSource;
        templateCompiled = builder.templateCompiled;
        reportJasperImages = builder.reportJasperImages;
    }

    public static final class Builder {
        private @NotNull @Size(min = 1, max = 50) String bezeichnung;
        private String filename;
        private byte[] templateSource;
        private byte[] templateCompiled;
        private List<ReportJasperImage> reportJasperImages;

        public Builder() {
        }

        public Builder bezeichnung(@NotNull @Size(min = 1, max = 50) String val) {
            bezeichnung = val;
            return this;
        }

        public Builder filename(String val) {
            filename = val;
            return this;
        }

        public Builder templateSource(byte[] val) {
            templateSource = val;
            return this;
        }

        public Builder templateCompiled(byte[] val) {
            templateCompiled = val;
            return this;
        }

        public Builder reportJasperImages(List<ReportJasperImage> val) {
            reportJasperImages = val;
            return this;
        }

        public ReportJasper build() {
            return new ReportJasper(this);
        }
    }
}