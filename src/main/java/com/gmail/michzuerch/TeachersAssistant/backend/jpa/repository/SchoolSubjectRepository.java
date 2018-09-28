package com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.SchoolSubject;
import org.springframework.data.repository.CrudRepository;

public interface SchoolSubjectRepository extends CrudRepository<SchoolSubject, Long> {
}
