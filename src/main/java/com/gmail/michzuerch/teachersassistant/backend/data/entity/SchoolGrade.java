package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/* Schulnote in german */
@Entity(name = "SchoolGrade")
public class SchoolGrade extends AbstractEntity {
    private String description;

    @ManyToOne
    private Student student;

    @ManyToOne
    private SchoolSubject schoolSubject;

    private LocalDateTime localDateTime;

    private BigDecimal note;

    private SchoolGrade(Builder builder) {
        setDescription(builder.description);
        setStudent(builder.student);
        setSchoolSubject(builder.schoolSubject);
        setLocalDateTime(builder.localDateTime);
        setNote(builder.note);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SchoolSubject getSchoolSubject() {
        return schoolSubject;
    }

    public void setSchoolSubject(SchoolSubject schoolSubject) {
        this.schoolSubject = schoolSubject;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal note) {
        this.note = note;
    }


    public static final class Builder {
        private String description;
        private Student student;
        private SchoolSubject schoolSubject;
        private LocalDateTime localDateTime;
        private BigDecimal note;

        public Builder() {
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder student(Student val) {
            student = val;
            return this;
        }

        public Builder schoolSubject(SchoolSubject val) {
            schoolSubject = val;
            return this;
        }

        public Builder localDateTime(LocalDateTime val) {
            localDateTime = val;
            return this;
        }

        public Builder note(BigDecimal val) {
            note = val;
            return this;
        }

        public SchoolGrade build() {
            return new SchoolGrade(this);
        }
    }
}