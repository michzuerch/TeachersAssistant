package com.gmail.michzuerch.TeachersAssistant.presentation.ui.student;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.SchoolClass;
import com.gmail.michzuerch.TeachersAssistant.backend.entity.Student;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.SchoolClassDeltaspikeFacade;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.StudentDeltaspikeFacade;
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

@CDIView("StudentView")
public class StudentView extends HorizontalLayout implements View {
    private static Logger logger = LoggerFactory.getLogger(StudentView.class.getName());

    TextField filterTextNachname = new TextField();
    ComboBox<SchoolClass> filterKlasse = new ComboBox<>();

    Grid<Student> grid = new Grid<>();

    @Inject
    private StudentDeltaspikeFacade facade;

    @Inject
    private SchoolClassDeltaspikeFacade schoolClassDeltaspikeFacade;

    @Inject
    private StudentForm form;

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

        filterKlasse.setPlaceholder("Filter für SchoolClass");
        filterKlasse.setItems(schoolClassDeltaspikeFacade.findAll());
        filterKlasse.setItemCaptionGenerator(item -> item.getBezeichnung());
        filterKlasse.addValueChangeListener(event -> updateList());

        Button clearFilterTextBtn = new Button(VaadinIcons.RECYCLE);
        clearFilterTextBtn.setDescription("Entferne Filter");
        clearFilterTextBtn.addClickListener(e -> {
            filterTextNachname.clear();
            filterKlasse.clear();
        });

        Button addBtn = new Button(VaadinIcons.PLUS);
        addBtn.addClickListener(event -> {
            grid.asSingleSelect().clear();
            Student student = new Student();
            if (filterKlasse.getValue() != null) student.setaSchoolClass(filterKlasse.getValue());
            form.setEntity(student);
            form.openInModalPopup();
            form.setSavedHandler(val -> {
                facade.save(val);
                updateList();
                grid.select(val);
                form.closePopup();
            });
        });

        CssLayout tools = new CssLayout();
        tools.addComponents(filterTextNachname, filterKlasse, clearFilterTextBtn, addBtn);
        tools.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        grid.addColumn(Student::getId).setCaption("id");
        grid.addColumn(Student::getNachname).setCaption("Nachname");
        grid.addColumn(Student::getVorname).setCaption("Vorname");
        grid.addColumn(schueler -> schueler.getaSchoolClass().getBezeichnung(), new ButtonRenderer(event -> {
            Student student = (Student) event.getItem();
            UI.getCurrent().getNavigator().navigateTo("SchoolClassView/id/" + student.getaSchoolClass().getId().toString());
        })).setCaption("SchoolClass").setStyleGenerator(item -> "v-align-center");
        grid.setSizeFull();

        // Render a button that deletes the data row (item)
        grid.addColumn(schueler -> "löschen",
                new ButtonRenderer(event -> {
                    Notification.show("Lösche Student id:" + event.getItem(), Notification.Type.HUMANIZED_MESSAGE);
                    facade.delete((Student) event.getItem());
                    updateList();
                })
        );

        grid.addColumn(schueler -> "ändern",
                new ButtonRenderer(event -> {
                    form.setEntity((Student) event.getItem());
                    form.openInModalPopup();
                    form.setSavedHandler(val -> {
                        facade.save(val);
                        System.err.println("Student:" + val);
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
            if (target.equals("klasseId")) {
                filterKlasse.setSelectedItem(schoolClassDeltaspikeFacade.findBy(id));
                updateList();
            } else if (target.equals("id")) {
                grid.select(facade.findBy(id));
            }
        }
        updateList();
    }

    public void updateList() {
        if (filterKlasse.getValue() != null) {
            logger.debug("Suche mit SchoolClass: " + filterKlasse.getValue().getBezeichnung());
            grid.setItems(facade.findByKlasse(filterKlasse.getValue()));
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
