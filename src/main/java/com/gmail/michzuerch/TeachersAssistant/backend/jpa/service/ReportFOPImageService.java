package com.gmail.michzuerch.TeachersAssistant.backend.jpa.service;

import com.gmail.michzuerch.TeachersAssistant.backend.jpa.domain.report.fop.ReportFOPImage;
import com.gmail.michzuerch.TeachersAssistant.backend.jpa.repository.ReportFOPImageRepository;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@VaadinSessionScope
public class ReportFOPImageService {

    @Autowired
    ReportFOPImageRepository repository;

    public ReportFOPImage add(ReportFOPImage val) {
        return repository.save(val);
    }

    public Optional<ReportFOPImage> findBy(Long id) {
        return repository.findById(id);
    }
}