package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.lehrer;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

public class LehrerForm extends AbstractForm<Schule> {
    private static Logger logger = LoggerFactory.getLogger(LehrerForm.class.getName());

    TextField bezeichnung = new TextField("Bezeichnung");

    public LehrerForm() {
        super(Schule.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Schule");
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
