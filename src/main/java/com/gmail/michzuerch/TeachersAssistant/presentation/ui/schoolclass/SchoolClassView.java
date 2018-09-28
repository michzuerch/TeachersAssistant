package com.gmail.michzuerch.TeachersAssistant.presentation.ui.schoolclass;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.School;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.SchoolClass;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.SchoolClassDeltaspikeFacade;
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

@CDIView("SchoolClassView")
public class SchoolClassView extends HorizontalLayout implements View {
    private static Logger logger = LoggerFactory.getLogger(SchoolClassView.class.getName());

    TextField filterTextBezeichnung = new TextField();
    ComboBox<School> filterSchule = new ComboBox<>();

    Grid<SchoolClass> grid = new Grid<>();

    @Inject
    private SchoolClassDeltaspikeFacade facade;

    @Inject
    private SchoolDeltaspikeFacade schoolDeltaspikeFacade;

    @Inject
    private SchoolClassForm form;

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

        filterSchule.setPlaceholder("Filter für School");
        filterSchule.setItems(schoolDeltaspikeFacade.findAll());
        filterSchule.setItemCaptionGenerator(item -> item.getBezeichnung());
        filterSchule.addValueChangeListener(event -> updateList());

        Button clearFilterTextBtn = new Button(VaadinIcons.RECYCLE);
        clearFilterTextBtn.setDescription("Entferne Filter");
        clearFilterTextBtn.addClickListener(e -> {
            filterTextBezeichnung.clear();
            filterSchule.clear();
        });

        Button addBtn = new Button(VaadinIcons.PLUS);
        addBtn.addClickListener(event -> {
            grid.asSingleSelect().clear();
            SchoolClass aSchoolClass = new SchoolClass();
            if (filterSchule.getValue() != null) aSchoolClass.setSchool(filterSchule.getValue());
            form.setEntity(aSchoolClass);
            form.openInModalPopup();
            form.setSavedHandler(val -> {
                facade.save(val);
                updateList();
                grid.select(val);
                form.closePopup();
            });
        });

        CssLayout tools = new CssLayout();
        tools.addComponents(filterTextBezeichnung, filterSchule, clearFilterTextBtn, addBtn);
        tools.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        grid.addColumn(SchoolClass::getId).setCaption("id");
        grid.addColumn(SchoolClass::getBezeichnung).setCaption("Bezeichnung");
        grid.addColumn(klasse -> klasse.getStudents().size(), new ButtonRenderer(event -> {
            SchoolClass aSchoolClass = (SchoolClass) event.getItem();
            if (aSchoolClass.getStudents().size() > 0) {
                UI.getCurrent().getNavigator().navigateTo("StudentView/klasseId/" + aSchoolClass.getId().toString());
            }
        })).setCaption("Anzahl Schüler").setStyleGenerator(item -> "v-align-center");
        grid.addColumn(klasse -> klasse.getSchool().getBezeichnung(), new ButtonRenderer(event -> {
            SchoolClass aSchoolClass = (SchoolClass) event.getItem();
            UI.getCurrent().getNavigator().navigateTo("SchoolSubjectView/id/" + aSchoolClass.getSchool().getId().toString());
        })).setCaption("School").setStyleGenerator(item -> "v-align-center");


        grid.setSizeFull();

        // Render a button that deletes the data row (item)
        grid.addColumn(adresse -> "löschen",
                new ButtonRenderer(event -> {
                    Notification.show("Lösche SchoolClass id:" + event.getItem(), Notification.Type.HUMANIZED_MESSAGE);
                    facade.delete((SchoolClass) event.getItem());
                    updateList();
                })
        );

        grid.addColumn(adresse -> "ändern",
                new ButtonRenderer(event -> {
                    form.setEntity((SchoolClass) event.getItem());
                    form.openInModalPopup();
                    form.setSavedHandler(val -> {
                        facade.save(val);
                        System.err.println("SchoolClass:" + val);
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
            logger.debug("Suche mit School:" + filterSchule.getValue().getBezeichnung());
            grid.setItems(facade.findBySchule(filterSchule.getValue()));
            return;
        }
        if (!filterTextBezeichnung.isEmpty()) {
            //Suche mit Bezeichnung
            logger.debug("Suche mit Bezeichnung:" + filterTextBezeichnung.getValue());
            grid.setItems(facade.findByBezeichungLikeIgnoreCase(filterTextBezeichnung.getValue() + "%"));
            return;
        }

        grid.setItems(facade.findAll());
    }

}
