package com.gmail.michzuerch.teachersassistant.backend.repositories;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
