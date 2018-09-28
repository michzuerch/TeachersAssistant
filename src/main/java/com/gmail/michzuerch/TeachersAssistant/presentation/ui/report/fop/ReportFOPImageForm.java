package com.gmail.michzuerch.TeachersAssistant.presentation.ui.report.fop;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.report.fop.ReportFOP;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.report.fop.ReportFOPImage;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.ReportFOPDeltaspikeFacade;
import com.gmail.michzuerch.TeachersAssistant.presentation.ui.util.field.ImageField;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class ReportFOPImageForm extends AbstractForm<ReportFOPImage> {
    private static Logger logger = LoggerFactory.getLogger(ReportFOPImageForm.class.getName());

    @Inject
    ReportFOPDeltaspikeFacade reportFOPDeltaspikeFacade;

    NativeSelect<ReportFOP> reportFOP = new NativeSelect<>("Report FOP");
    TextField bezeichnung = new TextField("Bezeichnung");
    ImageField image = new ImageField();

    private String filename;

    public ReportFOPImageForm() {
        super(ReportFOPImage.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Report FOP Image");
        openInModalPopup.setWidth(600, Unit.PIXELS);
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        reportFOP.setItems(reportFOPDeltaspikeFacade.findAll());
        reportFOP.setItemCaptionGenerator(item -> item.getBezeichnung() + " " + item.getId());

        image.setCaption("Bild");
        image.setHeight(300, Unit.PIXELS);
        image.setWidth(400, Unit.PIXELS);

        return new VerticalLayout(new FormLayout(
                reportFOP, bezeichnung, image), getToolbar());
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}
