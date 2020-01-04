package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "School")
public class School extends AbstractEntity {
    @NotNull
    private String description;

    @NotNull
    private String city;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Classroom> classrooms = new ArrayList<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchoolClass> schoolClasses = new ArrayList<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchoolSubject> schoolSubjects = new ArrayList<>();

    public School() {
    }

    private School(Builder builder) {
        description = builder.description;
        city = builder.city;
        teachers = builder.teachers;
        classrooms = builder.classrooms;
        schoolClasses = builder.schoolClasses;
        schoolSubjects = builder.schoolSubjects;
    }

    public static final class Builder {
        private @NotNull String description;
        private @NotNull String city;
        private List<Teacher> teachers;
        private List<Classroom> classrooms;
        private List<SchoolClass> schoolClasses;
        private List<SchoolSubject> schoolSubjects;

        public Builder() {
        }

        public Builder description(@NotNull String val) {
            description = val;
            return this;
        }

        public Builder city(@NotNull String val) {
            city = val;
            return this;
        }

        public Builder teachers(List<Teacher> val) {
            teachers = val;
            return this;
        }

        public Builder classrooms(List<Classroom> val) {
            classrooms = val;
            return this;
        }

        public Builder schoolClasses(List<SchoolClass> val) {
            schoolClasses = val;
            return this;
        }

        public Builder schoolSubjects(List<SchoolSubject> val) {
            schoolSubjects = val;
            return this;
        }

        public School build() {
            return new School(this);
        }
    }
}