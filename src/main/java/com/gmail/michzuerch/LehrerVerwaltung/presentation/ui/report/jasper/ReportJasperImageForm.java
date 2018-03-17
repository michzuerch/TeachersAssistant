package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.report.jasper;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.report.jasper.ReportJasper;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.report.jasper.ReportJasperImage;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.ReportJasperDeltaspikeFacade;
import com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.util.field.ImageAndMimetypeField;
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
    ImageAndMimetypeField image = new ImageAndMimetypeField();
    //Button downloadButton = new Button("Download Image");

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
        //getBinder().forField(image).bind("image");
        //StreamResource templateResource = new StreamResource(new ImageStreamSource(image.getValue()), "image.jpg");
        //FileDownloader fileDownloader = new FileDownloader(templateResource);
        //fileDownloader.extend(downloadButton);

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
