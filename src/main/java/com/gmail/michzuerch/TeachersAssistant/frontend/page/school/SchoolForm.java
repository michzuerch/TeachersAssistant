package com.gmail.michzuerch.TeachersAssistant.frontend.page.school;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.School;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.service.SchoolService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.beans.factory.annotation.Autowired;

public class SchoolForm extends FormLayout {

    private TextField schoolId = new TextField("School Id");
    private TextField bezeichnung = new TextField("Bezeichnung");
    private TextField ort = new TextField("Ort");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    @Autowired
    private SchoolService service;

    private SchoolView view;
    private School school;
    private Binder<School> binder = new Binder<>(School.class);

    public SchoolForm(SchoolView view) {
        this.view = view;
        binder.bindInstanceFields(this);
        save.getElement().setAttribute("theme", "primary");
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addClickListener(e -> this.save());
        delete.addClickListener(e -> this.delete());

        add(schoolId, bezeichnung, buttons);
        setSchool(null);
    }

    private void delete() {
        service.delete(school);
        view.updateList();
        setSchool(null);
    }

    private void save() {
        service.save(school);
        view.updateList();
        setSchool(null);
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
        binder.setBean(school);
        boolean enabled = school != null;
        save.setEnabled(enabled);
        delete.setEnabled(enabled);
        if (enabled) {
            schoolId.focus();
        }
    }
}
