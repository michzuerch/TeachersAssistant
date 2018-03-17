package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.report.fop;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.report.fop.ReportFOP;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.report.fop.ReportFOPImage;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.ReportFOPDeltaspikeFacade;
import com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.util.field.ImageAndMimetypeField;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class ReportFOPImageForm extends AbstractForm<ReportFOPImage> {
    private static Logger logger = LoggerFactory.getLogger(ReportFOPImageForm.class.getName());

    @Inject
    ReportFOPDeltaspikeFacade reportFOPDeltaspikeFacade;

    NativeSelect<ReportFOP> reportFop = new NativeSelect<>("Report FOP");
    TextField bezeichnung = new TextField("Bezeichnung");
    ImageAndMimetypeField image = new ImageAndMimetypeField();
    //Button downloadButton = new Button("Download Image");

    private String filename;

    public ReportFOPImageForm() {
        super(ReportFOPImage.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Report FOP Image");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        //getBinder().forField(image).bind("image");
        //StreamResource templateResource = new StreamResource(new ImageStreamSource(image.getValue()), "image.jpg");
        //FileDownloader fileDownloader = new FileDownloader(templateResource);
        //fileDownloader.extend(downloadButton);

        reportFop.setItems(reportFOPDeltaspikeFacade.findAll());
        reportFop.setItemCaptionGenerator(item -> item.getBezeichnung() + " " + item.getId());

        image.setCaption("Bild");
        image.setHeight(300, Unit.PIXELS);
        image.setWidth(400, Unit.PIXELS);

        return new VerticalLayout(new FormLayout(
                reportFop, bezeichnung, image), getToolbar());
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}
