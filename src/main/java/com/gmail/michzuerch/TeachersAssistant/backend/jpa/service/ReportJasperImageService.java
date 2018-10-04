package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.report.jasper.ReportJasperImage;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.ReportJasperImageRepository;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@VaadinSessionScope
public class ReportJasperImageService {

    @Autowired
    ReportJasperImageRepository repository;

    public ReportJasperImage add(ReportJasperImage val) {
        return repository.save(val);
    }

    public Optional<ReportJasperImage> findBy(Long id) {
        return repository.findById(id);
    }
}