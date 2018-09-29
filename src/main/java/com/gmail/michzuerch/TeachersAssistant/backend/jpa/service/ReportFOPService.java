package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.report.fop.ReportFOP;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.ReportFOPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportFOPService {

    @Autowired
    ReportFOPRepository repository;

    public ReportFOP add(ReportFOP val) {
        return repository.save(val);
    }
}