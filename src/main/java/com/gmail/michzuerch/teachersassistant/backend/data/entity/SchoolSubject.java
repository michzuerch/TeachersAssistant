package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/* Schulfach in german */
@Entity(name = "SchoolSubject")
public class SchoolSubject extends AbstractEntity {
    @NotNull
    private String description;

    @ManyToOne
    private School school;

    private SchoolSubject(Builder builder) {
        setDescription(builder.description);
        setSchool(builder.school);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public static final class Builder {
        private @NotNull String description;
        private School school;

        public Builder() {
        }

        public Builder description(@NotNull String val) {
            description = val;
            return this;
        }

        public Builder school(School val) {
            school = val;
            return this;
        }

        public SchoolSubject build() {
            return new SchoolSubject(this);
        }
    }
}
