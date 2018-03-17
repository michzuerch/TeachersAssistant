package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.buchhaltung.form;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Kontogruppe;
import com.vaadin.ui.*;
import org.vaadin.viritin.form.AbstractForm;

public class KontogruppeForm extends AbstractForm<Kontogruppe> {
    TextField bezeichnung = new TextField("Bezeichnung");
    TextField kontonummer = new TextField("Kontonummer");

    public KontogruppeForm() {
        super(Kontogruppe.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Kontogruppe");
        openInModalPopup.setWidth("400px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        return new VerticalLayout(new FormLayout(bezeichnung, kontonummer), getToolbar());
    }


}
