package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.report.css.ReportCSS;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.ReportCSSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportCSSService {

    @Autowired
    ReportCSSRepository repository;

    public ReportCSS add(ReportCSS val) {
        return repository.save(val);
    }
}