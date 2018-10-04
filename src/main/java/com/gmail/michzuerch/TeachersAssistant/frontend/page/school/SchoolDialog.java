package com.gmail.michzuerch.TeachersAssistant.frontend.page.school;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.School;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.SchoolRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class SchoolDialog extends Dialog {
    private static final Logger logger = LoggerFactory.getLogger(SchoolDialog.class);

    @Autowired
    SchoolRepository repository;
    BeanValidationBinder<School> binder = new BeanValidationBinder<>(School.class);
    private School bean = new School();
    private FormLayout layout = new FormLayout();
    private TextField bezeichnung = new TextField("Geoname Id");
    private TextField ort = new TextField("Locale Code");
    private Button save = new Button("Speichern");
    private Button cancel = new Button("Abbrechen");

    public SchoolDialog() {
        init();
    }

    private BeanValidationBinder<School> getBinder() {
        return binder;
    }

    private void init() {
        logger.debug("init");
        layout.add(bezeichnung, ort, cancel, save);

        add(layout);
        setCloseOnEsc(true);
        setCloseOnOutsideClick(false);
    }

}
