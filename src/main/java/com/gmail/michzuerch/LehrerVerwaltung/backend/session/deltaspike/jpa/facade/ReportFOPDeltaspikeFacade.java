package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.report.fop.ReportFOP;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.ReportFOPDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ReportFOPDeltaspikeFacade {
    @Inject
    ReportFOPDeltaspikeRepository repo;

    public ReportFOP findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(ReportFOP val) {
        repo.attachAndRemove(val);
    }

    public ReportFOP save(ReportFOP val) {
        return repo.save(val);
    }

    public List<ReportFOP> findAll() {
        return repo.findAll();
    }

    public List<ReportFOP> findByBezeichnungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }

}
