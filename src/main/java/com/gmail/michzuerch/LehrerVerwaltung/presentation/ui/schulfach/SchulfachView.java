package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.schulfach;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schulfach;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.SchulfachDeltaspikeFacade;
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

@CDIView("SchulfachView")
public class SchulfachView extends HorizontalLayout implements View {
    private static Logger logger = LoggerFactory.getLogger(SchulfachView.class.getName());

    TextField filterTextBezeichnung = new TextField();

    Grid<Schulfach> grid = new Grid<>();

    @Inject
    private SchulfachDeltaspikeFacade facade;

    @Inject
    private SchulfachForm form;

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
            Schulfach schulfach = new Schulfach();
            form.setEntity(schulfach);
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

        grid.addColumn(Schulfach::getId).setCaption("id");
        grid.addColumn(Schulfach::getBezeichnung).setCaption("Bezeichnung");
        grid.addColumn(schulfach -> schulfach.getSchueler().getVorname() + " " +
                        schulfach.getSchueler().getNachname() + "  id:" + schulfach.getSchueler().getId(),
                new ButtonRenderer(event -> {
                    Schulfach schulfach = (Schulfach) event.getItem();
                    UI.getCurrent().getNavigator().navigateTo("SchuelerView/id/" + schulfach.getSchueler().getId());
                })
        ).setCaption("Schüler").setStyleGenerator(item -> "v-align-center");

        // Render a button that deletes the data row (item)
        grid.addColumn(schule -> "löschen",
                new ButtonRenderer(event -> {
                    Notification.show("Lösche Schulfach id:" + event.getItem(), Notification.Type.HUMANIZED_MESSAGE);
                    facade.delete((Schulfach) event.getItem());
                    updateList();
                })
        );

        grid.addColumn(schulefach -> "ändern",
                new ButtonRenderer(event -> {
                    form.setEntity((Schulfach) event.getItem());
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
