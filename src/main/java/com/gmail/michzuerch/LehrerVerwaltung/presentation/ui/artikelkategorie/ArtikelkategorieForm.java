package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.artikelkategorie;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Artikelkategorie;
import com.vaadin.ui.*;
import org.vaadin.viritin.form.AbstractForm;

public class ArtikelkategorieForm extends AbstractForm<Artikelkategorie> {

    TextField bezeichnung = new TextField("Bezeichnung");

    public ArtikelkategorieForm() {
        super(Artikelkategorie.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        //@todo Breite für Window in Chromium muss immer angegeben werden
        openInModalPopup.setWidth(20, Unit.PERCENTAGE);
        openInModalPopup.setCaption("Artikelkategorie");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        return new VerticalLayout(new FormLayout(
                bezeichnung
        ), getToolbar());
    }
}
