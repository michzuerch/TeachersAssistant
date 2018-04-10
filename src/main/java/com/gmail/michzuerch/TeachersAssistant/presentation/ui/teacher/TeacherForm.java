package com.gmail.michzuerch.TeachersAssistant.presentation.ui.teacher;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.School;
import com.gmail.michzuerch.TeachersAssistant.backend.entity.Teacher;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.SchoolDeltaspikeFacade;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class TeacherForm extends AbstractForm<Teacher> {
    private static Logger logger = LoggerFactory.getLogger(TeacherForm.class.getName());

    @Inject
    SchoolDeltaspikeFacade schoolDeltaspikeFacade;

    TextField vorname = new TextField("Vorname");
    TextField nachname = new TextField("Nachname");
    ComboBox<School> schule = new ComboBox<>("School");

    public TeacherForm() {
        super(Teacher.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Teacher");
        openInModalPopup.setWidth("400px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        schule.setItems(schoolDeltaspikeFacade.findAll());
        schule.setItemCaptionGenerator(schule -> schule.getBezeichnung() + " " + schule.getOrt());

        return new VerticalLayout(new FormLayout(
                vorname, nachname, schule
        ), getToolbar());
    }
}
