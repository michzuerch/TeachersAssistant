package com.gmail.michzuerch.TeachersAssistant.presentation.ui.stundenplan;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.Lession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Notification.Type;
import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.handler.BasicDateClickHandler;
import org.vaadin.addon.calendar.ui.CalendarComponentEvents;

import java.time.Month;
import java.util.GregorianCalendar;
import java.util.Random;


public class StundenplanCalendar extends CustomComponent {

    private final Random R = new Random(0);
    public Panel panel;
    private StundenplanDataProvider eventProvider;
    private Calendar<LessionItem> calendar;

    public StundenplanCalendar() {

        setId("meeting-stundenplanCalendar");
        setSizeFull();

        initCalendar();

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.setSizeFull();

        panel = new Panel(calendar);
        panel.setHeight(100, Unit.PERCENTAGE);
        layout.addComponent(panel);

        setCompositionRoot(layout);

    }

    public void switchToMonth(Month month) {
        calendar.withMonth(month);
    }

    public Calendar<LessionItem> getCalendar() {
        return calendar;
    }

    private void onCalendarRangeSelect(CalendarComponentEvents.RangeSelectEvent event) {

        Lession lession = new Lession();

        lession.setStart(event.getStart().toLocalDateTime());
        lession.setEnde(event.getEnd().toLocalDateTime());

        lession.setBezeichnung("A Detail<br>with HTML<br> with more lines");

        // Random state
        //aufwand.setState(R.nextInt(2) == 1 ? Meeting.State.planned : Meeting.State.confirmed);

        eventProvider.addItem(new LessionItem(lession));
    }

    private void onCalendarClick(CalendarComponentEvents.ItemClickEvent event) {

        LessionItem lessionItem = (LessionItem) event.getCalendarItem();

        final Lession lession = lessionItem.getLession();

        Notification.show(lession.getBezeichnung(), Type.HUMANIZED_MESSAGE);
    }

    private void initCalendar() {

        eventProvider = new StundenplanDataProvider();

        calendar = new Calendar<>(eventProvider);

        calendar.addStyleName("stundenplanCalendar");
        calendar.setWidth(100.0f, Unit.PERCENTAGE);
        calendar.setHeight(100.0f, Unit.PERCENTAGE);
        calendar.setResponsive(true);

        calendar.setItemCaptionAsHtml(true);
        calendar.setContentMode(ContentMode.HTML);

//        calendar.setLocale(Locale.JAPAN);
//        calendar.setZoneId(ZoneId.of("America/Chicago"));
//        calendar.setWeeklyCaptionProvider(date ->  "<br>" + DateTimeFormatter.ofPattern("dd.MM.YYYY", getLocale()).format(date));
//        calendar.setWeeklyCaptionProvider(date -> DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(getLocale()).format(date));
//
        calendar.withVisibleDays(1, 7);
//        calendar.withMonth(ZonedDateTime.now().getMonth());

//        calendar.setStartDate(ZonedDateTime.of(2017, 9, 10, 0, 0, 0, 0, calendar.getZoneId()));
//        calendar.setEndDate(ZonedDateTime.of(2017, 9, 16, 0, 0, 0, 0, calendar.getZoneId()));


        addCalendarEventListeners();

        setupBlockedTimeSlots();
    }

    private void setupBlockedTimeSlots() {

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(java.util.Calendar.MINUTE);
        cal.clear(java.util.Calendar.SECOND);
        cal.clear(java.util.Calendar.MILLISECOND);

        GregorianCalendar bcal = new GregorianCalendar(UI.getCurrent().getLocale());
        bcal.clear();

        long start = bcal.getTimeInMillis();

        bcal.add(java.util.Calendar.HOUR, 7);
        bcal.add(java.util.Calendar.MINUTE, 30);
        long end = bcal.getTimeInMillis();

        calendar.addTimeBlock(start, end, "my-blocky-style");

        cal.add(java.util.Calendar.DAY_OF_WEEK, 1);

        bcal.clear();
        bcal.add(java.util.Calendar.HOUR, 14);
        bcal.add(java.util.Calendar.MINUTE, 30);
        start = bcal.getTimeInMillis();

        bcal.add(java.util.Calendar.MINUTE, 60);
        end = bcal.getTimeInMillis();

        calendar.addTimeBlock(start, end);

    }

    private void addCalendarEventListeners() {
        calendar.setHandler(new BasicDateClickHandler(true));
        calendar.setHandler(this::onCalendarClick);
        calendar.setHandler(this::onCalendarRangeSelect);
    }

}


