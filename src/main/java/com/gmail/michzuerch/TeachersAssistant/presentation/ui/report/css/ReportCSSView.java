package com.gmail.michzuerch.TeachersAssistant.presentation.ui.report.css;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.report.css.ReportCSS;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.ReportCSSDeltaspikeFacade;
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
import java.io.File;

@CDIView("ReportCSSView")
public class ReportCSSView extends HorizontalLayout implements View {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ReportCSSView.class.getName());

    File tempFile;
    String filename;

    Grid<ReportCSS> grid = new Grid<>();
    TextField filterTextBezeichnung = new TextField();

    @Inject
    private ReportCSSDeltaspikeFacade facade;

    @Inject
    private ReportCSSForm form;

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
            ReportCSS reportCSS = new ReportCSS();
            form.setEntity(reportCSS);
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

        grid.addColumn(ReportCSS::getId).setCaption("id");
        grid.addColumn(ReportCSS::getBezeichnung).setCaption("Bezeichnung");
        grid.addColumn(ReportCSS::getSizeCSS).setCaption("CSS Grösse");
        grid.addColumn(ReportCSS::getSizeHTML).setCaption("HTML Grösse");
        grid.addColumn(ReportCSS::getSizeJavascript).setCaption("Javascript Grösse");

        // Render a button that deletes the data row (item)
        grid.addColumn(report -> "löschen",
                new ButtonRenderer(event -> {
                    Notification.show("Lösche id:" + event.getItem(), Notification.Type.HUMANIZED_MESSAGE);
                    facade.delete((ReportCSS) event.getItem());
                    updateList();
                })
        );
        grid.addColumn(report -> "ändern",
                new ButtonRenderer(event -> {
                    form.setEntity((ReportCSS) event.getItem());
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


        //@todo Downloadbutton für Report
        grid.setSizeFull();

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

    private void updateList() {
        if (!filterTextBezeichnung.isEmpty()) {
            //Suche mit Bezeichnung
            logger.debug("Suche mit Bezeichnung:" + filterTextBezeichnung.getValue());
            grid.setItems(facade.findByBezeichnungLikeIgnoreCase(filterTextBezeichnung.getValue() + "%"));
            return;
        }
        grid.setItems(facade.findAll());
    }
}
