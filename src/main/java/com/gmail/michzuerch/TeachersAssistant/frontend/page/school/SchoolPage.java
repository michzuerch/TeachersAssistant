package com.gmail.michzuerch.TeachersAssistant.frontend.page.school;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.service.SchoolService;
import com.gmail.michzuerch.TeachersAssistant.frontend.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value = "School", layout = MainLayout.class)
public class SchoolPage extends VerticalLayout {
    private static final Logger logger = LoggerFactory.getLogger(SchoolPage.class);

    @Autowired
    SchoolService service;

    @Autowired
    SchoolGrid grid;
    Button btnAdd = new Button("Neu");

    @PostConstruct
    private void init() {
        add(btnAdd, grid);
    }
}
