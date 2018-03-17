package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Adresse;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Rechnung;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository(forEntity = Rechnung.class)
public interface RechnungDeltaspikeRepository extends EntityRepository<Rechnung, Long> {
    List<Rechnung> findByAdresse(Adresse adresse);

    List<Rechnung> findByBezeichnungLikeIgnoreCase(String bezeichnung);

    List<Rechnung> findByAdresseAndBezeichnungLikeIgnoreCase(Adresse adresse, String bezeichnung);
}
