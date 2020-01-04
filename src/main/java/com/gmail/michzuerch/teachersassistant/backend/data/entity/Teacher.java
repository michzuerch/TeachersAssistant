package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Teacher")
public class Teacher extends AbstractEntity {
    @NotNull
    private String lastname;

    private String vorname;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lession> lessions = new ArrayList<>();

    public Teacher() {
    }

    private Teacher(Builder builder) {
        lastname = builder.lastname;
        vorname = builder.vorname;
        school = builder.school;
        lessions = builder.lessions;
    }


    public static final class Builder {
        private @NotNull String lastname;
        private String vorname;
        private School school;
        private List<Lession> lessions;

        public Builder() {
        }

        public Builder lastname(@NotNull String val) {
            lastname = val;
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