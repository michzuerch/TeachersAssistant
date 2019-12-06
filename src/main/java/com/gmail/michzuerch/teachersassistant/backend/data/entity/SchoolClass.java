package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SchoolClass extends AbstractEntity {
    private String bezeichnung;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lession> lessions = new ArrayList<>();

    private SchoolClass(Builder builder) {
        setBezeichnung(builder.bezeichnung);
        setSchool(builder.school);
        setStudents(builder.students);
        setLessions(builder.lessions);
    }

    public SchoolClass() {
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Lession> getLessions() {
        return lessions;
    }

    public void setLessions(List<Lession> lessions) {
        this.lessions = lessions;
    }

    public static final class Builder {
        private String bezeichnung;
        private School school;
        private List<Student> students;
        private List<Lession> lessions;

        public Builder() {
        }

        public Builder bezeichnung(String val) {
            bezeichnung = val;
            return this;
        }

        public Builder school(School val) {
            school = val;
            return this;
        }

        public Builder students(List<Student> val) {
            students = val;
            return this;
        }

        public Builder lessions(List<Lession> val) {
            lessions = val;
            return this;
        }

        public SchoolClass build() {
            return new SchoolClass(this);
        }
    }
}