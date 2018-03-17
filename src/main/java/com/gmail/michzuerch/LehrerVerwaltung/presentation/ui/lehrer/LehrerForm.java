package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.lehrer;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lehrer;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.SchuleDeltaspikeFacade;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class LehrerForm extends AbstractForm<Lehrer> {
    private static Logger logger = LoggerFactory.getLogger(LehrerForm.class.getName());

    @Inject
    SchuleDeltaspikeFacade schuleDeltaspikeFacade;

    TextField vorname = new TextField("Vorname");
    TextField nachname = new TextField("Nachname");
    ComboBox<Schule> schule = new ComboBox<>("Schule");

    public LehrerForm() {
        super(Lehrer.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Lehrer");
        openInModalPopup.setWidth("400px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        schule.setItems(schuleDeltaspikeFacade.findAll());
        schule.setItemCaptionGenerator(schule -> schule.getBezeichnung() + " " + schule.getOrt());

        return new VerticalLayout(new FormLayout(
                vorname, nachname, schule
        ), getToolbar());
    }
}
