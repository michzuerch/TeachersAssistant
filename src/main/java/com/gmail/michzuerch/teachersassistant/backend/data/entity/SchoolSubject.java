package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/* Schulfach in german */
@Entity(name = "SchoolSubject")
public class SchoolSubject extends AbstractEntity {
    @NotNull
    private String description;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "schoolSubject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lession> lessions = new ArrayList<>();

    private SchoolSubject(Builder builder) {
        setDescription(builder.description);
        setSchool(builder.school);
        setLessions(builder.lessions);
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

    public List<Lession> getLessions() {
        return lessions;
    }

    public void setLessions(List<Lession> lessions) {
        this.lessions = lessions;
    }


    public static final class Builder {
        private @NotNull String description;
        private School school;
        private List<Lession> lessions;

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

        public Builder lessions(List<Lession> val) {
            lessions = val;
            return this;
        }

        public SchoolSubject build() {
            return new SchoolSubject(this);
        }
    }
}
