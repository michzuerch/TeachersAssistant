package com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.facade;

import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Adresse;
import com.gmail.michzuerch.LehrerVerwaltung.backend.entity.Rechnung;
import com.gmail.michzuerch.LehrerVerwaltung.backend.session.deltaspike.jpa.repository.RechnungDeltaspikeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/RechnungDeltaspikeFacade")
public class RechnungDeltaspikeFacade {
    @Inject
    RechnungDeltaspikeRepository repo;

    @GET
    @Path("/findBy/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Rechnung findBy(@PathParam(value = "id") Long id) {
        return repo.findBy(id);
    }

    public void delete(Rechnung val) {
        repo.attachAndRemove(val);
    }

    public Rechnung save(Rechnung val) {
        return repo.save(val);
    }

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rechnung> findAll() {
        return repo.findAll();
    }

    public List<Rechnung> findByBezeichnungLikeIgnoreCase(String bezeichnung) {
        return repo.findByBezeichnungLikeIgnoreCase(bezeichnung);
    }

    public List<Rechnung> findByAdresse(Adresse adresse) {
        return repo.findByAdresse(adresse);
    }

    public List<Rechnung> findByAdresseAndBezeichnungLikeIgnoreCase(Adresse adresse, String bezeichnung) {
        return repo.findByAdresseAndBezeichnungLikeIgnoreCase(adresse, bezeichnung);
    }


}
