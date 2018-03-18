package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.report.fop.ReportFOP;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = ReportFOP.class)
public interface ReportFOPDeltaspikeRepository extends EntityRepository<ReportFOP, Long> {
    List<ReportFOP> findByBezeichnungLikeIgnoreCase(String bezeichnung);
}
