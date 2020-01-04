package com.gmail.michzuerch.teachersassistant.backend.service;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.Lession;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.repositories.LessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class LessionService implements CrudService<Lession> {

    private final LessionRepository lessionRepository;

    @Autowired
    public LessionService(LessionRepository lessionRepository) {
        super();
        this.lessionRepository = lessionRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public Lession saveLession(User currentUser, Long id, BiConsumer<User, Lession> lessionFiller) {
        Lession lession;
        if (id == null) {
            lession = new Lession();
        } else {
            lession = load(id);
        }
        lessionFiller.accept(currentUser, lession);
        return lessionRepository.save(lession);
    }

    @Transactional(rollbackOn = Exception.class)
    public Lession saveLession(Lession lession) {
        return lessionRepository.save(lession);
    }

    @Override
    public JpaRepository<Lession, Long> getRepository() {
        return lessionRepository;
    }

    @Override
    @Transactional
    public Lession createNew(User currentUser) {
        Lession lession = new Lession();
        return lession;
    }
}
