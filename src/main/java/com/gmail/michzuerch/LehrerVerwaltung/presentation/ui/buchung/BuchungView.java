package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.buchung;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Buchung;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.BuchungDeltaspikeFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.teemusa.flexlayout.*;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@CDIView("BuchungView")
public class BuchungView extends VerticalLayout implements View {
    private static final Logger LOGGER = Logger.getLogger(BuchungView.class.getName());

    TextField filterText = new TextField();
    Grid<Buchung> grid = new Grid<>(Buchung.class);

    @Inject
    private BuchungDeltaspikeFacade service;

    @Inject
    private BuchungForm form;

    private Component createContent() {
        FlexLayout layout = new FlexLayout();

        layout.setFlexDirection(FlexDirection.Row);
        layout.setAlignItems(AlignItems.FlexEnd);
        layout.setJustifyContent(JustifyContent.SpaceBetween);
        layout.setAlignContent(AlignContent.Stretch);
        layout.setFlexWrap(FlexWrap.Wrap);

        filterText.setPlaceholder("Filter für Erster...");
        filterText.addValueChangeListener(e -> updateList());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn = new Button(VaadinIcons.RECYCLE);
        clearFilterTextBtn.setDescription("Entferne Filter");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());

        Button addBtn = new Button(VaadinIcons.PLUS);
        addBtn.addClickListener(event -> {
            grid.asSingleSelect().clear();
            form.setEntity(new Buchung());
            form.openInModalPopup();
            form.setSavedHandler(val -> {
                service.save(val);
                updateList();
                grid.select(val);
                form.closePopup();
            });
        });

        CssLayout tools = new CssLayout();
        tools.addComponents(filterText, clearFilterTextBtn, addBtn);
        tools.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        grid.setCaption("Buchung");
        grid.setCaptionAsHtml(true);
        grid.setColumns("id");
        grid.setSizeFull();

        // Render a button that deletes the data row (item)
        grid.addColumn(buchung -> "löschen",
                new ButtonRenderer(event -> {
                    Notification.show("Lösche Buchung id:" + event.getItem(), Notification.Type.HUMANIZED_MESSAGE);
                    service.delete((Buchung) event.getItem());
                    updateList();
                })
        );

        grid.addColumn(buchung -> "ändern",
                new ButtonRenderer(event -> {
                    form.setEntity((Buchung) event.getItem());
                    form.openInModalPopup();
                    form.setSavedHandler(val -> {
                        service.save(val);
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

        updateList();
    }

    public void updateList() {
        List<Buchung> items;
        /*
        if (!filterText.isEmpty()) {
            items = service.findByErsterLike(filterText.getValue().toLowerCase());
        } else {
            items = service.findAll();
        }
        */
        items = service.findAll();
        grid.setItems(items);
    }

}
