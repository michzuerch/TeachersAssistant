package com.gmail.michzuerch.TeachersAssistant.presentation.ui.testdaten;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.School;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.SchoolClass;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Student;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Teacher;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.SchoolClassDeltaspikeFacade;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.SchoolDeltaspikeFacade;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.StudentDeltaspikeFacade;
import com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.facade.TeacherDeltaspikeFacade;
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
    SchoolClassDeltaspikeFacade schoolClassDeltaspikeFacade;

    @Inject
    TeacherDeltaspikeFacade teacherDeltaspikeFacade;

    @Inject
    StudentDeltaspikeFacade studentDeltaspikeFacade;

    @Inject
    SchoolDeltaspikeFacade schoolDeltaspikeFacade;

    Button btnCreateSchule = new Button("Test-School erstellen");

    private void createTestschule() {
        School school = new School("Testschule", "Zürich");
        school = schoolDeltaspikeFacade.save(school);

        Teacher teacher1 = new Teacher("Anton", "Schulze");
        teacher1.setSchool(school);
        school.getTeachers().add(teacher1);
        teacher1 = teacherDeltaspikeFacade.save(teacher1);

        Teacher teacher2 = new Teacher("Hugo", "Minder");
        teacher2.setSchool(school);
        school.getTeachers().add(teacher2);
        teacher2 = teacherDeltaspikeFacade.save(teacher2);

        Teacher teacher3 = new Teacher("Susanne", "Belzing");
        teacher3.setSchool(school);
        school.getTeachers().add(teacher3);
        teacher3 = teacherDeltaspikeFacade.save(teacher3);

        Teacher teacher4 = new Teacher("Baro", "Debarre");
        teacher4.setSchool(school);
        school.getTeachers().add(teacher4);
        teacher4 = teacherDeltaspikeFacade.save(teacher4);

        Teacher teacher5 = new Teacher("Dagobert", "Huber");
        teacher5.setSchool(school);
        school.getTeachers().add(teacher5);
        teacher5 = teacherDeltaspikeFacade.save(teacher5);

        SchoolClass schoolClass1 = new SchoolClass();
        schoolClass1.setBezeichnung("Sek 1");
        schoolClass1.setSchool(school);
        school.getSchoolClasses().add(schoolClass1);
        schoolClass1 = schoolClassDeltaspikeFacade.save(schoolClass1);

        SchoolClass schoolClass2 = new SchoolClass();
        schoolClass2.setBezeichnung("Sek 2");
        schoolClass2.setSchool(school);
        school.getSchoolClasses().add(schoolClass2);
        schoolClass2 = schoolClassDeltaspikeFacade.save(schoolClass2);

        SchoolClass schoolClass3 = new SchoolClass();
        schoolClass3.setBezeichnung("Sek 3");
        schoolClass3.setSchool(school);
        school.getSchoolClasses().add(schoolClass3);
        schoolClass3 = schoolClassDeltaspikeFacade.save(schoolClass3);

        SchoolClass schoolClass4 = new SchoolClass();
        schoolClass4.setBezeichnung("Sek 4");
        schoolClass4.setSchool(school);
        school.getSchoolClasses().add(schoolClass4);
        schoolClass4 = schoolClassDeltaspikeFacade.save(schoolClass4);

        SchoolClass schoolClass5 = new SchoolClass();
        schoolClass5.setBezeichnung("Sek 5");
        schoolClass5.setSchool(school);
        school.getSchoolClasses().add(schoolClass5);
        schoolClass5 = schoolClassDeltaspikeFacade.save(schoolClass5);

        Student student1 = new Student("Karl", "Meier");
        student1.setaSchoolClass(schoolClass1);
        student1 = studentDeltaspikeFacade.save(student1);

        Student student2 = new Student("Ulrich", "Metzger");
        student2.setaSchoolClass(schoolClass1);
        student2 = studentDeltaspikeFacade.save(student2);

        Student student3 = new Student("Hans", "Demeter");
        student3.setaSchoolClass(schoolClass2);
        student3 = studentDeltaspikeFacade.save(student3);

        Student student4 = new Student("Ueli", "Knecht");
        student4.setaSchoolClass(schoolClass2);
        student4 = studentDeltaspikeFacade.save(student4);

        Student student5 = new Student("Tanja", "Trillo");
        student5.setaSchoolClass(schoolClass3);
        student5 = studentDeltaspikeFacade.save(student5);

        Student student6 = new Student("Paul", "Tanner");
        student6.setaSchoolClass(schoolClass3);
        student6 = studentDeltaspikeFacade.save(student6);

        Student student7 = new Student("Robert", "Suber");
        student7.setaSchoolClass(schoolClass4);
        student7 = studentDeltaspikeFacade.save(student7);

        Student student8 = new Student("Werner", "Brauer");
        student8.setaSchoolClass(schoolClass4);
        student8 = studentDeltaspikeFacade.save(student8);

        Student student9 = new Student("Paula", "Weiler");
        student9.setaSchoolClass(schoolClass5);
        student9 = studentDeltaspikeFacade.save(student9);

        Student student10 = new Student("Jo", "Gumibel");
        student10.setaSchoolClass(schoolClass5);
        student10 = studentDeltaspikeFacade.save(student10);

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
