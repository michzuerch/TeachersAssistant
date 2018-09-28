package com.gmail.michzuerch.TeachersAssistant.presentation.ui.report.fop;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.report.fop.ReportFOP;
import com.gmail.michzuerch.TeachersAssistant.presentation.ui.util.field.FOPXmlField;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

public class ReportFOPForm extends AbstractForm<ReportFOP> {
    private static Logger logger = LoggerFactory.getLogger(ReportFOPForm.class.getName());

    TextField bezeichnung = new TextField("Bezeichnung");
    FOPXmlField template = new FOPXmlField();

    public ReportFOPForm() {
        super(ReportFOP.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Report FOP");
        openInModalPopup.setWidth(800, Unit.PIXELS);
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        return new VerticalLayout(new FormLayout(
                bezeichnung, template), getToolbar());
    }


}
