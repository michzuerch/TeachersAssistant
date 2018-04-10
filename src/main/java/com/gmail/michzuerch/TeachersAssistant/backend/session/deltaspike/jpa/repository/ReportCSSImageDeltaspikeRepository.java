package com.gmail.michzuerch.TeachersAssistant.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.TeachersAssistant.backend.entity.report.css.ReportCSSImage;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = ReportCSSImage.class)
public interface ReportCSSImageDeltaspikeRepository extends EntityRepository<ReportCSSImage, Long> {
    List<ReportCSSImage> findByBezeichnungLikeIgnoreCase(String bezeichnung);
}
