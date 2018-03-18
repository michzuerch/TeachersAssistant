package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.klasse;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Klasse;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.KlasseDeltaspikeFacade;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.SchuleDeltaspikeFacade;
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

@CDIView("KlasseView")
public class KlasseView extends HorizontalLayout implements View {
    private static Logger logger = LoggerFactory.getLogger(KlasseView.class.getName());

    TextField filterTextBezeichnung = new TextField();
    ComboBox<Schule> filterSchule = new ComboBox<>();

    Grid<Klasse> grid = new Grid<>();

    @Inject
    private KlasseDeltaspikeFacade facade;

    @Inject
    private SchuleDeltaspikeFacade schuleDeltaspikeFacade;

    @Inject
    private KlasseForm form;

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

        filterSchule.setPlaceholder("Filter für Schule");
        filterSchule.setItems(schuleDeltaspikeFacade.findAll());
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
            Klasse klasse = new Klasse();
            if (filterSchule.getValue() != null) klasse.setSchule(filterSchule.getValue());
            form.setEntity(klasse);
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

        grid.addColumn(Klasse::getId).setCaption("id");
        grid.addColumn(Klasse::getBezeichnung).setCaption("Bezeichnung");
        grid.addColumn(klasse -> klasse.getSchuelers().size(), new ButtonRenderer(event -> {
            Klasse klasse = (Klasse) event.getItem();
            if (klasse.getSchuelers().size() > 0) {
                UI.getCurrent().getNavigator().navigateTo("SchuelerView/klasseId/" + klasse.getId().toString());
            }
        })).setCaption("Anzahl Schüler").setStyleGenerator(item -> "v-align-center");
        grid.addColumn(klasse -> klasse.getSchule().getBezeichnung(), new ButtonRenderer(event -> {
            Klasse klasse = (Klasse) event.getItem();
            UI.getCurrent().getNavigator().navigateTo("SchuleView/id/" + klasse.getSchule().getId().toString());
        })).setCaption("Schule").setStyleGenerator(item -> "v-align-center");


        grid.setSizeFull();

        // Render a button that deletes the data row (item)
        grid.addColumn(adresse -> "löschen",
                new ButtonRenderer(event -> {
                    Notification.show("Lösche Klasse id:" + event.getItem(), Notification.Type.HUMANIZED_MESSAGE);
                    facade.delete((Klasse) event.getItem());
                    updateList();
                })
        );

        grid.addColumn(adresse -> "ändern",
                new ButtonRenderer(event -> {
                    form.setEntity((Klasse) event.getItem());
                    form.openInModalPopup();
                    form.setSavedHandler(val -> {
                        facade.save(val);
                        System.err.println("Klasse:" + val);
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
            if (target.equals("schuleId")) {
                filterSchule.setSelectedItem(schuleDeltaspikeFacade.findBy(id));
                updateList();
            } else if (target.equals("id")) {
                grid.select(facade.findBy(id));
            }
        }
        updateList();
    }

    public void updateList() {
        if (filterSchule.getValue() != null) {
            logger.debug("Suche mit Schule:" + filterSchule.getValue().getBezeichnung());
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
