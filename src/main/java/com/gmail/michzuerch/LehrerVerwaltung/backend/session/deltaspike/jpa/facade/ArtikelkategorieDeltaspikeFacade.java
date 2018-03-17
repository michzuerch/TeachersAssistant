package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Artikelkategorie;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.ArtikelkategorieDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ArtikelkategorieDeltaspikeFacade {
    @Inject
    ArtikelkategorieDeltaspikeRepository repo;

    public Artikelkategorie findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Artikelkategorie val) {
        repo.attachAndRemove(val);
    }

    public Artikelkategorie save(Artikelkategorie val) {
        return repo.save(val);
    }

    public List<Artikelkategorie> findAll() {
        return repo.findAll();
    }

    public List<Artikelkategorie> findByBezeichnungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }

}
