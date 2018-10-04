package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.report.css.ReportCSSImage;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.ReportCSSImageRepository;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@VaadinSessionScope
public class ReportCSSImageService {

    @Autowired
    ReportCSSImageRepository repository;

    public ReportCSSImage add(ReportCSSImage val) {
        return repository.save(val);
    }

    public Optional<ReportCSSImage> findBy(Long id) {
        return repository.findById(id);
    }
}