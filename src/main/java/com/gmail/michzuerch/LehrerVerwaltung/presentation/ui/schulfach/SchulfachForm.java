package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.schulfach;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schueler;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schulfach;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.SchuelerDeltaspikeFacade;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class SchulfachForm extends AbstractForm<Schulfach> {
    private static Logger logger = LoggerFactory.getLogger(SchulfachForm.class.getName());

    @Inject
    SchuelerDeltaspikeFacade schuelerDeltaspikeFacade;

    TextField bezeichnung = new TextField("Bezeichnung");
    ComboBox<Schueler> schueler = new ComboBox<>("Schüler");

    public SchulfachForm() {
        super(Schulfach.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Schulfach");
        openInModalPopup.setWidth("400px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        schueler.setItems(schuelerDeltaspikeFacade.findAll());
        schueler.setItemCaptionGenerator(schueler -> schueler.getVorname() + " " + schueler.getNachname() + " id:" + schueler.getId());
        return new VerticalLayout(new FormLayout(
                schueler, bezeichnung
        ), getToolbar());
    }
}
