package com.gmail.michzuerch.teachersassistant.backend.data.entity.report;


import com.gmail.michzuerch.teachersassistant.backend.data.entity.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ReportCSS")
public class ReportCSS extends AbstractEntity {
    @NotNull
    private String bezeichnung;

    @NotNull
    @Basic(fetch = FetchType.LAZY)
    private byte[] css;

    @NotNull
    @Basic(fetch = FetchType.LAZY)
    private byte[] html;

    @NotNull
    @Basic(fetch = FetchType.LAZY)
    private byte[] javascript;

    @OneToMany(mappedBy = "reportCSS", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReportCSSImage> reportCSSImages = new ArrayList<>();

    public ReportCSS() {
    }

    private ReportCSS(Builder builder) {
        bezeichnung = builder.bezeichnung;
        css = builder.css;
        html = builder.html;
        javascript = builder.javascript;
        reportCSSImages = builder.reportCSSImages;
    }

    public static final class Builder {
        private @NotNull String bezeichnung;
        private @NotNull byte[] css;
        private @NotNull byte[] html;
        private @NotNull byte[] javascript;
        private List<ReportCSSImage> reportCSSImages;

        public Builder() {
        }

        public Builder bezeichnung(@NotNull String val) {
            bezeichnung = val;
            return this;
        }

        public Builder css(@NotNull byte[] val) {
            css = val;
            return this;
        }

        public Builder html(@NotNull byte[] val) {
            html = val;
            return this;
        }

        public Builder javascript(@NotNull byte[] val) {
            javascript = val;
            return this;
        }

        public Builder reportCSSImages(List<ReportCSSImage> val) {
            reportCSSImages = val;
            return this;
        }

        public ReportCSS build() {
            return new ReportCSS(this);
        }
    }
}