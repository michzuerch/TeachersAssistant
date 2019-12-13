package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "School")
public class School extends AbstractEntity {
    private String bezeichnung;

    private String ort;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchoolSubject> schoolSubjects = new ArrayList<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchoolClass> schoolClasses = new ArrayList<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Classroom> classrooms = new ArrayList<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Teacher> teachers = new ArrayList<>();

    private School(Builder builder) {
        setBezeichnung(builder.bezeichnung);
        setOrt(builder.ort);
        setSchoolClasses(builder.schoolClasses);
        setClassrooms(builder.classrooms);
        setTeachers(builder.teachers);
    }

    public School() {
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public List<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }

    public void setSchoolClasses(List<SchoolClass> schoolClasses) {
        this.schoolClasses = schoolClasses;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public static final class Builder {
        private String bezeichnung;
        private String ort;
        private List<SchoolClass> schoolClasses;
        private List<Classroom> classrooms;
        private List<Teacher> teachers;

        public Builder() {
        }

        public Builder bezeichnung(String val) {
            bezeichnung = val;
            return this;
        }

        public Builder ort(String val) {
            ort = val;
            return this;
        }

        public Builder schoolClasses(List<SchoolClass> val) {
            schoolClasses = val;
            return this;
        }

        public Builder classrooms(List<Classroom> val) {
            classrooms = val;
            return this;
        }

        public Builder teachers(List<Teacher> val) {
            teachers = val;
            return this;
        }

        public School build() {
            return new School(this);
        }
    }
}