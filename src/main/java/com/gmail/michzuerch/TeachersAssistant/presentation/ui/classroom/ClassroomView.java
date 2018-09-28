package com.gmail.michzuerch.TeachersAssistant.presentation.ui.classroom;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Classroom;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.ClassroomDeltaspikeFacade;
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

@CDIView("ClassroomView")
public class ClassroomView extends HorizontalLayout implements View {
    private static Logger logger = LoggerFactory.getLogger(ClassroomView.class.getName());

    TextField filterTextBezeichnung = new TextField();

    Grid<Classroom> grid = new Grid<>();

    @Inject
    private ClassroomDeltaspikeFacade facade;

    @Inject
    private ClassroomForm form;

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
            Classroom classroom = new Classroom();
            form.setEntity(classroom);
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

        grid.addColumn(Classroom::getId).setCaption("id");
        grid.addColumn(Classroom::getBezeichnung).setCaption("Bezeichnung");
        grid.addColumn(schulraum -> schulraum.getSchool().getBezeichnung() + " " +
                        schulraum.getSchool().getOrt() + "  id:" + schulraum.getSchool().getId(),
                new ButtonRenderer(event -> {
                    Classroom classroom = (Classroom) event.getItem();
                    UI.getCurrent().getNavigator().navigateTo("SchoolGradeView/id/" + classroom.getSchool().getId());
                })
        ).setCaption("School").setStyleGenerator(item -> "v-align-center");

        // Render a button that deletes the data row (item)
        grid.addColumn(schule -> "löschen",
                new ButtonRenderer(event -> {
                    Notification.show("Lösche Classroom id:" + event.getItem(), Notification.Type.HUMANIZED_MESSAGE);
                    facade.delete((Classroom) event.getItem());
                    updateList();
                })
        );

        grid.addColumn(schule -> "ändern",
                new ButtonRenderer(event -> {
                    form.setEntity((Classroom) event.getItem());
                    form.openInModalPopup();
                    form.setSavedHandler(val -> {
                        facade.save(val);
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
        grid.setSizeFull();

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
