package com.gmail.michzuerch.teachersassistant.backend.service.report;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.report.ReportJasperImage;
import com.gmail.michzuerch.teachersassistant.backend.repositories.report.ReportJasperImageRepository;
import com.gmail.michzuerch.teachersassistant.backend.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class ReportJasperImageService implements CrudService<ReportJasperImage> {

    private final ReportJasperImageRepository reportJasperImageRepository;

    @Autowired
    public ReportJasperImageService(ReportJasperImageRepository reportJasperImageRepository) {
        super();
        this.reportJasperImageRepository = reportJasperImageRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public ReportJasperImage saveReportJasperImage(User currentUser, Long id, BiConsumer<User, ReportJasperImage> reportJasperImageFiller) {
        ReportJasperImage reportJasperImage;
        if (id == null) {
            reportJasperImage = new ReportJasperImage();
        } else {
            reportJasperImage = load(id);
        }
        reportJasperImageFiller.accept(currentUser, reportJasperImage);
        return reportJasperImageRepository.save(reportJasperImage);
    }

    @Transactional(rollbackOn = Exception.class)
    public ReportJasperImage saveReportJasperImage(ReportJasperImage reportJasperImage) {
        return reportJasperImageRepository.save(reportJasperImage);
    }

    @Override
    public JpaRepository<ReportJasperImage, Long> getRepository() {
        return reportJasperImageRepository;
    }

    @Override
    @Transactional
    public ReportJasperImage createNew(User currentUser) {
        ReportJasperImage reportJasperImage = new ReportJasperImage();
        return reportJasperImage;
    }
}
