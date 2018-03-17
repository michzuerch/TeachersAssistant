package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.imagetest;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.ImageTest;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.ImageTestDeltaspikeFacade;
import com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.util.field.ImageAndMimetypeField;
import com.vaadin.ui.*;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class ImageTestForm extends AbstractForm<ImageTest> {
    @Inject
    ImageTestDeltaspikeFacade imageTestDeltaspikeFacade;

    TextField titel = new TextField("Titel");
    ImageAndMimetypeField bild = new ImageAndMimetypeField();

    public ImageTestForm() {
        super(ImageTest.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("ImageTest");
        openInModalPopup.setWidth("500px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {

        return new VerticalLayout(new FormLayout(
                titel, bild
        ), getToolbar());
    }
}
