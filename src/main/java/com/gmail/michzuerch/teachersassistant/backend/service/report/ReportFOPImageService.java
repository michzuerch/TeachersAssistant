package com.gmail.michzuerch.teachersassistant.backend.service.report;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.report.ReportFOPImage;
import com.gmail.michzuerch.teachersassistant.backend.repositories.report.ReportFOPImageRepository;
import com.gmail.michzuerch.teachersassistant.backend.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class ReportFOPImageService implements CrudService<ReportFOPImage> {

    private final ReportFOPImageRepository reportFOPImageRepository;

    @Autowired
    public ReportFOPImageService(ReportFOPImageRepository reportFOPImageRepository) {
        super();
        this.reportFOPImageRepository = reportFOPImageRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public ReportFOPImage saveReportFOPImage(User currentUser, Long id, BiConsumer<User, ReportFOPImage> reportFOPImageFiller) {
        ReportFOPImage reportFOPImage;
        if (id == null) {
            reportFOPImage = new ReportFOPImage();
        } else {
            reportFOPImage = load(id);
        }
        reportFOPImageFiller.accept(currentUser, reportFOPImage);
        return reportFOPImageRepository.save(reportFOPImage);
    }

    @Transactional(rollbackOn = Exception.class)
    public ReportFOPImage saveReportFOPImage(ReportFOPImage reportFOPImage) {
        return reportFOPImageRepository.save(reportFOPImage);
    }

    @Override
    public JpaRepository<ReportFOPImage, Long> getRepository() {
        return reportFOPImageRepository;
    }

    @Override
    @Transactional
    public ReportFOPImage createNew(User currentUser) {
        ReportFOPImage reportFOPImage = new ReportFOPImage();
        return reportFOPImage;
    }
}
