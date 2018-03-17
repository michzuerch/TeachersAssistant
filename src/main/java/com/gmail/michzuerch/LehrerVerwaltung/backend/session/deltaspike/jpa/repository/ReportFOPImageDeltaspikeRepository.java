package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.report.fop.ReportFOPImage;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = ReportFOPImage.class)
public interface ReportFOPImageDeltaspikeRepository extends EntityRepository<ReportFOPImage, Long> {
    List<ReportFOPImage> findByBezeichnungLikeIgnoreCase(String bezeichnung);
}
