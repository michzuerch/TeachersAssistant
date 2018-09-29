package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.report.jasper.ReportJasper;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.ReportJasperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportJasperService {

    @Autowired
    ReportJasperRepository repository;

    public ReportJasper add(ReportJasper val) {
        return repository.save(val);
    }
}