package com.gmail.michzuerch.TeachersAssistant.presentation.ui.student;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.SchoolClass;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Student;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.SchoolClassDeltaspikeFacade;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class StudentForm extends AbstractForm<Student> {
    private static Logger logger = LoggerFactory.getLogger(StudentForm.class.getName());

    @Inject
    SchoolClassDeltaspikeFacade schoolClassDeltaspikeFacade;

    TextField vorname = new TextField("Vorname");
    TextField nachname = new TextField("Nachname");
    ComboBox<SchoolClass> klasse = new ComboBox<>("SchoolClass");

    public StudentForm() {
        super(Student.class);
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
        klasse.setItems(schoolClassDeltaspikeFacade.findAll());
        klasse.setItemCaptionGenerator(klasse -> klasse.getBezeichnung());
        return new VerticalLayout(new FormLayout(
                vorname, nachname, klasse
        ), getToolbar());
    }
}
