package com.gmail.michzuerch.TeachersAssistant.presentation.ui.stundenplan;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Lession;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.LessionDeltaspikeFacade;
import com.vaadin.cdi.ViewScoped;
import com.vaadin.icons.VaadinIcons;
import org.vaadin.addon.calendar.item.EditableCalendarItem;

import javax.inject.Inject;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ViewScoped
public class LessionItem implements EditableCalendarItem {

    @Inject
    LessionDeltaspikeFacade lessionDeltaspikeFacade;

    private Lession lession;

    public LessionItem() {
        lession = lessionDeltaspikeFacade.findAll().get(0);
    }

    public LessionItem(Lession val) {
        lession = val;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LessionItem)) {
            return false;
        }
        LessionItem that = (LessionItem) o;
        return getLession().equals(that.getLession());
    }


    public Lession getLession() {
        return lession;
    }

    @Override
    public ZonedDateTime getStart() {
        return null;
    }

    @Override
    public void setStart(ZonedDateTime start) {
        getLession().setStart(start.toLocalDateTime());
    }

    @Override
    public ZonedDateTime getEnd() {
        return getLession().getEnde().atZone(ZoneId.systemDefault());
    }

    @Override
    public void setEnd(ZonedDateTime end) {
        getLession().setEnde(end.toLocalDateTime());
    }

    @Override
    public String getCaption() {
        return null;
    }

    @Override
    public void setCaption(String caption) {
        getLession().getBezeichnung();
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
        return "state-" + getLession().getBezeichnung().toLowerCase();
    }


//    @Override
//    public boolean isClickable() {
//        return lession.isEditable();
//    }

    @Override
    public void setStyleName(String styleName) {

    }

    @Override
    public int hashCode() {
        return getLession().hashCode();
    }

    @Override
    public boolean isAllDay() {
        return false;
        //return lession.isLongTimeEvent();
    }

    @Override
    public void setAllDay(boolean isAllDay) {

    }

    @Override
    public boolean isMoveable() {
        return true;
        //return getLession().isMoveable();
        //return lession.isEditable();
    }

    @Override
    public boolean isResizeable() {
        return true;
        //return getLession().isResizable();
        //return lession.isEditable();
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

