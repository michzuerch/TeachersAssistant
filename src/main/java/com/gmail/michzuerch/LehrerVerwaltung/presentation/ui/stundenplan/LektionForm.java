package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.stundenplan;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lektion;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

public class LektionForm extends AbstractForm<Lektion> {
    private static Logger logger = LoggerFactory.getLogger(LektionForm.class.getName());

    TextField bezeichnung = new TextField("Bezeichnung");

    public LektionForm() {
        super(Lektion.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Lektion");
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
