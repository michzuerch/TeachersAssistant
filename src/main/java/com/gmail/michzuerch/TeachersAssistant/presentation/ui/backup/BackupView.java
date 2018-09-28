package com.gmail.michzuerch.TeachersAssistant.presentation.ui.backup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.School;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.SchoolClass;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Student;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Teacher;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.SchoolClassDeltaspikeFacade;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.SchoolDeltaspikeFacade;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.StudentDeltaspikeFacade;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.TeacherDeltaspikeFacade;
import com.gmail.michzuerch.TeachersAssistant.presentation.ui.backup.dto.*;
import com.vaadin.cdi.CDIView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemusa.flexlayout.*;
import org.vaadin.viritin.button.DownloadButton;
import server.droporchoose.UploadComponent;

import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by michzuerch on 25.07.15.
 */
@CDIView("BackupView")
public class BackupView extends VerticalLayout implements View {
    private static Logger logger = LoggerFactory.getLogger(BackupView.class.getName());
    @Inject
    SchoolDeltaspikeFacade schoolDeltaspikeFacade;

    @Inject
    TeacherDeltaspikeFacade teacherDeltaspikeFacade;

    @Inject
    SchoolClassDeltaspikeFacade schoolClassDeltaspikeFacade;

    @Inject
    StudentDeltaspikeFacade studentDeltaspikeFacade;

    private BackupSchulen getBackupSchulen() {
        BackupSchulen backupSchulen = new BackupSchulen();
        Instant now = Instant.now();
        backupSchulen.setBackupdatum(LocalDateTime.ofInstant(now, ZoneOffset.UTC));

        schoolDeltaspikeFacade.findAll().stream().forEach(schule -> {
            BackupSchule backupSchule = new BackupSchule(schule.getBezeichnung(), schule.getOrt());
            backupSchulen.getBackupSchules().add(backupSchule);
            schule.getTeachers().stream().forEach(lehrer -> {
                BackupLehrer backupLehrer = new BackupLehrer(lehrer.getVorname(), lehrer.getNachname());
                backupSchule.getBackupLehrers().add(backupLehrer);
            });

            schule.getSchoolClasses().stream().forEach(klasse -> {
                BackupKlasse backupKlasse = new BackupKlasse(klasse.getBezeichnung());
                backupSchule.getBackupKlasses().add(backupKlasse);
                klasse.getStudents().stream().forEach(schueler -> {
                    BackupSchueler backupSchueler = new BackupSchueler(schueler.getVorname(), schueler.getNachname());
                    backupKlasse.getBackupSchuelers().add(backupSchueler);
                });
            });
            backupSchulen.getBackupSchules().add(backupSchule);
        });
        return backupSchulen;
    }

    private Component createContent() {
        FlexLayout layout = new FlexLayout();

        layout.setFlexDirection(FlexDirection.Row);
        layout.setAlignItems(AlignItems.FlexStart);
        layout.setJustifyContent(JustifyContent.Center);
        layout.setAlignContent(AlignContent.Center);
        layout.setFlexWrap(FlexWrap.Nowrap);

        Button downloaderSchulen = new DownloadButton(stream -> {
            ObjectMapper mapper = new ObjectMapper()
                    .registerModule(new ParameterNamesModule())
                    .registerModule(new Jdk8Module())
                    .registerModule(new JavaTimeModule()); // new module, NOT JSR310Module
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(stream, getBackupSchulen());
                stream.flush();
                stream.close();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).withCaption("Schulen").withIcon(VaadinIcons.DOWNLOAD);

        ((DownloadButton) downloaderSchulen).setFileName("Schulen.json");

        //Setze Dateiname für Downloadbuttons

        Panel panelBackup = new Panel("Backup");
        Panel panelBackupSchulen = new Panel("Schulen");
        panelBackupSchulen.setContent(new HorizontalLayout(
                downloaderSchulen));

        panelBackup.setContent(
                new VerticalLayout(panelBackupSchulen));

        Panel panelRestore = new Panel("Restore");

        UploadComponent uploadSchulen = new UploadComponent();
        uploadSchulen.setCaption("Schulen");
        uploadSchulen.setReceivedCallback(this::uploadReceivedSchulen);

        panelRestore.setContent(new VerticalLayout(uploadSchulen));
        layout.addComponents(panelBackup, panelRestore);
        layout.setSizeFull();
        return layout;
    }

    private void uploadReceivedSchulen(String s, Path path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            BackupSchulen backupSchulen = mapper.readValue(new FileInputStream(path.toFile()), BackupSchulen.class);
            for (BackupSchule backupSchule : backupSchulen.getBackupSchules()) {
                School school = new School();
                school.setBezeichnung(backupSchule.getBezeichnung());
                school.setOrt(backupSchule.getOrt());
                school = schoolDeltaspikeFacade.save(school);
                for (BackupLehrer backupLehrer : backupSchule.getBackupLehrers()) {
                    Teacher teacher = new Teacher();
                    teacher.setVorname(backupLehrer.getVorname());
                    teacher.setNachname(backupLehrer.getNachname());
                    teacher.setSchool(school);
                    teacher = teacherDeltaspikeFacade.save(teacher);
                    school.getTeachers().add(teacher);
                }
                for (BackupKlasse backupKlasse : backupSchule.getBackupKlasses()) {
                    SchoolClass aSchoolClass = new SchoolClass();
                    aSchoolClass.setBezeichnung(backupKlasse.getBezeichnung());
                    aSchoolClass.setSchool(school);
                    aSchoolClass = schoolClassDeltaspikeFacade.save(aSchoolClass);
                    school.getSchoolClasses().add(aSchoolClass);

                    for (BackupSchueler backupSchueler : backupKlasse.getBackupSchuelers()) {
                        Student student = new Student();
                        student.setVorname(backupSchueler.getVorname());
                        student.setNachname(backupSchueler.getNachname());
                        student.setaSchoolClass(aSchoolClass);
                        student = studentDeltaspikeFacade.save(student);
                    }
                }
                school = schoolDeltaspikeFacade.save(school);
                Notification.show("School erstellt: " + school.getBezeichnung(),
                        Notification.Type.TRAY_NOTIFICATION);
            }


        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        addComponent(createContent());
        setSizeFull();
    }
}

