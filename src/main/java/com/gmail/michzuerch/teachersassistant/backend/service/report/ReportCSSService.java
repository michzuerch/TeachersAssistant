package com.gmail.michzuerch.teachersassistant.backend.service.report;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.report.ReportCSS;
import com.gmail.michzuerch.teachersassistant.backend.repositories.report.ReportCSSRepository;
import com.gmail.michzuerch.teachersassistant.backend.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class ReportCSSService implements CrudService<ReportCSS> {

    private final ReportCSSRepository reportCSSRepository;

    @Autowired
    public ReportCSSService(ReportCSSRepository reportCSSRepository) {
        super();
        this.reportCSSRepository = reportCSSRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public ReportCSS saveReportCSS(User currentUser, Long id, BiConsumer<User, ReportCSS> reportCSSFiller) {
        ReportCSS reportCSS;
        if (id == null) {
            reportCSS = new ReportCSS();
        } else {
            reportCSS = load(id);
        }
        reportCSSFiller.accept(currentUser, reportCSS);
        return reportCSSRepository.save(reportCSS);
    }

    @Transactional(rollbackOn = Exception.class)
    public ReportCSS saveReportCSS(ReportCSS reportCSS) {
        return reportCSSRepository.save(reportCSS);
    }

    @Override
    public JpaRepository<ReportCSS, Long> getRepository() {
        return reportCSSRepository;
    }

    @Override
    @Transactional
    public ReportCSS createNew(User currentUser) {
        ReportCSS reportCSS = new ReportCSS();
        return reportCSS;
    }
}
