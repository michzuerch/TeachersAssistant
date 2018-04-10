package com.gmail.michzuerch.TeachersAssistant.presentation.ui.stundenplan;

import org.vaadin.addon.calendar.item.CalendarEditableItemProvider;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

public class StundenplanDataProvider implements CalendarEditableItemProvider<LessionItem> {
    public StundenplanDataProvider() {
        super();
    }

    @Override
    public void addItem(LessionItem item) {

    }

    @Override
    public void removeItem(LessionItem item) {

    }

    @Override
    public void setItems(Collection<LessionItem> items) {

    }

    @Override
    public List<LessionItem> getItems(ZonedDateTime startDate, ZonedDateTime endDate) {
        return null;
    }
}
