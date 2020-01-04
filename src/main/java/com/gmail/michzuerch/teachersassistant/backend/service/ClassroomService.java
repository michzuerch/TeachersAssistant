package com.gmail.michzuerch.teachersassistant.backend.service;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.Classroom;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class ClassroomService implements CrudService<Classroom> {

    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository) {
        super();
        this.classroomRepository = classroomRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public Classroom saveClassroom(User currentUser, Long id, BiConsumer<User, Classroom> studentFiller) {
        Classroom classroom;
        if (id == null) {
            classroom = new Classroom();
        } else {
            classroom = load(id);
        }
        studentFiller.accept(currentUser, classroom);
        return classroomRepository.save(classroom);
    }

    @Transactional(rollbackOn = Exception.class)
    public Classroom saveClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Override
    public JpaRepository<Classroom, Long> getRepository() {
        return classroomRepository;
    }

    @Override
    @Transactional
    public Classroom createNew(User currentUser) {
        Classroom classroom = new Classroom();
        return classroom;
    }
}
