package com.gmail.michzuerch.teachersassistant.backend.repositories;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
}
