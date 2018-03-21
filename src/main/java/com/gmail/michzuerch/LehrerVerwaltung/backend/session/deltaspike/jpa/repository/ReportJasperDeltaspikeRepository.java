package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.report.jasper.ReportJasper;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = ReportJasper.class)
public interface ReportJasperDeltaspikeRepository extends EntityRepository<ReportJasper, Long> {
    List<ReportJasper> findByBezeichnungLikeIgnoreCase(String bezeichnung);
}