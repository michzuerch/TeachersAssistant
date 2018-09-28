package com.gmail.michzuerch.TeachersAssistant.presentation.ui.schoolsubject;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.SchoolSubject;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Student;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.StudentDeltaspikeFacade;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class SchoolSubjectForm extends AbstractForm<SchoolSubject> {
    private static Logger logger = LoggerFactory.getLogger(SchoolSubjectForm.class.getName());

    @Inject
    StudentDeltaspikeFacade studentDeltaspikeFacade;

    TextField bezeichnung = new TextField("Bezeichnung");
    ComboBox<Student> schueler = new ComboBox<>("Schüler");

    public SchoolSubjectForm() {
        super(SchoolSubject.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("SchoolSubject");
        openInModalPopup.setWidth("400px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        schueler.setItems(studentDeltaspikeFacade.findAll());
        schueler.setItemCaptionGenerator(schueler -> schueler.getVorname() + " " + schueler.getNachname() + " id:" + schueler.getId());
        return new VerticalLayout(new FormLayout(
                schueler, bezeichnung
        ), getToolbar());
    }
}
