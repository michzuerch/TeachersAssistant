package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Student;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.StudentRepository;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@VaadinSessionScope
public class StudentService {

    @Autowired
    StudentRepository repository;

    public Student add(Student val) {
        return repository.save(val);
    }
}