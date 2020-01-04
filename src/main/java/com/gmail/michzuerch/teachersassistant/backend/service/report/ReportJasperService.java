package com.gmail.michzuerch.teachersassistant.backend.service.report;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.report.ReportJasper;
import com.gmail.michzuerch.teachersassistant.backend.repositories.report.ReportJasperRepository;
import com.gmail.michzuerch.teachersassistant.backend.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class ReportJasperService implements CrudService<ReportJasper> {

    private final ReportJasperRepository reportJasperRepository;

    @Autowired
    public ReportJasperService(ReportJasperRepository reportJasperRepository) {
        super();
        this.reportJasperRepository = reportJasperRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public ReportJasper saveReportJasper(User currentUser, Long id, BiConsumer<User, ReportJasper> reportJasperFiller) {
        ReportJasper reportJasper;
        if (id == null) {
            reportJasper = new ReportJasper();
        } else {
            reportJasper = load(id);
        }
        reportJasperFiller.accept(currentUser, reportJasper);
        return reportJasperRepository.save(reportJasper);
    }

    @Transactional(rollbackOn = Exception.class)
    public ReportJasper saveReportJasper(ReportJasper reportJasper) {
        return reportJasperRepository.save(reportJasper);
    }

    @Override
    public JpaRepository<ReportJasper, Long> getRepository() {
        return reportJasperRepository;
    }

    @Override
    @Transactional
    public ReportJasper createNew(User currentUser) {
        ReportJasper reportJasper = new ReportJasper();
        return reportJasper;
    }
}
