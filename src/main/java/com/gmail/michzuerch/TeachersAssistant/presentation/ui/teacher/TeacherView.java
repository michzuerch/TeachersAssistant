package com.gmail.michzuerch.TeachersAssistant.presentation.ui.teacher;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.School;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Teacher;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.SchoolDeltaspikeFacade;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.TeacherDeltaspikeFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemusa.flexlayout.*;

import javax.inject.Inject;

@CDIView("TeacherView")
public class TeacherView extends HorizontalLayout implements View {
    private static Logger logger = LoggerFactory.getLogger(TeacherView.class.getName());

    TextField filterTextNachname = new TextField();
    ComboBox<School> filterSchule = new ComboBox<>();

    Grid<Teacher> grid = new Grid<>();

    @Inject
    private TeacherDeltaspikeFacade facade;

    @Inject
    private SchoolDeltaspikeFacade schoolDeltaspikeFacade;

    @Inject
    private TeacherForm form;

    private Component createContent() {
        FlexLayout layout = new FlexLayout();

        layout.setFlexDirection(FlexDirection.Row);
        layout.setAlignItems(AlignItems.FlexEnd);
        layout.setJustifyContent(JustifyContent.SpaceBetween);
        layout.setAlignContent(AlignContent.Stretch);
        layout.setFlexWrap(FlexWrap.Wrap);

        filterTextNachname.setPlaceholder("Filter für Nachname");
        filterTextNachname.addValueChangeListener(e -> updateList());
        filterTextNachname.setValueChangeMode(ValueChangeMode.LAZY);

        filterSchule.setPlaceholder("Filter für School");
        filterSchule.setItems(schoolDeltaspikeFacade.findAll());
        filterSchule.setItemCaptionGenerator(item -> item.getBezeichnung());
        filterSchule.addValueChangeListener(event -> updateList());


        Button clearFilterTextBtn = new Button(VaadinIcons.RECYCLE);
        clearFilterTextBtn.setDescription("Entferne Filter");
        clearFilterTextBtn.addClickListener(e -> {
            filterTextNachname.clear();
            filterSchule.clear();
        });

        Button addBtn = new Button(VaadinIcons.PLUS);
        addBtn.addClickListener(event -> {
            grid.asSingleSelect().clear();
            Teacher teacher = new Teacher();
            if (filterSchule.getValue() != null) teacher.setSchool(filterSchule.getValue());
            form.setEntity(teacher);
            form.openInModalPopup();
            form.setSavedHandler(val -> {
                facade.save(val);
                updateList();
                grid.select(val);
                form.closePopup();
            });
        });

        CssLayout tools = new CssLayout();
        tools.addComponents(filterTextNachname, filterSchule, clearFilterTextBtn, addBtn);
        tools.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        grid.addColumn(Teacher::getId).setCaption("id");
        grid.addColumn(Teacher::getVorname).setCaption("Vorname");
        grid.addColumn(Teacher::getNachname).setCaption("Nachname");
        grid.addColumn(teacher -> teacher.getSchool().getBezeichnung(), new ButtonRenderer(event -> {
            Teacher teacher1 = (Teacher) event.getItem();
            UI.getCurrent().getNavigator().navigateTo("SchoolSubjectView/id/" + teacher1.getSchool().getId().toString());
        })).setCaption("School").setStyleGenerator(item -> "v-align-center");
        grid.setSizeFull();

        // Render a button that deletes the data row (item)
        grid.addColumn(adresse -> "löschen",
                new ButtonRenderer(event -> {
                    Notification.show("Lösche Teacher id:" + event.getItem(), Notification.Type.HUMANIZED_MESSAGE);
                    facade.delete((Teacher) event.getItem());
                    updateList();
                })
        );

        grid.addColumn(adresse -> "ändern",
                new ButtonRenderer(event -> {
                    form.setEntity((Teacher) event.getItem());
                    form.openInModalPopup();
                    form.setSavedHandler(val -> {
                        facade.save(val);
                        System.err.println("Teacher:" + val);
                        updateList();
                        grid.select(val);
                        form.closePopup();
                    });
                    form.setResetHandler(val -> {
                        updateList();
                        grid.select(val);
                        form.closePopup();
                    });
                }));
        layout.addComponents(tools, grid);
        layout.setSizeFull();
        return layout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        addComponent(createContent());
        setSizeFull();
        if (viewChangeEvent.getParameters() != null) {
            String[] msgs = viewChangeEvent.getParameters().split("/");
            String target = "";
            Long id = new Long(0);
            for (String msg : msgs) {
                if (target.isEmpty()) {
                    target = msg;
                } else {
                    id = Long.valueOf(msg);
                }
            }

            if (target.equals("schuleId")) {
                filterSchule.setSelectedItem(schoolDeltaspikeFacade.findBy(id));
                updateList();
            } else if (target.equals("id")) {
                grid.select(facade.findBy(id));
            }
        }
        updateList();
    }

    public void updateList() {
        if (filterSchule.getValue() != null) {
            logger.debug("Suche mit School: " + filterSchule.getValue().getBezeichnung());
            grid.setItems(facade.findBySchule(filterSchule.getValue()));
            return;
        }

        if (!filterTextNachname.isEmpty()) {
            //Suche mit Nachname
            logger.debug("Suche mit Nachname:" + filterTextNachname.getValue());
            grid.setItems(facade.findByNachnameLikeIgnoreCase(filterTextNachname.getValue() + "%"));
            return;
        }

        grid.setItems(facade.findAll());
    }

}
