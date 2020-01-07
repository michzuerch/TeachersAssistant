package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Student")
public class Student extends AbstractEntity {
    @NotNull
    private String firstname;

    private String lastname;

    private LocalDate birthdate;

    @ManyToOne
    private SchoolClass schoolClass;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchoolGrade> schoolGrades = new ArrayList<>();

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public List<SchoolGrade> getSchoolGrades() {
        return schoolGrades;
    }

    public void setSchoolGrades(List<SchoolGrade> schoolGrades) {
        this.schoolGrades = schoolGrades;
    }

    public Student() {
    }

    private Student(Builder builder) {
        firstname = builder.firstname;
        lastname = builder.lastname;
        birthdate = builder.birthdate;
        schoolClass = builder.schoolClass;
        schoolGrades = builder.schoolGrades;
    }

    public static final class Builder {
        private @NotNull String firstname;
        private String lastname;
        private LocalDate birthdate;
        private SchoolClass schoolClass;
        private List<SchoolGrade> schoolGrades;

        public Builder() {
        }

        public Builder firstname(@NotNull String val) {
            firstname = val;
            return this;
        }

        public Builder lastname(String val) {
            lastname = val;
            return this;
        }

        public Builder birthdate(LocalDate val) {
            birthdate = val;
            return this;
        }

        public Builder schoolClass(SchoolClass val) {
            schoolClass = val;
            return this;
        }

        public Builder schoolGrades(List<SchoolGrade> val) {
            schoolGrades = val;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}