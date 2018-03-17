package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.buchhaltung;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.*;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.*;
import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.LoggerFactory;
import org.vaadin.teemusa.flexlayout.*;
import org.vaadin.ui.NumberField;

import javax.inject.Inject;
import java.util.Locale;

@CDIView("BuchungsmaskeView")
public class BuchungsmaskeView extends VerticalLayout implements View {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(BuchungsmaskeView.class.getName());
    @Inject
    BuchhaltungDeltaspikeFacade buchhaltungDeltaspikeFacade;
    @Inject
    KontoklasseDeltaspikeFacade kontoklasseDeltaspikeFacade;
    @Inject
    KontohauptgruppeDeltaspikeFacade kontohauptgruppeDeltaspikeFacade;
    @Inject
    KontogruppeDeltaspikeFacade kontogruppeDeltaspikeFacade;
    @Inject
    KontoDeltaspikeFacade kontoDeltaspikeFacade;
    @Inject
    MehrwertsteuercodeDeltaspikeFacade mehrwertsteuercodeDeltaspikeFacade;

    private NativeSelect<Buchhaltung> buchhaltungNativeSelect = new NativeSelect<>();
    private Panel buchenPanel = new Panel("Buchen");
    private NativeSelect<Mehrwertsteuercode> mehrwertsteuercodeNativeSelect = new NativeSelect<>();
    private NumberField betragField = new NumberField("Betrag");
    private Button buchenButton = new Button("Buchen");
    private HorizontalLayout buchenLayout = new HorizontalLayout();

    private Panel createHabenPanel() {
        Panel habenPanel = new Panel("Habenkonto");
        NativeSelect<Kontoklasse> habenKontoklasse = new NativeSelect<>();
        NativeSelect<Kontohauptgruppe> habenKontohauptgruppe = new NativeSelect<>();
        NativeSelect<Kontogruppe> habenKontogruppe = new NativeSelect<>();
        NativeSelect<Konto> habenKonto = new NativeSelect<>();
        Label habenKontoLabel = new Label("Kontonummer Haben:");
        HorizontalLayout habenKontoLayout = new HorizontalLayout();

        habenKontoklasse.setCaption("Kontoklasse");
        habenKontoklasse.setItemCaptionGenerator(kontoklasse -> kontoklasse.getBezeichnung() + " " + kontoklasse.getShowKontonummer());
        habenKontoklasse.setEmptySelectionAllowed(false);

        habenKontohauptgruppe.setCaption("Kontohauptgruppe");
        habenKontohauptgruppe.setItemCaptionGenerator(kontohauptgruppe -> kontohauptgruppe.getBezeichnung() + " " + kontohauptgruppe.getShowKontonummer());
        habenKontohauptgruppe.setEmptySelectionAllowed(false);

        habenKontogruppe.setCaption("Kontogruppe");
        habenKontogruppe.setItemCaptionGenerator(kontogruppe -> kontogruppe.getBezeichnung() + " " + kontogruppe.getShowKontonummer());
        habenKontogruppe.setEmptySelectionAllowed(false);

        habenKonto.setCaption("Konto");
        habenKonto.setItemCaptionGenerator(konto -> konto.getBezeichnung() + " " + konto.getShowKontonummer());
        habenKonto.setEmptySelectionAllowed(false);

        habenKontoLabel.setStyleName(ValoTheme.LABEL_HUGE);
        habenKontoLayout.addComponents(habenKontoklasse, habenKontohauptgruppe, habenKontogruppe, habenKonto, habenKontoLabel);
        habenKontoLayout.setMargin(true);
        habenPanel.setContent(habenKontoLayout);
        habenKontoklasse.setItems(kontoklasseDeltaspikeFacade.findByBuchhaltung(buchhaltungNativeSelect.getValue()));
        habenKontoklasse.setSelectedItem(kontoklasseDeltaspikeFacade.findAll().get(0));

        habenKontoklasse.addValueChangeListener(
                valueChangeEvent -> {
                    habenKontohauptgruppe.setItems(kontohauptgruppeDeltaspikeFacade.findByKontoklasse(valueChangeEvent.getValue()));
                    habenKontohauptgruppe.setSelectedItem(kontohauptgruppeDeltaspikeFacade.findByKontoklasse(valueChangeEvent.getValue()).get(0));
                });
        habenKontohauptgruppe.addValueChangeListener(
                valueChangeEvent -> {
                    habenKontogruppe.setItems(kontogruppeDeltaspikeFacade.findByKontohauptgruppe(valueChangeEvent.getValue()));
                    habenKontogruppe.setSelectedItem(kontogruppeDeltaspikeFacade.findByKontohauptgruppe(valueChangeEvent.getValue()).get(0));
                });

        habenKontogruppe.addValueChangeListener(
                valueChangeEvent -> {
                    habenKonto.setItems(kontoDeltaspikeFacade.findByKontogruppe(valueChangeEvent.getValue()));
                    habenKonto.setSelectedItem(kontoDeltaspikeFacade.findByKontogruppe(valueChangeEvent.getValue()).get(0));
                });

        habenKonto.addValueChangeListener(
                valueChangeEvent -> {
                    habenKontoLabel.setValue("Konto Haben: " + valueChangeEvent.getValue().getShowKontonummer());
                });


        return habenPanel;
    }

    private Panel createSollPanel() {
        Panel sollPanel = new Panel("Sollkonto");
        NativeSelect<Kontoklasse> sollKontoklasse = new NativeSelect<>();
        NativeSelect<Kontohauptgruppe> sollKontohauptgruppe = new NativeSelect<>();
        NativeSelect<Kontogruppe> sollKontogruppe = new NativeSelect<>();
        NativeSelect<Konto> sollKonto = new NativeSelect<>();
        Label sollKontoLabel = new Label("Kontonummer Soll:");
        HorizontalLayout sollKontoLayout = new HorizontalLayout();

        sollKontoklasse.setCaption("Kontoklasse");
        sollKontoklasse.setItemCaptionGenerator(kontoklasse -> kontoklasse.getBezeichnung() + " " + kontoklasse.getShowKontonummer());
        sollKontoklasse.setEmptySelectionAllowed(false);

        sollKontohauptgruppe.setCaption("Kontohauptgruppe");
        sollKontohauptgruppe.setItemCaptionGenerator(kontohauptgruppe -> kontohauptgruppe.getBezeichnung() + " " + kontohauptgruppe.getShowKontonummer());
        sollKontohauptgruppe.setEmptySelectionAllowed(false);

        sollKontogruppe.setCaption("Kontogruppe");
        sollKontogruppe.setItemCaptionGenerator(kontogruppe -> kontogruppe.getBezeichnung() + " " + kontogruppe.getShowKontonummer());
        sollKontogruppe.setEmptySelectionAllowed(false);

        sollKonto.setCaption("Konto");
        sollKonto.setItemCaptionGenerator(konto -> konto.getBezeichnung() + " " + konto.getShowKontonummer());
        sollKonto.setEmptySelectionAllowed(false);

        sollKontoLabel.setStyleName(ValoTheme.LABEL_HUGE);
        sollKontoLayout.setMargin(true);
        sollKontoLayout.addComponents(sollKontoklasse, sollKontohauptgruppe, sollKontogruppe, sollKonto, sollKontoLabel);
        sollPanel.setContent(sollKontoLayout);

        sollKontoklasse.setItems(kontoklasseDeltaspikeFacade.findByBuchhaltung(buchhaltungNativeSelect.getValue()));
        sollKontoklasse.setSelectedItem(kontoklasseDeltaspikeFacade.findAll().get(0));
        sollKontoklasse.addValueChangeListener(
                valueChangeEvent -> {
                    sollKontohauptgruppe.setItems(kontohauptgruppeDeltaspikeFacade.findByKontoklasse(valueChangeEvent.getValue()));
                    sollKontohauptgruppe.setSelectedItem(kontohauptgruppeDeltaspikeFacade.findByKontoklasse(valueChangeEvent.getValue()).get(0));
                });
        sollKontohauptgruppe.addValueChangeListener(
                valueChangeEvent -> {
                    sollKontogruppe.setItems(kontogruppeDeltaspikeFacade.findByKontohauptgruppe(valueChangeEvent.getValue()));
                    sollKontogruppe.setSelectedItem(kontogruppeDeltaspikeFacade.findByKontohauptgruppe(valueChangeEvent.getValue()).get(0));
                });

        sollKontogruppe.addValueChangeListener(
                valueChangeEvent -> {
                    sollKonto.setItems(kontoDeltaspikeFacade.findByKontogruppe(valueChangeEvent.getValue()));
                    sollKonto.setSelectedItem(kontoDeltaspikeFacade.findByKontogruppe(valueChangeEvent.getValue()).get(0));
                });

        sollKonto.addValueChangeListener(
                valueChangeEvent -> {
                    sollKontoLabel.setValue("Konto Soll: " + valueChangeEvent.getValue().getShowKontonummer());
                });


        return sollPanel;
    }


    private Component createContent() {
        FlexLayout layout = new FlexLayout();

        layout.setFlexDirection(FlexDirection.Row);
        layout.setAlignItems(AlignItems.FlexEnd);
        layout.setJustifyContent(JustifyContent.SpaceBetween);
        layout.setAlignContent(AlignContent.Stretch);
        layout.setFlexWrap(FlexWrap.Wrap);

        buchhaltungNativeSelect.setCaption("Buchhaltung");
        buchhaltungNativeSelect.setEmptySelectionAllowed(false);
        buchhaltungNativeSelect.setItemCaptionGenerator(buchhaltung -> buchhaltung.getBezeichnung() + " " + buchhaltung.getJahr() + " " + buchhaltung.getId());
        buchhaltungNativeSelect.setItems(buchhaltungDeltaspikeFacade.findAll());
        buchhaltungNativeSelect.setSelectedItem(buchhaltungDeltaspikeFacade.findAll().get(0));


        addComponent(buchhaltungNativeSelect);
        addComponent(new HorizontalLayout(createSollPanel(), createHabenPanel()));

        layout.setSizeFull();
        return layout;
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        mehrwertsteuercodeNativeSelect.setItemCaptionGenerator(mehrwertsteuercode -> mehrwertsteuercode.getBezeichnung());
        mehrwertsteuercodeNativeSelect.setEmptySelectionAllowed(false);
        mehrwertsteuercodeNativeSelect.setItems(mehrwertsteuercodeDeltaspikeFacade.findByBuchhaltung(buchhaltungNativeSelect.getValue()));
        mehrwertsteuercodeNativeSelect.setCaption("Mehrwertsteuercode");


        betragField.setLocale(Locale.GERMAN);
        betragField.setDecimalPrecision(2);
        betragField.setDecimalSeparator('.');
        betragField.setGroupingSeparator('\'');
        betragField.setDecimalSeparatorAlwaysShown(true);
        betragField.setMinimumFractionDigits(2);
        betragField.setMinValue(5);

//        getBinder().forField(stundensatz).withConverter(
//                NumberField.getConverter("Muss Betrag sein")
//        ).bind("stundensatz");

        buchenLayout.addComponents(mehrwertsteuercodeNativeSelect, betragField, buchenButton);
        buchenLayout.setMargin(true);
        buchenPanel.setContent(buchenLayout);
        addComponent(buchenPanel);


        addComponent(createContent());
        setSizeFull();

    }
}
