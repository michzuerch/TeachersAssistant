package com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Lession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessionRepository extends JpaRepository<Lession, Long> {
}
