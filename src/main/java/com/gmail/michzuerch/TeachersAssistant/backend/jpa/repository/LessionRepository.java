package com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Lession;
import org.springframework.data.repository.CrudRepository;

public interface LessionRepository extends CrudRepository<Lession, Long> {
}
