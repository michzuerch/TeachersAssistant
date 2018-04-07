package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.stundenplan;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lektion;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import com.vaadin.server.ErrorMessage;
import com.vaadin.shared.ui.ErrorLevel;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.form.AbstractForm;

import java.time.LocalDateTime;

public class LektionForm extends AbstractForm<Lektion> {
    private static Logger logger = LoggerFactory.getLogger(LektionForm.class.getName());

    TextField bezeichnung = new TextField("Bezeichnung");
    DateTimeField start = new DateTimeField("Start");
    DateTimeField ende = new DateTimeField("Ende");

    Button debugButton = new Button("Debug");

    public LektionForm() {
        super(Lektion.class);
    }

    @Override
    public Window openInModalPopup() {
        final Window openInModalPopup = super.openInModalPopup();
        openInModalPopup.setCaption("Lektion");
        openInModalPopup.setWidth("550px");
        return openInModalPopup;
    }

    @Override
    protected Component createContent() {
        debugButton.addClickListener(event -> start.setComponentError(new ErrorMessage() {
            @Override
            public ErrorLevel getErrorLevel() {
                return ErrorLevel.ERROR;
            }

            @Override
            public String getFormattedHtmlMessage() {
                return "Error";
            }
        }));


        getBinder().forField(ende).withValidator(new Validator<LocalDateTime>() {
            @Override
            public ValidationResult apply(LocalDateTime value, ValueContext context) {
                return ValidationResult.error("ValidationError");
            }
        });


        return new VerticalLayout(new FormLayout(
                bezeichnung, start, ende, debugButton
        ), getToolbar());
    }
}
