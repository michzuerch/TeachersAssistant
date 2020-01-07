package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "SchoolClass")
public class SchoolClass extends AbstractEntity {
    @NotNull
    private String description;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public SchoolClass() {
    }

    private SchoolClass(Builder builder) {
        description = builder.description;
        school = builder.school;
        students = builder.students;
    }


    public static final class Builder {
        private @NotNull String description;
        private School school;
        private List<Student> students;

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

        public Builder students(List<Student> val) {
            students = val;
            return this;
        }

        public SchoolClass build() {
            return new SchoolClass(this);
        }
    }
}