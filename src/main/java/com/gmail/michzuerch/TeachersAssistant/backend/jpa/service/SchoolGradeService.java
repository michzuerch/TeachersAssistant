package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.SchoolGrade;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.SchoolGradeRepository;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@VaadinSessionScope
public class SchoolGradeService {

    @Autowired
    SchoolGradeRepository repository;

    public SchoolGrade add(SchoolGrade val) {
        return repository.save(val);
    }
}