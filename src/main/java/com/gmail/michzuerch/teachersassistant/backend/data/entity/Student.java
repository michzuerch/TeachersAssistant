package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Student extends AbstractEntity {

    private String vorname;

    private String nachname;

    @ManyToOne
    private SchoolClass schoolClass;

    @ManyToOne
    private School school;

    private Student(Builder builder) {
        setVorname(builder.vorname);
        setNachname(builder.nachname);
        setSchoolClass(builder.schoolClass);
        setSchool(builder.school);
    }

    public Student() {
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

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public static final class Builder {
        private String vorname;
        private String nachname;
        private SchoolClass schoolClass;
        private School school;

        public Builder() {
        }

        public Builder vorname(String val) {
            vorname = val;
            return this;
        }

        public Builder nachname(String val) {
            nachname = val;
            return this;
        }

        public Builder schoolClass(SchoolClass val) {
            schoolClass = val;
            return this;
        }

        public Builder school(School val) {
            school = val;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}