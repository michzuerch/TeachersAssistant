package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.Lession;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.LessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessionService {

    @Autowired
    LessionRepository repository;

    public Lession add(Lession val) {
        return repository.save(val);
    }
}