package com.gmail.michzuerch.teachersassistant.backend.repositories;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
