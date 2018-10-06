package com.gmail.michzuerch.TeachersAssistant.frontend.page.school;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.School;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.service.SchoolService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.UIScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class SchoolView extends VerticalLayout {
    private static final Logger logger = LoggerFactory.getLogger(SchoolView.class);

    private SchoolService service;

    private SchoolDialog dialog;
    private Button btnAdd = new Button("Neu");
    private Grid<School> grid = new Grid<>();

    private TextField filterText = new TextField();
    private Button clearFilterTextBtn = new Button(new Icon(VaadinIcon.CLOSE_CIRCLE));

    public SchoolView(@Autowired SchoolService schoolService, @Autowired SchoolDialog schoolDialog) {
        this.service = schoolService;
        this.dialog = schoolDialog;

        filterText.setPlaceholder("Filter by Bezeichnung...");
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList());
        clearFilterTextBtn.addClickListener(e -> {
            filterText.clear();
            updateList();
        });

        grid.setSizeFull();
        grid.addColumn(School::getId).setSortable(true).setHeader("Id");
        grid.addColumn(School::getBezeichnung).setHeader("Bezeichnung");
        grid.addColumn(School::getOrt).setHeader("Ort");

        HorizontalLayout filtering = new HorizontalLayout(filterText, clearFilterTextBtn);
        HorizontalLayout toolbar = new HorizontalLayout(btnAdd, filtering);

        HorizontalLayout main = new HorizontalLayout(grid);
        main.setAlignItems(Alignment.START);
        main.setSizeFull();

        btnAdd.addClickListener(e -> {
            grid.asSingleSelect().clear();
            dialog.setBean(new School());
        });

        grid.asSingleSelect().addValueChangeListener(event -> {
            dialog.setBean(event.getValue());
        });

        add(toolbar, main);
        setHeight("100vh");
        updateList();
    }

    public void updateList() {
        if (filterText.isEmpty()) {
            grid.setItems(service.findAll());
        } else {
            logger.debug("findByBezeichnung(" + filterText.getValue() + ")");
            grid.setItems(service.findByBezeichnungIgnoreCase(filterText.getValue()));
        }
    }
}
