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

    private String firstname;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lession> lessions = new ArrayList<>();

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public Teacher() {
    }

    private Teacher(Builder builder) {
        lastname = builder.lastname;
        firstname = builder.firstname;
        school = builder.school;
        lessions = builder.lessions;
    }

    public static final class Builder {
        private @NotNull String lastname;
        private String firstname;
        private School school;
        private List<Lession> lessions;

        public Builder() {
        }

        public Builder lastname(@NotNull String val) {
            lastname = val;
            return this;
        }

        public Builder firstname(String val) {
            firstname = val;
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