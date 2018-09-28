package com.gmail.michzuerch.TeachersAssistant.presentation.ui.schoolgrade;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.School;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

public class SchoolGradeForm extends AbstractForm<School> {
    private static Logger logger = LoggerFactory.getLogger(SchoolGradeForm.class.getName());

    TextField bezeichnung = new TextField("Bezeichnung");

    public SchoolGradeForm() {
        super(School.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("SchoolGrade");
        openInModalPopup.setWidth("400px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        return new VerticalLayout(new FormLayout(
                bezeichnung
        ), getToolbar());
    }
}
