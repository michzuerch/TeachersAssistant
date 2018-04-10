package com.gmail.michzuerch.TeachersAssistant.presentation.ui.school;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.School;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

public class SchoolForm extends AbstractForm<School> {
    private static Logger logger = LoggerFactory.getLogger(SchoolForm.class.getName());

    TextField bezeichnung = new TextField("Bezeichnung");

    public SchoolForm() {
        super(School.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("School");
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
