package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.schueler;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schueler;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.SchuelerDeltaspikeFacade;
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

@CDIView("SchuelerView")
public class SchuelerView extends HorizontalLayout implements View {
    private static Logger logger = LoggerFactory.getLogger(SchuelerView.class.getName());

    TextField filterTextNachname = new TextField();

    Grid<Schueler> grid = new Grid<>();

    @Inject
    private SchuelerDeltaspikeFacade facade;

    @Inject
    private SchuelerForm form;

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

        Button clearFilterTextBtn = new Button(VaadinIcons.RECYCLE);
        clearFilterTextBtn.setDescription("Entferne Filter");
        clearFilterTextBtn.addClickListener(e -> {
            filterTextNachname.clear();
        });

        Button addBtn = new Button(VaadinIcons.PLUS);
        addBtn.addClickListener(event -> {
            grid.asSingleSelect().clear();
            Schueler schueler = new Schueler();
            form.setEntity(schueler);
            form.openInModalPopup();
            form.setSavedHandler(val -> {
                facade.save(val);
                updateList();
                grid.select(val);
                form.closePopup();
            });
        });

        CssLayout tools = new CssLayout();
        tools.addComponents(filterTextNachname, clearFilterTextBtn, addBtn);
        tools.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        grid.addColumn(Schueler::getId).setCaption("id");
        grid.addColumn(Schueler::getNachname).setCaption("Nachname");
        grid.addColumn(Schueler::getVorname).setCaption("Vorname");


//        grid.addColumn(adresse -> adresse.getAnzahlRechnungen(), new ButtonRenderer(event -> {
//            Adresse adresse = (Adresse) event.getItem();
//            if (adresse.getAnzahlRechnungen() > 0) {
//                UI.getCurrent().getNavigator().navigateTo("RechnungView/adresseId/" + adresse.getId().toString());
//            }
//        })).setCaption("Anzahl Rechnungen").setStyleGenerator(item -> "v-align-center");
        grid.setSizeFull();

        // Render a button that deletes the data row (item)
        grid.addColumn(schueler -> "löschen",
                new ButtonRenderer(event -> {
                    Notification.show("Lösche Schueler id:" + event.getItem(), Notification.Type.HUMANIZED_MESSAGE);
                    facade.delete((Schueler) event.getItem());
                    updateList();
                })
        );

        grid.addColumn(schueler -> "ändern",
                new ButtonRenderer(event -> {
                    form.setEntity((Schueler) event.getItem());
                    form.openInModalPopup();
                    form.setSavedHandler(val -> {
                        facade.save(val);
                        System.err.println("Schueler:" + val);
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
        if (!filterTextNachname.isEmpty()) {
            //Suche mit Nachname
            logger.debug("Suche mit Nachname:" + filterTextNachname.getValue());
            grid.setItems(facade.findByNachnameLikeIgnoreCase(filterTextNachname.getValue() + "%"));
            return;
        }

        grid.setItems(facade.findAll());
    }

}
