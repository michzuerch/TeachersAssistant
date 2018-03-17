package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.artikelkategorie;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Artikelkategorie;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.ArtikelkategorieDeltaspikeFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.LoggerFactory;
import org.vaadin.teemusa.flexlayout.*;

import javax.inject.Inject;


// @todo : java.lang.IllegalStateException: Property type 'java.util.Date' doesn't match the field type 'java.time.LocalDateTime'.
// Binding should be configured manually using converter.
@CDIView("ArtikelkategorieView")
public class ArtikelkategorieView extends VerticalLayout implements View {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ArtikelkategorieView.class.getName());

    TextField filterTextBezeichnung = new TextField();
    Grid<Artikelkategorie> grid = new Grid<>();

    @Inject
    private ArtikelkategorieDeltaspikeFacade artikelkategorieDeltaspikeFacade;

    @Inject
    private ArtikelkategorieForm artikelkategorieForm;

    private Component createContent() {
        FlexLayout layout = new FlexLayout();

        layout.setFlexDirection(FlexDirection.Row);
        layout.setAlignItems(AlignItems.FlexEnd);
        layout.setJustifyContent(JustifyContent.SpaceBetween);
        layout.setAlignContent(AlignContent.Stretch);
        layout.setFlexWrap(FlexWrap.Wrap);

        filterTextBezeichnung.setPlaceholder("Filter Bezeichnung");
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
            Artikelkategorie artikelkategorie = new Artikelkategorie();
            artikelkategorieForm.setEntity(artikelkategorie);
            artikelkategorieForm.openInModalPopup();
            artikelkategorieForm.setSavedHandler(val -> {
                artikelkategorieDeltaspikeFacade.save(val);
                updateList();
                grid.select(val);
                artikelkategorieForm.closePopup();
            });
        });

        CssLayout tools = new CssLayout();
        tools.addComponents(filterTextBezeichnung, clearFilterTextBtn, addBtn);
        tools.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        grid.addColumn(Artikelkategorie::getId).setCaption("id");
        grid.addColumn(Artikelkategorie::getBezeichnung).setCaption("Bezeichnung");
        grid.addColumn(artikelkategorie -> artikelkategorie.getArtikels().size(), new ButtonRenderer(event -> {
            Artikelkategorie artikelkategorie = (Artikelkategorie) event.getItem();
            if (artikelkategorie.getArtikels().size() > 0) {
                UI.getCurrent().getNavigator().navigateTo("ArtikelView/artikelkategorieId/" + artikelkategorie.getId().toString());

            }
        })).setCaption("Anzahl Artikel").setStyleGenerator(item -> "v-align-center");


        grid.setSizeFull();

        // Render a button that deletes the data row (item)
        grid.addColumn(aufwand -> "löschen",
                new ButtonRenderer(event -> {
                    Artikelkategorie artikelkategorie = (Artikelkategorie) event.getItem();
                    Notification.show("Lösche Artikelkategorie id:" + artikelkategorie.getId(), Notification.Type.HUMANIZED_MESSAGE);
                    artikelkategorieDeltaspikeFacade.delete(artikelkategorie);
                    updateList();
                })
        );

        grid.addColumn(adresse -> "ändern",
                new ButtonRenderer(event -> {
                    Artikelkategorie artikelkategorie = (Artikelkategorie) event.getItem();
                    artikelkategorieForm.setEntity(artikelkategorie);
                    artikelkategorieForm.openInModalPopup();
                    artikelkategorieForm.setSavedHandler(val -> {
                        artikelkategorieDeltaspikeFacade.save(val);
                        updateList();
                        grid.select(val);
                        artikelkategorieForm.closePopup();
                    });
                    artikelkategorieForm.setResetHandler(val -> {
                        updateList();
                        grid.select(val);
                        artikelkategorieForm.closePopup();
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
                grid.select(artikelkategorieDeltaspikeFacade.findBy(id));
            }
        }

        updateList();
    }

    public void updateList() {
        if (!filterTextBezeichnung.isEmpty()) {
            //Suche mit Bezeichnung
            logger.debug("Suche mit Bezeichnung:" + filterTextBezeichnung.getValue());
            grid.setItems(artikelkategorieDeltaspikeFacade.findByBezeichnungLikeIgnoreCase(filterTextBezeichnung.getValue() + "%"));
            return;
        }
        grid.setItems(artikelkategorieDeltaspikeFacade.findAll());
    }

}
