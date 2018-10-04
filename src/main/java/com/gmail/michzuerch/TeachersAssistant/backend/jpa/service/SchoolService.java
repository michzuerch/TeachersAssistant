package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.School;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.SchoolRepository;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@VaadinSessionScope
public class SchoolService {

    @Autowired
    SchoolRepository repository;

    public School add(School val) {
        return repository.save(val);
    }

    public List<School> findAll() {
        return repository.findAll();
    }

    public Collection<School> findByBezeichnungIgnoreCase(String value) {
        return repository.findByBezeichnungIgnoreCase(value);
    }

    public void delete(School school) {
        repository.delete(school);
    }

    public School save(School school) {
        return repository.save(school);
    }
}