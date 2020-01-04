package com.gmail.michzuerch.teachersassistant.backend.service.report;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.report.ReportCSSImage;
import com.gmail.michzuerch.teachersassistant.backend.repositories.report.ReportCSSImageRepository;
import com.gmail.michzuerch.teachersassistant.backend.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class ReportCSSImageService implements CrudService<ReportCSSImage> {

    private final ReportCSSImageRepository reportCSSImageRepository;

    @Autowired
    public ReportCSSImageService(ReportCSSImageRepository reportCSSImageRepository) {
        super();
        this.reportCSSImageRepository = reportCSSImageRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public ReportCSSImage saveReportCSSImage(User currentUser, Long id, BiConsumer<User, ReportCSSImage> reportCSSImageFiller) {
        ReportCSSImage reportCSSImage;
        if (id == null) {
            reportCSSImage = new ReportCSSImage();
        } else {
            reportCSSImage = load(id);
        }
        reportCSSImageFiller.accept(currentUser, reportCSSImage);
        return reportCSSImageRepository.save(reportCSSImage);
    }

    @Transactional(rollbackOn = Exception.class)
    public ReportCSSImage saveReportCSSImage(ReportCSSImage reportCSSImage) {
        return reportCSSImageRepository.save(reportCSSImage);
    }

    @Override
    public JpaRepository<ReportCSSImage, Long> getRepository() {
        return reportCSSImageRepository;
    }

    @Override
    @Transactional
    public ReportCSSImage createNew(User currentUser) {
        ReportCSSImage reportCSSImage = new ReportCSSImage();
        return reportCSSImage;
    }
}
