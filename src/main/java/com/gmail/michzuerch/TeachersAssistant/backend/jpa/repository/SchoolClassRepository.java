package com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.SchoolClass;
import org.springframework.data.repository.CrudRepository;

public interface SchoolClassRepository extends CrudRepository<SchoolClass, Long> {
}
