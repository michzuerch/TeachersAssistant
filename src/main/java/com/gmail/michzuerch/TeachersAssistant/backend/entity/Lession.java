package com.gmail.michzuerch.TeachersAssistant.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Lession extends AbstractEntity {
    @Column
    @NotNull
    @Size(min = 3, message = "Mindestlänge 3 Zeichen")
    private String bezeichnung;

    @Column
    @NotNull
    private LocalDateTime start;

    @Column
    @NotNull
    private LocalDateTime ende;

    @ManyToOne
    private SchoolSubject schoolSubject;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Classroom classroom;

    @ManyToOne
    private SchoolClass aSchoolClass;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnde() {
        return ende;
    }

    public void setEnde(LocalDateTime ende) {
        this.ende = ende;
    }

    public SchoolSubject getSchoolSubject() {
        return schoolSubject;
    }

    public void setSchoolSubject(SchoolSubject schoolSubject) {
        this.schoolSubject = schoolSubject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public SchoolClass getaSchoolClass() {
        return aSchoolClass;
    }

    public void setaSchoolClass(SchoolClass aSchoolClass) {
        this.aSchoolClass = aSchoolClass;
    }
}
