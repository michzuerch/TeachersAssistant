package com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class SchoolGrade extends AbstractEntity {
    /**
	 *
	 */
	private static final long serialVersionUID = -6788488170825961649L;

	@Column
    private String bezeichnung;

    @ManyToOne
    private SchoolSubject schoolSubject;

    @Column
    private LocalDateTime localDateTime;

    @Column
    private float note;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
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

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
