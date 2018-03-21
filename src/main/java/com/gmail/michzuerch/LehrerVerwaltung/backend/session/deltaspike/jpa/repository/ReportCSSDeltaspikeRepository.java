package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.report.css.ReportCSS;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = ReportCSS.class)
public interface ReportCSSDeltaspikeRepository extends EntityRepository<ReportCSS, Long> {
    List<ReportCSS> findByBezeichnungLikeIgnoreCase(String bezeichnung);

}