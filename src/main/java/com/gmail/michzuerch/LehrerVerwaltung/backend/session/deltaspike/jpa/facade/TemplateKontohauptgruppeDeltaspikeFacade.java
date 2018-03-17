package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;


import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateKontohauptgruppe;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.TemplateKontoklasse;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.TemplateKontohauptgruppeDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TemplateKontohauptgruppeDeltaspikeFacade {
    @Inject
    TemplateKontohauptgruppeDeltaspikeRepository repo;

    public TemplateKontohauptgruppe findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(TemplateKontohauptgruppe val) {
        repo.attachAndRemove(val);
    }

    public TemplateKontohauptgruppe save(TemplateKontohauptgruppe val) {
        return repo.save(val);
    }

    public List<TemplateKontohauptgruppe> findAll() {
        return repo.findAll();
    }

    public List<TemplateKontohauptgruppe> findByBezeichnungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }

    public List<TemplateKontohauptgruppe> findByTemplateKontoklasse(TemplateKontoklasse templateKontoklasse) {
        return repo.findByTemplateKontoklasse(templateKontoklasse);
    }

}
