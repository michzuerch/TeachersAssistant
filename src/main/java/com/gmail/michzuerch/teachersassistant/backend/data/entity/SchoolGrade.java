package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/* Schulnote in german */
@Entity(name = "SchoolGrade")
public class SchoolGrade extends AbstractEntity {
    @NotNull
    private String description;

    @ManyToOne
    private Student student;

    @ManyToOne
    private SchoolSubject schoolSubject;

    private LocalDateTime timestamp;

    private BigDecimal note;

    public SchoolGrade() {
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal note) {
        this.note = note;
    }


    private SchoolGrade(Builder builder) {
        description = builder.description;
        student = builder.student;
        schoolSubject = builder.schoolSubject;
        timestamp = builder.timestamp;
        note = builder.note;
    }

    public static final class Builder {
        private @NotNull String description;
        private Student student;
        private SchoolSubject schoolSubject;
        private LocalDateTime timestamp;
        private BigDecimal note;

        public Builder() {
        }

        public Builder description(@NotNull String val) {
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

        public Builder timestamp(LocalDateTime val) {
            timestamp = val;
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