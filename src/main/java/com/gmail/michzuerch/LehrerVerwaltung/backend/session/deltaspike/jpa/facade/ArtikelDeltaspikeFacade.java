package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Artikel;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Artikelkategorie;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.ArtikelDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ArtikelDeltaspikeFacade {
    @Inject
    ArtikelDeltaspikeRepository repo;

    public Artikel findBy(Long id) {
        return repo.findBy(id);
    }

    public void delete(Artikel val) {
        repo.attachAndRemove(val);
    }

    public Artikel save(Artikel val) {
        return repo.save(val);
    }

    public List<Artikel> findAll() {
        return repo.findAll();
    }

    public List<Artikel> findByBezeichnungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }

    public List<Artikel> findByArtikelkategorieAndBezeichnungLikeIgnoreCase(Artikelkategorie artikelkategorie, String bezeichnung) {
        return repo.findByArtikelkategorieAndBezeichnungLikeIgnoreCase(artikelkategorie, bezeichnung);
    }

    public List<Artikel> findByArtikelkategorie(Artikelkategorie artikelkategorie) {
        return repo.findByArtikelkategorie(artikelkategorie);
    }
}
