package com.gmail.michzuerch.teachersassistant.backend.repositories;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.SchoolGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolGradeRepository extends JpaRepository<SchoolGrade, Long> {
}
