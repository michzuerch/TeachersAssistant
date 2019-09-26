package com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.report.fop;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ReportFOP extends AbstractEntity {
    /**
	 *
	 */
	private static final long serialVersionUID = -7921968839715827974L;

	@Column
    @NotNull
    private String bezeichnung;

    @Column
    @NotNull
    @Basic(fetch = FetchType.LAZY)
    private byte[] template;

    @OneToMany(mappedBy = "reportFOP", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReportFOPImage> reportFOPImages = new ArrayList<>();

    @Transient
    private int size;

    public int getSize() {
        return template.length;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public byte[] getTemplate() {
        return template;
    }

    public void setTemplate(byte[] xslfile) {
        this.template = xslfile;
    }

    public List<ReportFOPImage> getReportFOPImages() {
        return reportFOPImages;
    }

    public void setReportFOPImages(List<ReportFOPImage> reportFOPImages) {
        this.reportFOPImages = reportFOPImages;
    }
}
