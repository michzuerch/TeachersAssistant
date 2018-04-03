package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.stundenplan;

import org.vaadin.addon.calendar.item.CalendarEditableItemProvider;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

public class StundenplanDataProvider implements CalendarEditableItemProvider<LektionItem> {
    public StundenplanDataProvider() {
        super();
    }

    @Override
    public void addItem(LektionItem item) {

    }

    @Override
    public void removeItem(LektionItem item) {

    }

    @Override
    public void setItems(Collection<LektionItem> items) {

    }

    @Override
    public List<LektionItem> getItems(ZonedDateTime startDate, ZonedDateTime endDate) {
        return null;
    }
}
