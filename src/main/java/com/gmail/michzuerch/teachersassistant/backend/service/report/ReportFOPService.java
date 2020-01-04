package com.gmail.michzuerch.teachersassistant.backend.service.report;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.report.ReportFOP;
import com.gmail.michzuerch.teachersassistant.backend.repositories.report.ReportFOPRepository;
import com.gmail.michzuerch.teachersassistant.backend.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class ReportFOPService implements CrudService<ReportFOP> {

    private final ReportFOPRepository reportFOPRepository;

    @Autowired
    public ReportFOPService(ReportFOPRepository reportFOPRepository) {
        super();
        this.reportFOPRepository = reportFOPRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public ReportFOP saveReportFOP(User currentUser, Long id, BiConsumer<User, ReportFOP> reportFOPFiller) {
        ReportFOP reportFOP;
        if (id == null) {
            reportFOP = new ReportFOP();
        } else {
            reportFOP = load(id);
        }
        reportFOPFiller.accept(currentUser, reportFOP);
        return reportFOPRepository.save(reportFOP);
    }

    @Transactional(rollbackOn = Exception.class)
    public ReportFOP saveReportFOP(ReportFOP reportFOP) {
        return reportFOPRepository.save(reportFOP);
    }

    @Override
    public JpaRepository<ReportFOP, Long> getRepository() {
        return reportFOPRepository;
    }

    @Override
    @Transactional
    public ReportFOP createNew(User currentUser) {
        ReportFOP reportCSS = new ReportFOP();
        return reportCSS;
    }
}
