package com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SchoolRepository extends JpaRepository<School, Long> {
    Collection<School> findByBeichnungIgnoreCase(String value);
}
