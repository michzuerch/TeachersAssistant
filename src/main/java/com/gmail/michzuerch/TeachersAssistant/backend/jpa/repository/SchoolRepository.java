package com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.School;
import org.springframework.data.repository.CrudRepository;

public interface SchoolRepository extends CrudRepository<School, Long> {
}
