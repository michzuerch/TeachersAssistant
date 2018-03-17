package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Adresse.class)
public interface AdresseDeltaspikeRepository extends EntityRepository<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Adresse, Long> {
    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Adresse> findByFirmaLikeIgnoreCaseAndNachnameLikeIgnoreCaseAndOrtLikeIgnoreCase(String firma, String nachname, String ort);

    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Adresse> findByNachnameLikeIgnoreCaseAndOrtLikeIgnoreCase(String nachname, String ort);

    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Adresse> findByFirmaLikeIgnoreCaseAndNachnameLikeIgnoreCase(String firma, String nachname);

    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Adresse> findByFirmaLikeIgnoreCaseAndOrtLikeIgnoreCase(String firma, String Ort);

    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Adresse> findByNachnameLikeIgnoreCase(String nachname);

    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Adresse> findByFirmaLikeIgnoreCase(String firma);

    List<com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Adresse> findByOrtLikeIgnoreCase(String ort);
}
