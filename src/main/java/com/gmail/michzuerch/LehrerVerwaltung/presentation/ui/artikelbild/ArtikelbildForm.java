package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.artikelbild;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Artikel;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Artikelbild;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.ArtikelDeltaspikeFacade;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.ArtikelbildDeltaspikeFacade;
import com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.util.field.ImageAndMimetypeField;
import com.vaadin.ui.*;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class ArtikelbildForm extends AbstractForm<Artikelbild> {
    @Inject
    ArtikelDeltaspikeFacade artikelDeltaspikeFacade;

    @Inject
    ArtikelbildDeltaspikeFacade artikelbildDeltaspikeFacade;

    ComboBox<Artikel> artikel = new ComboBox<>();
    TextField titel = new TextField("Titel");
    ImageAndMimetypeField imageAndMimetype = new ImageAndMimetypeField();

    public ArtikelbildForm() {
        super(Artikelbild.class);
    }

    public void lockSelect() {
        artikel.setEnabled(false);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Artikelbild");
        openInModalPopup.setWidth("500px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        artikel.setCaption("Artikel");
        artikel.setEmptySelectionAllowed(false);
        imageAndMimetype.setCaption("Bild");
        artikel.setItemCaptionGenerator(artikel1 -> artikel1.getBezeichnung() + " id:" + artikel1.getId());
        artikel.setItems(artikelDeltaspikeFacade.findAll());
        return new VerticalLayout(new FormLayout(
                artikel, titel, imageAndMimetype
        ), getToolbar());
    }
}
