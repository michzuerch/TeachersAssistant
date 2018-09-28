package com.gmail.michzuerch.TeachersAssistant.presentation.ui.schoolclass;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.School;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.SchoolClass;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.SchoolDeltaspikeFacade;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import javax.inject.Inject;

public class SchoolClassForm extends AbstractForm<SchoolClass> {
    private static Logger logger = LoggerFactory.getLogger(SchoolClassForm.class.getName());

    @Inject
    SchoolDeltaspikeFacade schoolDeltaspikeFacade;

    TextField bezeichnung = new TextField("Bezeichnung");
    ComboBox<School> schule = new ComboBox<>("School");

    public SchoolClassForm() {
        super(SchoolClass.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("SchoolClass");
        openInModalPopup.setWidth("400px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        schule.setItems(schoolDeltaspikeFacade.findAll());
        schule.setItemCaptionGenerator(schule -> schule.getBezeichnung());

        return new VerticalLayout(new FormLayout(
                bezeichnung, schule
        ), getToolbar());
    }
}
