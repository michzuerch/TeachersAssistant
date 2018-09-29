package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.SchoolClass;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassService {

    @Autowired
    SchoolClassRepository repository;

    public SchoolClass add(SchoolClass val) {
        return repository.save(val);
    }
}