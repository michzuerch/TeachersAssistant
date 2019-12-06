package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class SchoolGrade extends AbstractEntity {
    private String bezeichnung;

    @ManyToOne
    private School school;

    private LocalDateTime localDateTime;

    private float note;

    private SchoolGrade(Builder builder) {
        setBezeichnung(builder.bezeichnung);
        setSchool(builder.school);
        setLocalDateTime(builder.localDateTime);
        setNote(builder.note);
    }

    public SchoolGrade() {
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public static final class Builder {
        private String bezeichnung;
        private School school;
        private LocalDateTime localDateTime;
        private float note;

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

        public Builder localDateTime(LocalDateTime val) {
            localDateTime = val;
            return this;
        }

        public Builder note(float val) {
            note = val;
            return this;
        }

        public SchoolGrade build() {
            return new SchoolGrade(this);
        }
    }
}