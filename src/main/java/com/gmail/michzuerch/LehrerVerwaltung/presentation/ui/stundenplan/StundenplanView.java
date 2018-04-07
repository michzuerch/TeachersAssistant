package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.stundenplan;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lektion;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schulraum;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.LektionDeltaspikeFacade;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.SchulraumDeltaspikeFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemusa.flexlayout.*;

import javax.inject.Inject;

@CDIView("StundenplanView")
public class StundenplanView extends HorizontalLayout implements View {
    private static Logger logger = LoggerFactory.getLogger(StundenplanView.class.getName());

    TextField filterTextBezeichnung = new TextField();
    ComboBox<Schulraum> filterSchulraum = new ComboBox<Schulraum>();
    StundenplanCalendar stundenplanCalendar = new StundenplanCalendar();

    Button debugbutton = new Button("Debug");

    @Inject
    private LektionDeltaspikeFacade lektionDeltaspikeFacade;

    @Inject
    private SchulraumDeltaspikeFacade schulraumDeltaspikeFacade;

    @Inject
    private LektionForm form;


    private Component createContent() {
        FlexLayout layout = new FlexLayout();

        layout.setFlexDirection(FlexDirection.Row);
        layout.setAlignItems(AlignItems.FlexEnd);
        layout.setJustifyContent(JustifyContent.SpaceBetween);
        layout.setAlignContent(AlignContent.Stretch);
        layout.setFlexWrap(FlexWrap.Wrap);

        filterTextBezeichnung.setPlaceholder("Filter für Titel");
        filterTextBezeichnung.addValueChangeListener(e -> updateList());
        filterTextBezeichnung.setValueChangeMode(ValueChangeMode.LAZY);
        filterSchulraum.setPlaceholder("Filter für Schulraum");
        filterSchulraum.setItems(schulraumDeltaspikeFacade.findAll());
        filterSchulraum.setItemCaptionGenerator(item -> item.getBezeichnung() + " id:" + item.getId());
        filterSchulraum.addValueChangeListener(valueChangeEvent -> updateList());
        Button clearFilterTextBtn = new Button(VaadinIcons.RECYCLE);
        clearFilterTextBtn.setDescription("Entferne Filter");
        clearFilterTextBtn.addClickListener(e -> {
            filterTextBezeichnung.clear();
            filterSchulraum.clear();
        });

        Button addBtn = new Button(VaadinIcons.PLUS);
        addBtn.addClickListener(event -> {
            //grid.asSingleSelect().clear();
            Lektion lektion = new Lektion();
            lektion.setSchulraum(schulraumDeltaspikeFacade.findAll().get(0));
            if (!filterSchulraum.isEmpty()) lektion.setSchulraum(filterSchulraum.getValue());
            form.setEntity(lektion);
            form.openInModalPopup();
            form.setSavedHandler(aufwand1 -> {
                lektionDeltaspikeFacade.save(aufwand1);
                updateList();
                //grid.select(aufwand1);
                form.closePopup();
            });
        });

        debugbutton.addClickListener(event -> {
            //lektionDeltaspikeFacade.find
        });

        CssLayout tools = new CssLayout();
        tools.addComponents(filterSchulraum, filterTextBezeichnung, clearFilterTextBtn, addBtn, debugbutton);
        tools.setWidth(50, Unit.PERCENTAGE);
        tools.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        layout.addComponents(tools, stundenplanCalendar);
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
//
//        if ((!filterRechnung.isEmpty()) && (!filterTextBezeichnung.isEmpty())) {
//            // Such mit Rechnung und Titel
//            logger.debug("Suche mit Rechnung und Bezeichnung:" + filterRechnung.getValue().getId() + "," + filterTextBezeichnung.getValue());
//            grid.setItems(rechnungspositionDeltaspikeFacade.findByRechnungAndBezeichnungLikeIgnoreCase(filterRechnung.getValue(), filterTextBezeichnung.getValue() + "%"));
//            return;
//        } else if ((!filterRechnung.isEmpty()) && (filterTextBezeichnung.isEmpty())) {
//            // Suche mit Rechnung
//            logger.debug("Suche mit Rechnung:" + filterRechnung.getValue().getId());
//            grid.setItems(rechnungspositionDeltaspikeFacade.findByRechnung(filterRechnung.getValue()));
//            return;
//        } else if ((filterRechnung.isEmpty()) && (!filterTextBezeichnung.isEmpty())) {
//            // Suche mit Bezeichnung
//            logger.debug("Suche mit Bezeichnung:" + filterTextBezeichnung.getValue());
//            grid.setItems(rechnungspositionDeltaspikeFacade.findByBezeichnungLikeIgnoreCase(filterTextBezeichnung.getValue() + "%"));
//            return;
//        }
//        grid.setItems(rechnungspositionDeltaspikeFacade.findAll());
//
    }

}
