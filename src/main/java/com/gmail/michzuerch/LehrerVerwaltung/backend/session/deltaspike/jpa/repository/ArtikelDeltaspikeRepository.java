package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Artikel;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Artikelkategorie;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Artikel.class)
public interface ArtikelDeltaspikeRepository extends EntityRepository<Artikel, Long> {
    List<Artikel> findByBezeichnungLikeIgnoreCase(String bezeichnung);

    List<Artikel> findByArtikelkategorie(Artikelkategorie artikelkategorie);

    List<Artikel> findByArtikelkategorieAndBezeichnungLikeIgnoreCase(Artikelkategorie artikelkategorie, String bezeichnung);
}
