package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Classroom extends AbstractEntity {
    private String bezeichnung;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lession> lessions = new ArrayList<>();

    private Classroom(Builder builder) {
        setBezeichnung(builder.bezeichnung);
        setSchool(builder.school);
        setLessions(builder.lessions);
    }

    public Classroom() {
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

    public List<Lession> getLessions() {
        return lessions;
    }

    public void setLessions(List<Lession> lessions) {
        this.lessions = lessions;
    }

    public static final class Builder {
        private String bezeichnung;
        private School school;
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

        public Builder lessions(List<Lession> val) {
            lessions = val;
            return this;
        }

        public Classroom build() {
            return new Classroom(this);
        }
    }
}