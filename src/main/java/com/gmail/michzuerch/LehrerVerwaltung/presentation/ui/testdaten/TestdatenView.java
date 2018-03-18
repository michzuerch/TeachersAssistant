package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.testdaten;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Klasse;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Lehrer;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schueler;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Schule;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.KlasseDeltaspikeFacade;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.LehrerDeltaspikeFacade;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.SchuelerDeltaspikeFacade;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade.SchuleDeltaspikeFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

import javax.inject.Inject;

@CDIView("TestdatenView")
public class TestdatenView extends HorizontalLayout implements View {
    @Inject
    KlasseDeltaspikeFacade klasseDeltaspikeFacade;

    @Inject
    LehrerDeltaspikeFacade lehrerDeltaspikeFacade;

    @Inject
    SchuelerDeltaspikeFacade schuelerDeltaspikeFacade;

    @Inject
    SchuleDeltaspikeFacade schuleDeltaspikeFacade;

    Button btnCreateSchule = new Button("Test-Schule erstellen");

    private void createTestschule() {
        Schule schule = new Schule("Testschule", "Zürich");
        schule = schuleDeltaspikeFacade.save(schule);

        Lehrer lehrer1 = new Lehrer("Anton", "Schulze");
        lehrer1.setSchule(schule);
        schule.getLehrers().add(lehrer1);
        lehrer1 = lehrerDeltaspikeFacade.save(lehrer1);

        Lehrer lehrer2 = new Lehrer("Hugo", "Minder");
        lehrer2.setSchule(schule);
        schule.getLehrers().add(lehrer2);
        lehrer2 = lehrerDeltaspikeFacade.save(lehrer2);

        Lehrer lehrer3 = new Lehrer("Susanne", "Belzing");
        lehrer3.setSchule(schule);
        schule.getLehrers().add(lehrer3);
        lehrer3 = lehrerDeltaspikeFacade.save(lehrer3);

        Lehrer lehrer4 = new Lehrer("Baro", "Debarre");
        lehrer4.setSchule(schule);
        schule.getLehrers().add(lehrer4);
        lehrer4 = lehrerDeltaspikeFacade.save(lehrer4);

        Lehrer lehrer5 = new Lehrer("Dagobert", "Huber");
        lehrer5.setSchule(schule);
        schule.getLehrers().add(lehrer5);
        lehrer5 = lehrerDeltaspikeFacade.save(lehrer5);

        Klasse klasse1 = new Klasse();
        klasse1.setBezeichnung("Sek 1");
        klasse1.setSchule(schule);
        schule.getKlasses().add(klasse1);
        klasse1 = klasseDeltaspikeFacade.save(klasse1);

        Klasse klasse2 = new Klasse();
        klasse2.setBezeichnung("Sek 2");
        klasse2.setSchule(schule);
        schule.getKlasses().add(klasse2);
        klasse2 = klasseDeltaspikeFacade.save(klasse2);

        Klasse klasse3 = new Klasse();
        klasse3.setBezeichnung("Sek 3");
        klasse3.setSchule(schule);
        schule.getKlasses().add(klasse3);
        klasse3 = klasseDeltaspikeFacade.save(klasse3);

        Klasse klasse4 = new Klasse();
        klasse4.setBezeichnung("Sek 4");
        klasse4.setSchule(schule);
        schule.getKlasses().add(klasse4);
        klasse4 = klasseDeltaspikeFacade.save(klasse4);

        Klasse klasse5 = new Klasse();
        klasse5.setBezeichnung("Sek 5");
        klasse5.setSchule(schule);
        schule.getKlasses().add(klasse5);
        klasse5 = klasseDeltaspikeFacade.save(klasse5);

        Schueler schueler1 = new Schueler("Karl", "Meier");
        schueler1.setKlasse(klasse1);
        schueler1 = schuelerDeltaspikeFacade.save(schueler1);

        Schueler schueler2 = new Schueler("Ulrich", "Metzger");
        schueler2.setKlasse(klasse1);
        schueler2 = schuelerDeltaspikeFacade.save(schueler2);

        Schueler schueler3 = new Schueler("Hans", "Demeter");
        schueler3.setKlasse(klasse2);
        schueler3 = schuelerDeltaspikeFacade.save(schueler3);

        Schueler schueler4 = new Schueler("Ueli", "Knecht");
        schueler4.setKlasse(klasse2);
        schueler4 = schuelerDeltaspikeFacade.save(schueler4);

        Schueler schueler5 = new Schueler("Tanja", "Trillo");
        schueler5.setKlasse(klasse3);
        schueler5 = schuelerDeltaspikeFacade.save(schueler5);

        Schueler schueler6 = new Schueler("Paul", "Tanner");
        schueler6.setKlasse(klasse3);
        schueler6 = schuelerDeltaspikeFacade.save(schueler6);

        Schueler schueler7 = new Schueler("Robert", "Suber");
        schueler7.setKlasse(klasse4);
        schueler7 = schuelerDeltaspikeFacade.save(schueler7);

        Schueler schueler8 = new Schueler("Werner", "Brauer");
        schueler8.setKlasse(klasse4);
        schueler8 = schuelerDeltaspikeFacade.save(schueler8);

        Schueler schueler9 = new Schueler("Paula", "Weiler");
        schueler9.setKlasse(klasse5);
        schueler9 = schuelerDeltaspikeFacade.save(schueler9);

        Schueler schueler10 = new Schueler("Jo", "Gumibel");
        schueler10.setKlasse(klasse5);
        schueler10 = schuelerDeltaspikeFacade.save(schueler10);

        Notification.show("Testdaten", "Testdaten erstellt.", Notification.TYPE_TRAY_NOTIFICATION);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        btnCreateSchule.addClickListener(event1 -> {
            createTestschule();
        });
        addComponent(btnCreateSchule);
    }
}
