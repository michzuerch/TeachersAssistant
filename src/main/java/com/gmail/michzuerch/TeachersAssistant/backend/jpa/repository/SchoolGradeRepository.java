package com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.SchoolGrade;
import org.springframework.data.repository.CrudRepository;

public interface SchoolGradeRepository extends CrudRepository<SchoolGrade, Long> {
}
