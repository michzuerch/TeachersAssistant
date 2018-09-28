package com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends AbstractEntity {
    @Column
    private String vorname;

    @Column
    private String nachname;

    @ManyToOne
    private SchoolClass aSchoolClass;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchoolSubject> schoolSubjects = new ArrayList<>();

    public Student() {
    }

    public Student(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public SchoolClass getaSchoolClass() {
        return aSchoolClass;
    }

    public void setaSchoolClass(SchoolClass aSchoolClass) {
        this.aSchoolClass = aSchoolClass;
    }

    public List<SchoolSubject> getSchoolSubjects() {
        return schoolSubjects;
    }

    public void setSchoolSubjects(List<SchoolSubject> schoolSubjects) {
        this.schoolSubjects = schoolSubjects;
    }
}
