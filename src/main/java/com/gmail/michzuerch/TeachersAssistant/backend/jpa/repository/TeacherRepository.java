package com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}
