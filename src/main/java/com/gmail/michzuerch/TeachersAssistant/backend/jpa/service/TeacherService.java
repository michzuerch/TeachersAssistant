package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Teacher;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.TeacherRepository;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@VaadinSessionScope
public class TeacherService {

    @Autowired
    TeacherRepository repository;

    public Teacher add(Teacher val) {
        return repository.save(val);
    }
}