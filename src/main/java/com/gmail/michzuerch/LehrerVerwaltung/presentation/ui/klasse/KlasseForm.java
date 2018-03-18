package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.klasse;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Klasse;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.SchuleDeltaspikeFacade;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class KlasseForm extends AbstractForm<Klasse> {
    private static Logger logger = LoggerFactory.getLogger(KlasseForm.class.getName());

    @Inject
    SchuleDeltaspikeFacade schuleDeltaspikeFacade;

    TextField bezeichnung = new TextField("Bezeichnung");
    ComboBox<Schule> schule = new ComboBox<>("Schule");

    public KlasseForm() {
        super(Klasse.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Klasse");
        openInModalPopup.setWidth("400px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        schule.setItems(schuleDeltaspikeFacade.findAll());
        schule.setItemCaptionGenerator(schule -> schule.getBezeichnung());

        return new VerticalLayout(new FormLayout(
                bezeichnung, schule
        ), getToolbar());
    }
}
