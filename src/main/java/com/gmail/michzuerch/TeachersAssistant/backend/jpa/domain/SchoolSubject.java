package com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SchoolSubject extends AbstractEntity {

    @Column
    private String bezeichnung;

    @ManyToOne
    private Student student;

    @OneToMany(mappedBy = "schoolsubject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchoolGrade> schoolGrades = new ArrayList<>();

    @OneToMany(mappedBy = "schoolsubject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lession> lessions = new ArrayList<>();

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public List<SchoolGrade> getSchoolGrades() {
        return schoolGrades;
    }

    public void setSchoolGrades(List<SchoolGrade> schoolGrades) {
        this.schoolGrades = schoolGrades;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Lession> getLessions() {
        return lessions;
    }

    public void setLessions(List<Lession> lessions) {
        this.lessions = lessions;
    }
}
