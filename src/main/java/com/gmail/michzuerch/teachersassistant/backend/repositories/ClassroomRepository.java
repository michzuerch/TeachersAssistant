package com.gmail.michzuerch.teachersassistant.backend.repositories;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
