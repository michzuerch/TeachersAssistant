package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.schulraum;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schulraum;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.SchuleDeltaspikeFacade;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class SchulraumForm extends AbstractForm<Schulraum> {
    private static Logger logger = LoggerFactory.getLogger(SchulraumForm.class.getName());

    @Inject
    SchuleDeltaspikeFacade schuleDeltaspikeFacade;

    TextField bezeichnung = new TextField("Bezeichnung");
    ComboBox<Schule> schule = new ComboBox<>("Schule");

    public SchulraumForm() {
        super(Schulraum.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Schulraum");
        openInModalPopup.setWidth("400px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        schule.setItems(schuleDeltaspikeFacade.findAll());
        schule.setItemCaptionGenerator(schule -> schule.getBezeichnung() + " " + schule.getOrt());
        return new VerticalLayout(new FormLayout(
                schule, bezeichnung
        ), getToolbar());
    }
}
