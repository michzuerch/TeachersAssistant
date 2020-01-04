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
    private String description;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime ende;

    // Schulfach in german
    @ManyToOne
    private SchoolSubject schoolSubject;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Classroom classroom;

    public Lession() {
    }

    private Lession(Builder builder) {
        description = builder.description;
        start = builder.start;
        ende = builder.ende;
        schoolSubject = builder.schoolSubject;
        teacher = builder.teacher;
        classroom = builder.classroom;
    }


    public static final class Builder {
        private @NotNull @Size(min = 3, message = "Mindestlänge 3 Zeichen") String description;
        private @NotNull LocalDateTime start;
        private @NotNull LocalDateTime ende;
        private SchoolSubject schoolSubject;
        private Teacher teacher;
        private Classroom classroom;

        public Builder() {
        }

        public Builder description(@NotNull @Size(min = 3, message = "Mindestlänge 3 Zeichen") String val) {
            description = val;
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

        public Builder schoolSubject(SchoolSubject val) {
            schoolSubject = val;
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

        public Lession build() {
            return new Lession(this);
        }
    }
}