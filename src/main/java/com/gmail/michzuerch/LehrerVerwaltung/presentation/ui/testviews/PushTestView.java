package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.testviews;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.LoggerFactory;
import org.vaadin.teemusa.flexlayout.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ForkJoinPool;

@CDIView("PushTestView")
public class PushTestView extends VerticalLayout implements View {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(PushTestView.class.getName());

    private Component createContent() {
        FlexLayout layout = new FlexLayout();

        layout.setFlexDirection(FlexDirection.Row);
        layout.setAlignItems(AlignItems.Center);
        layout.setJustifyContent(JustifyContent.SpaceBetween);
        layout.setAlignContent(AlignContent.Center);
        layout.setFlexWrap(FlexWrap.Wrap);

        final Label labelTime = new Label("???");

        labelTime.addStyleName("h1");
        // now in a background thread we constantly update the time
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        ForkJoinPool.commonPool().submit(() -> {
            boolean keepGoing = true;
            while (keepGoing) {
                getUI().access(() -> labelTime.setValue(LocalTime.now().format(dateTimeFormatter)));
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    keepGoing = false;
                }
            }
        });
        layout.addComponents(labelTime);
        layout.setSizeFull();
        return layout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponent(createContent());
        setSizeFull();
    }
}
