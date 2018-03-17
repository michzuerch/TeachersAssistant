package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.schueler;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Klasse;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schueler;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.KlasseDeltaspikeFacade;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class SchuelerForm extends AbstractForm<Schueler> {
    private static Logger logger = LoggerFactory.getLogger(SchuelerForm.class.getName());

    @Inject
    KlasseDeltaspikeFacade klasseDeltaspikeFacade;

    TextField vorname = new TextField("Vorname");
    TextField nachname = new TextField("Nachname");
    ComboBox<Klasse> klasse = new ComboBox<>("Klasse");

    public SchuelerForm() {
        super(Schueler.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Schüler");
        openInModalPopup.setWidth("400px");
        return openInModalPopup;

    }

    @Override
    protected Component createContent() {
        klasse.setItems(klasseDeltaspikeFacade.findAll());
        klasse.setItemCaptionGenerator(klasse -> klasse.getBezeichnung());
        return new VerticalLayout(new FormLayout(
                vorname, nachname, klasse
        ), getToolbar());
    }
}
