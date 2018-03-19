package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.report.jasper;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.report.jasper.ReportJasper;
import com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.util.field.JasperXmlField;
import com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.util.validator.JRXMLValidationHelper;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

public class ReportJasperForm extends AbstractForm<ReportJasper> {
    private static Logger logger = LoggerFactory.getLogger(ReportJasperForm.class.getName());

    TextField bezeichnung = new TextField("Bezeichnung");
    JasperXmlField templateSource = new JasperXmlField();
    Button validateAndCompileButton = new Button("Validate and Compile");

    private String filename;

    public ReportJasperForm() {
        super(ReportJasper.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Report Jasper");
        openInModalPopup.setWidth(800, Unit.PIXELS);
        return openInModalPopup;
    }

    @Override
    public boolean isValid() {
        JRXMLValidationHelper helper = new JRXMLValidationHelper();
        if (templateSource.getValue() != null) {
            if (!helper.verifyValidatesInternalXsd(templateSource.getValue())) {
                logger.debug("templateSource not valid xsd");
                return false;
            }
            if (!helper.compileJRXML(templateSource.getValue())) {
                logger.debug("templateSource not compilable");
                return false;
            }
            logger.debug("Form is valid");
            return true;
        }
        logger.debug("Form is valid");
        return true;
    }

    @Override
    protected Component createContent() {
//        validateAndCompileButton.addClickListener(event -> {
//            if (compileJRXML(getEntity().getTemplateSource())) {
//                Notification.show("Compiling verfolgreich", Notification.Type.TRAY_NOTIFICATION);
//            } else {
//                Notification.show("Fehler beim Compilieren", Notification.Type.ERROR_MESSAGE);
//            }
//
//        });

        return new VerticalLayout(new FormLayout(
                bezeichnung, templateSource, validateAndCompileButton), getToolbar());
    }



}
