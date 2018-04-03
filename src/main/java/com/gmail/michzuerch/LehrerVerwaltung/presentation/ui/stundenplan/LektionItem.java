package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.stundenplan;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lektion;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.LektionDeltaspikeFacade;
import com.vaadin.cdi.ViewScoped;
import com.vaadin.icons.VaadinIcons;
import org.vaadin.addon.calendar.item.EditableCalendarItem;

import javax.inject.Inject;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ViewScoped
public class LektionItem implements EditableCalendarItem {

    @Inject
    LektionDeltaspikeFacade lektionDeltaspikeFacade;

    private Lektion lektion;

    public LektionItem() {
        lektion = lektionDeltaspikeFacade.findAll().get(0);
    }

    public LektionItem(Lektion val) {
        lektion = val;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LektionItem)) {
            return false;
        }
        LektionItem that = (LektionItem) o;
        return getLektion().equals(that.getLektion());
    }


    public Lektion getLektion() {
        return lektion;
    }

    @Override
    public ZonedDateTime getStart() {
        return null;
    }

    @Override
    public void setStart(ZonedDateTime start) {
        getLektion().setStart(start.toLocalDateTime());
    }

    @Override
    public ZonedDateTime getEnd() {
        return getLektion().getEnde().atZone(ZoneId.systemDefault());
    }

    @Override
    public void setEnd(ZonedDateTime end) {
        getLektion().setEnde(end.toLocalDateTime());
    }

    @Override
    public String getCaption() {
        return null;
    }

    @Override
    public void setCaption(String caption) {
        getLektion().getBezeichnung();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public String getStyleName() {
        return "state-" + getLektion().getBezeichnung().toLowerCase();
    }


//    @Override
//    public boolean isClickable() {
//        return lektion.isEditable();
//    }

    @Override
    public void setStyleName(String styleName) {

    }

    @Override
    public int hashCode() {
        return getLektion().hashCode();
    }

    @Override
    public boolean isAllDay() {
        return false;
        //return lektion.isLongTimeEvent();
    }

    @Override
    public void setAllDay(boolean isAllDay) {

    }

    @Override
    public boolean isMoveable() {
        return true;
        //return getLektion().isMoveable();
        //return lektion.isEditable();
    }

    @Override
    public boolean isResizeable() {
        return true;
        //return getLektion().isResizable();
        //return lektion.isEditable();
    }

    @Override
    public ItemChangeNotifier getNotifier() {
        return null;
    }

    @Override
    public String getDateCaptionFormat() {
        //return CalendarItem.RANGE_TIME;
        return VaadinIcons.CLOCK.getHtml() + " %s<br>" +
                VaadinIcons.ARROW_CIRCLE_RIGHT_O.getHtml() + " %s";
    }

}

