package com.gmail.michzuerch.TeachersAssistant.presentation.ui.classroom;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.Classroom;
import com.gmail.michzuerch.TeachersAssistant.backend.entity.School;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.SchoolDeltaspikeFacade;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class ClassroomForm extends AbstractForm<Classroom> {
    private static Logger logger = LoggerFactory.getLogger(ClassroomForm.class.getName());

    @Inject
    SchoolDeltaspikeFacade schoolDeltaspikeFacade;

    TextField bezeichnung = new TextField("Bezeichnung");
    ComboBox<School> schule = new ComboBox<>("School");

    public ClassroomForm() {
        super(Classroom.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Classroom");
        openInModalPopup.setWidth("400px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        schule.setItems(schoolDeltaspikeFacade.findAll());
        schule.setItemCaptionGenerator(schule -> schule.getBezeichnung() + " " + schule.getOrt());
        return new VerticalLayout(new FormLayout(
                schule, bezeichnung
        ), getToolbar());
    }
}
