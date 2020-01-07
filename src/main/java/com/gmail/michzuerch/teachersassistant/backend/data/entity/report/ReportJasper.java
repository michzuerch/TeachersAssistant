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
    private String description;

    private String filename;

    @Basic(fetch = FetchType.LAZY)
    private byte[] templateSource;

    @Basic(fetch = FetchType.LAZY)
    private byte[] templateCompiled;

    @OneToMany(mappedBy = "reportJasper", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReportJasperImage> reportJasperImages = new ArrayList<>();

    private ReportJasper(Builder builder) {
        setDescription(builder.description);
        setFilename(builder.filename);
        setTemplateSource(builder.templateSource);
        setTemplateCompiled(builder.templateCompiled);
        setReportJasperImages(builder.reportJasperImages);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getTemplateSource() {
        return templateSource;
    }

    public void setTemplateSource(byte[] templateSource) {
        this.templateSource = templateSource;
    }

    public byte[] getTemplateCompiled() {
        return templateCompiled;
    }

    public void setTemplateCompiled(byte[] templateCompiled) {
        this.templateCompiled = templateCompiled;
    }

    public List<ReportJasperImage> getReportJasperImages() {
        return reportJasperImages;
    }

    public void setReportJasperImages(List<ReportJasperImage> reportJasperImages) {
        this.reportJasperImages = reportJasperImages;
    }

    public ReportJasper() {
    }

    public static final class Builder {
        private @NotNull @Size(min = 1, max = 50) String description;
        private String filename;
        private byte[] templateSource;
        private byte[] templateCompiled;
        private List<ReportJasperImage> reportJasperImages;

        public Builder() {
        }

        public Builder description(@NotNull @Size(min = 1, max = 50) String val) {
            description = val;
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