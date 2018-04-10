package com.gmail.michzuerch.TeachersAssistant.presentation.ui.report.jasper;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.report.jasper.ReportJasper;
import com.gmail.michzuerch.TeachersAssistant.backend.entity.report.jasper.ReportJasperImage;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.ReportJasperDeltaspikeFacade;
import com.gmail.michzuerch.TeachersAssistant.presentation.ui.util.field.ImageField;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class ReportJasperImageForm extends AbstractForm<ReportJasperImage> {
    private static Logger logger = LoggerFactory.getLogger(ReportJasperImageForm.class.getName());

    @Inject
    ReportJasperDeltaspikeFacade reportJasperDeltaspikeFacade;

    NativeSelect<ReportJasper> reportJasper = new NativeSelect<>("Report Jasper");
    TextField bezeichnung = new TextField("Bezeichnung");
    ImageField image = new ImageField();

    private String filename;

    public ReportJasperImageForm() {
        super(ReportJasperImage.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Report Jasper Image");
        openInModalPopup.setWidth(600, Unit.PIXELS);
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        reportJasper.setItems(reportJasperDeltaspikeFacade.findAll());
        reportJasper.setItemCaptionGenerator(item -> item.getBezeichnung() + " " + item.getId());

        image.setCaption("Bild");
        image.setHeight(300, Unit.PIXELS);
        image.setWidth(400, Unit.PIXELS);

        return new VerticalLayout(new FormLayout(
                reportJasper, bezeichnung, image), getToolbar());
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}
