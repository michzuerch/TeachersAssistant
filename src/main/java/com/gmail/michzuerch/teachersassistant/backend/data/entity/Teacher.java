package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher extends AbstractEntity {
    private String nachname;
    private String vorname;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lession> lessions = new ArrayList<>();

    private Teacher(Builder builder) {
        setNachname(builder.nachname);
        setVorname(builder.vorname);
        setSchool(builder.school);
        setLessions(builder.lessions);
    }

    public Teacher() {
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Lession> getLessions() {
        return lessions;
    }

    public void setLessions(List<Lession> lessions) {
        this.lessions = lessions;
    }

    public static final class Builder {
        private String nachname;
        private String vorname;
        private School school;
        private List<Lession> lessions;

        public Builder() {
        }

        public Builder nachname(String val) {
            nachname = val;
            return this;
        }

        public Builder vorname(String val) {
            vorname = val;
            return this;
        }

        public Builder school(School val) {
            school = val;
            return this;
        }

        public Builder lessions(List<Lession> val) {
            lessions = val;
            return this;
        }

        public Teacher build() {
            return new Teacher(this);
        }
    }


}