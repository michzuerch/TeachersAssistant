package com.gmail.michzuerch.teachersassistant.backend.data.entity.report;


import com.gmail.michzuerch.teachersassistant.backend.data.entity.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ReportFOP")
public class ReportFOP extends AbstractEntity {
        @NotNull
        private String bezeichnung;

        @Basic(fetch = FetchType.LAZY)
        private byte[] template;

        @OneToMany(mappedBy = "reportFOP", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<ReportFOPImage> reportFOPImages = new ArrayList<>();

        public ReportFOP() {
        }

        private ReportFOP(Builder builder) {
                bezeichnung = builder.bezeichnung;
                template = builder.template;
                reportFOPImages = builder.reportFOPImages;
        }


        public static final class Builder {
                private @NotNull String bezeichnung;
                private byte[] template;
                private List<ReportFOPImage> reportFOPImages;

                public Builder() {
                }

                public Builder bezeichnung(@NotNull String val) {
                        bezeichnung = val;
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
