package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.util.field;

import org.vaadin.ui.NumberField;

import java.util.Locale;

public class BetragField extends NumberField {
    public BetragField() {
        super();
        setLocale(Locale.GERMAN);
        setDecimalPrecision(2);
        setDecimalSeparator('.');
        setGroupingSeparator('\'');
        setGroupingUsed(false);
        setDecimalSeparatorAlwaysShown(true);
        setMinimumFractionDigits(2);
    }

    public BetragField(String caption) {
        super();
        setCaption(caption);
        setLocale(Locale.GERMAN);
        setDecimalPrecision(2);
        setDecimalSeparator('.');
        setGroupingSeparator('\'');
        setGroupingUsed(false);
        setDecimalSeparatorAlwaysShown(true);
        setMinimumFractionDigits(2);
        //setMinValue(5);

    }
}
