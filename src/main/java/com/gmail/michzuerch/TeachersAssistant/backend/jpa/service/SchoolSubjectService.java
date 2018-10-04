package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.SchoolSubject;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.SchoolSubjectRepository;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@VaadinSessionScope
public class SchoolSubjectService {

    @Autowired
    SchoolSubjectRepository repository;

    public SchoolSubject add(SchoolSubject val) {
        return repository.save(val);
    }
}