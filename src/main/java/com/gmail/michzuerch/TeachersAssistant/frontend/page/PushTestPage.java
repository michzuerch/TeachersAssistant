package com.gmail.michzuerch.TeachersAssistant.frontend.page;

import com.gmail.michzuerch.TeachersAssistant.frontend.MainLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Route(value = "PushTest", layout = MainLayout.class)
public class PushTestPage extends VerticalLayout {
    /**
	 *
	 */
	private static final long serialVersionUID = 7673048339108834238L;
    private FeederThread thread;
    private Label timeLbl = new Label("Uhr");

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        add(timeLbl);
        // Start the data feed thread
        thread = new FeederThread(attachEvent.getUI(), this);
        thread.start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        // Cleanup
        thread.interrupt();
        thread = null;
    }

    private static class FeederThread extends Thread {
        private final UI ui;
        private final PushTestPage view;

        private int count = 0;

        public FeederThread(UI ui, PushTestPage view) {
            this.ui = ui;
            this.view = view;
        }

        @Override
        public void run() {
            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            try {
                // Update the data for a while
                while (count < 60) {
                    // Sleep to emulate background work
                    Thread.sleep(500);
                    //String message = "This is update " + count++;
                    //log.debug(LocalTime.now().format(dateTimeFormatter));
                    ui.access(() -> view.timeLbl.setText(LocalTime.now().format(dateTimeFormatter)));
                }

                // Inform that we are done
                ui.access(() -> {
                    view.timeLbl.setText("Done updating");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}