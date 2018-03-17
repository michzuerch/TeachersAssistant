package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.buchhaltung.form;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Buchhaltung;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Konto;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Mehrwertsteuercode;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.BuchhaltungDeltaspikeFacade;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.KontoDeltaspikeFacade;
import com.vaadin.data.converter.StringToFloatConverter;
import com.vaadin.ui.*;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MehrwertsteuercodeForm extends AbstractForm<Mehrwertsteuercode> {

    @Inject
    BuchhaltungDeltaspikeFacade buchhaltungDeltaspikeFacade;

    @Inject
    KontoDeltaspikeFacade kontoDeltaspikeFacade;

    NativeSelect<Buchhaltung> buchhaltungNativeSelect = new NativeSelect<>();
    NativeSelect<Konto> kontoNativeSelect = new NativeSelect<>();
    TextField bezeichnung = new TextField("Bezeichnung");
    TextField code = new TextField("Code");
    TextField prozent = new TextField("Prozent");
    CheckBox verkauf = new CheckBox("Verkauf");

    public MehrwertsteuercodeForm() {
        super(Mehrwertsteuercode.class);
    }

    public void lockSelect() {
        buchhaltungNativeSelect.setEnabled(false);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Template Buchhaltung");
        openInModalPopup.setWidth("600px");
        return openInModalPopup;
    }

    private List<Konto> createTemplateKontoList(Buchhaltung buchhaltung) {
        List<Konto> list = new ArrayList<>();

        buchhaltung.getKontoklasse().stream().forEach(kontoklasse -> {
            kontoklasse.getKontohauptgruppes().stream().forEach(kontohauptgruppe -> {
                kontohauptgruppe.getKontogruppes().stream().forEach(kontogruppe -> {
                    kontogruppe.getKontos().stream().forEach(konto -> {
                        list.add(konto);
                    });
                });
            });
        });
        return list;
    }

    @Override
    protected Component createContent() {
        buchhaltungNativeSelect.setCaption("Buchhaltung");
        buchhaltungNativeSelect.setEmptySelectionAllowed(false);
        buchhaltungNativeSelect.setItemCaptionGenerator(item -> item.getBezeichnung() + " " + item.getId());
        buchhaltungNativeSelect.setItems(buchhaltungDeltaspikeFacade.findAll());
        buchhaltungNativeSelect.setSelectedItem(buchhaltungDeltaspikeFacade.findAll().get(0));

        kontoNativeSelect.setCaption("Konto");
        kontoNativeSelect.setEmptySelectionAllowed(false);
        kontoNativeSelect.setItemCaptionGenerator(item -> item.getBezeichnung() + " " + item.getId());
        kontoNativeSelect.setItems(createTemplateKontoList(buchhaltungNativeSelect.getValue()));


        buchhaltungNativeSelect.addValueChangeListener(event -> {
            kontoNativeSelect.setItems(createTemplateKontoList(buchhaltungNativeSelect.getValue()));
        });

        getBinder().forField(prozent).withConverter(
                new StringToFloatConverter("Muss Prozent Zahl sein")
        ).bind("prozent");

        return new VerticalLayout(new FormLayout(buchhaltungNativeSelect, kontoNativeSelect, code, bezeichnung, verkauf, prozent), getToolbar());
    }


}
