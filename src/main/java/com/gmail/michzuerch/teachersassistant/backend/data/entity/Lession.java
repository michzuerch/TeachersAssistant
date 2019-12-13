package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity(name = "Lession")
public class Lession extends AbstractEntity {
    @NotNull
    @Size(min = 3, message = "Mindestlänge 3 Zeichen")
    private String bezeichnung;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime ende;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Classroom classroom;

    @ManyToOne
    private SchoolClass schoolClass;

    private Lession(Builder builder) {
        setBezeichnung(builder.bezeichnung);
        setStart(builder.start);
        setEnde(builder.ende);
        setTeacher(builder.teacher);
        setClassroom(builder.classroom);
        setSchoolClass(builder.schoolClass);
    }

    public Lession() {
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnde() {
        return ende;
    }

    public void setEnde(LocalDateTime ende) {
        this.ende = ende;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public static final class Builder {
        private @NotNull @Size(min = 3, message = "Mindestlänge 3 Zeichen") String bezeichnung;
        private @NotNull LocalDateTime start;
        private @NotNull LocalDateTime ende;
        private Teacher teacher;
        private Classroom classroom;
        private SchoolClass schoolClass;

        public Builder() {
        }

        public Builder bezeichnung(@NotNull @Size(min = 3, message = "Mindestlänge 3 Zeichen") String val) {
            bezeichnung = val;
            return this;
        }

        public Builder start(@NotNull LocalDateTime val) {
            start = val;
            return this;
        }

        public Builder ende(@NotNull LocalDateTime val) {
            ende = val;
            return this;
        }

        public Builder teacher(Teacher val) {
            teacher = val;
            return this;
        }

        public Builder classroom(Classroom val) {
            classroom = val;
            return this;
        }

        public Builder schoolClass(SchoolClass val) {
            schoolClass = val;
            return this;
        }

        public Lession build() {
            return new Lession(this);
        }
    }
}