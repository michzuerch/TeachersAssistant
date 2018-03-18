package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.lehrer;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lehrer;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.LehrerDeltaspikeFacade;
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

@CDIView("LehrerView")
public class LehrerView extends HorizontalLayout implements View {
    private static Logger logger = LoggerFactory.getLogger(LehrerView.class.getName());

    TextField filterTextNachname = new TextField();
    ComboBox<Schule> filterSchule = new ComboBox<>();

    Grid<Lehrer> grid = new Grid<>();

    @Inject
    private LehrerDeltaspikeFacade facade;

    @Inject
    private LehrerForm form;

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
            Lehrer lehrer = new Lehrer();
            form.setEntity(lehrer);
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

        grid.addColumn(Lehrer::getId).setCaption("id");
        grid.addColumn(Lehrer::getVorname).setCaption("Vorname");
        grid.addColumn(Lehrer::getNachname).setCaption("Nachname");
        grid.addColumn(lehrer -> lehrer.getSchule().getBezeichnung(), new ButtonRenderer(event -> {
            Lehrer lehrer = (Lehrer) event.getItem();
            UI.getCurrent().getNavigator().navigateTo("SchuleView/id/" + lehrer.getSchule().getId().toString());
        })).setCaption("Schule").setStyleGenerator(item -> "v-align-center");
        grid.setSizeFull();

        // Render a button that deletes the data row (item)
        grid.addColumn(adresse -> "löschen",
                new ButtonRenderer(event -> {
                    Notification.show("Lösche Lehrer id:" + event.getItem(), Notification.Type.HUMANIZED_MESSAGE);
                    facade.delete((Lehrer) event.getItem());
                    updateList();
                })
        );

        grid.addColumn(adresse -> "ändern",
                new ButtonRenderer(event -> {
                    form.setEntity((Lehrer) event.getItem());
                    form.openInModalPopup();
                    form.setSavedHandler(val -> {
                        facade.save(val);
                        System.err.println("Lehrer:" + val);
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
