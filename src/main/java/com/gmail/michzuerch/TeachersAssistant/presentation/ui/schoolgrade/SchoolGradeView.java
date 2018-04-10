package com.gmail.michzuerch.TeachersAssistant.presentation.ui.schoolgrade;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.School;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.SchoolDeltaspikeFacade;
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

@CDIView("SchoolGradeView")
public class SchoolGradeView extends HorizontalLayout implements View {
    private static Logger logger = LoggerFactory.getLogger(SchoolGradeView.class.getName());

    TextField filterTextBezeichnung = new TextField();

    Grid<School> grid = new Grid<>();

    @Inject
    private SchoolDeltaspikeFacade facade;

    @Inject
    private SchoolGradeForm form;

    private Component createContent() {
        FlexLayout layout = new FlexLayout();

        layout.setFlexDirection(FlexDirection.Row);
        layout.setAlignItems(AlignItems.FlexEnd);
        layout.setJustifyContent(JustifyContent.SpaceBetween);
        layout.setAlignContent(AlignContent.Stretch);
        layout.setFlexWrap(FlexWrap.Wrap);

        filterTextBezeichnung.setPlaceholder("Filter für Bezeichnung");
        filterTextBezeichnung.addValueChangeListener(e -> updateList());
        filterTextBezeichnung.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn = new Button(VaadinIcons.RECYCLE);
        clearFilterTextBtn.setDescription("Entferne Filter");
        clearFilterTextBtn.addClickListener(e -> {
            filterTextBezeichnung.clear();
        });

        Button addBtn = new Button(VaadinIcons.PLUS);
        addBtn.addClickListener(event -> {
            grid.asSingleSelect().clear();
            School school = new School();
            form.setEntity(school);
            form.openInModalPopup();
            form.setSavedHandler(val -> {
                facade.save(val);
                updateList();
                grid.select(val);
                form.closePopup();
            });
        });

        CssLayout tools = new CssLayout();
        tools.addComponents(filterTextBezeichnung, clearFilterTextBtn, addBtn);
        tools.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        grid.addColumn(School::getId).setCaption("id");
        grid.addColumn(School::getBezeichnung).setCaption("Bezeichnung");
        grid.addColumn(School::getOrt).setCaption("Ort");

        grid.addColumn(schule -> schule.getSchoolClasses().size(), new ButtonRenderer(event -> {
            School school = (School) event.getItem();
            if (school.getSchoolClasses().size() > 0) {
                UI.getCurrent().getNavigator().navigateTo("SchoolClassView/schuleId/" + school.getId().toString());
            }
        })).setCaption("Anzahl Klassen").setStyleGenerator(item -> "v-align-center");
        grid.setSizeFull();

        grid.addColumn(schule -> schule.getTeachers().size(), new ButtonRenderer(event -> {
            School school = (School) event.getItem();
            if (school.getTeachers().size() > 0) {
                UI.getCurrent().getNavigator().navigateTo("TeacherView/schuleId/" + school.getId().toString());
            }
        })).setCaption("Anzahl Teacher").setStyleGenerator(item -> "v-align-center");
        grid.setSizeFull();

        // Render a button that deletes the data row (item)
        grid.addColumn(schule -> "löschen",
                new ButtonRenderer(event -> {
                    Notification.show("Lösche School id:" + event.getItem(), Notification.Type.HUMANIZED_MESSAGE);
                    facade.delete((School) event.getItem());
                    updateList();
                })
        );

        grid.addColumn(schule -> "ändern",
                new ButtonRenderer(event -> {
                    form.setEntity((School) event.getItem());
                    form.openInModalPopup();
                    form.setSavedHandler(val -> {
                        facade.save(val);
                        System.err.println("School:" + val);
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
            String target = new String();
            Long id = new Long(0);
            for (String msg : msgs) {
                if (target.isEmpty()) {
                    target = msg;
                } else {
                    id = Long.valueOf(msg);
                }
            }
            if (target.equals("id")) {
                grid.select(facade.findBy(id));
            }
        }
        updateList();
    }

    public void updateList() {
        if (!filterTextBezeichnung.isEmpty()) {
            //Suche mit Bezeichnung
            logger.debug("Suche mit Bezeichnung:" + filterTextBezeichnung.getValue());
            grid.setItems(facade.findByBezeichungLikeIgnoreCase(filterTextBezeichnung.getValue() + "%"));
            return;
        }

        grid.setItems(facade.findAll());
    }

}
