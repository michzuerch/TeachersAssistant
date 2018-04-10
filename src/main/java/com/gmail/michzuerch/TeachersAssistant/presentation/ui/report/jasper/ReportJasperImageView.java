package com.gmail.michzuerch.TeachersAssistant.presentation.ui.report.jasper;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.report.jasper.ReportJasper;
import com.gmail.michzuerch.TeachersAssistant.backend.entity.report.jasper.ReportJasperImage;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.ReportJasperDeltaspikeFacade;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.ReportJasperImageDeltaspikeFacade;
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

@CDIView("ReportJasperImageView")
public class ReportJasperImageView extends HorizontalLayout implements View {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ReportJasperImageView.class.getName());

    Grid<ReportJasperImage> grid = new Grid<>();
    TextField filterTextBezeichnung = new TextField();

    @Inject
    private ReportJasperDeltaspikeFacade reportJasperDeltaspikeFacade;

    @Inject
    private ReportJasperImageDeltaspikeFacade reportJasperImageDeltaspikeFacade;

    @Inject
    private ReportJasperImageForm form;


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
            ReportJasperImage reportJasperImage = new ReportJasperImage();
            form.setEntity(reportJasperImage);
            form.openInModalPopup();
            form.setSavedHandler(val -> {
                System.err.println("Save:" + val);
                val.setMimeType(form.image.getMimetype());
                reportJasperImageDeltaspikeFacade.save(val);
                updateList();
                grid.select(val);
                form.closePopup();
            });
        });


        CssLayout tools = new CssLayout();
        tools.addComponents(filterTextBezeichnung, clearFilterTextBtn, addBtn);
        tools.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        grid.addColumn(ReportJasperImage::getId).setCaption("id");
        grid.addColumn(ReportJasperImage::getBezeichnung).setCaption("Bezeichnung");
        grid.addColumn(ReportJasperImage::getMimeType).setCaption("MIME Type");
        grid.addColumn(ReportJasperImage::getSize).setCaption("Grösse");

        // Render a button that deletes the data row (item)
        grid.addColumn(report -> "löschen",
                new ButtonRenderer(event -> {
                    ReportJasperImage reportJasperImage = (ReportJasperImage) event.getItem();
                    ReportJasper reportJasper = reportJasperDeltaspikeFacade.findBy(reportJasperImage.getReportJasper().getId());
                    reportJasper.getReportJasperImages().remove(reportJasperImage);
                    reportJasperDeltaspikeFacade.save(reportJasper);
                    reportJasperImageDeltaspikeFacade.delete((ReportJasperImage) event.getItem());
                    updateList();
                    Notification.show("Lösche id:" + reportJasperImage.getId(), Notification.Type.HUMANIZED_MESSAGE);
                })
        );
        grid.addColumn(report -> "ändern",
                new ButtonRenderer(event -> {
                    form.setEntity((ReportJasperImage) event.getItem());
                    form.openInModalPopup();
                    form.setSavedHandler(val -> {
                        //val.setTemplateCompiled(form.getCompiledReport());
                        val.setMimeType(form.image.getMimetype());
                        reportJasperImageDeltaspikeFacade.save(val);
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
            grid.setItems(reportJasperImageDeltaspikeFacade.findByBezeichnungLikeIgnoreCase(filterTextBezeichnung.getValue() + "%"));
            return;
        }
        grid.setItems(reportJasperImageDeltaspikeFacade.findAll());
    }
}
