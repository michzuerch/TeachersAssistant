package com.gmail.michzuerch.teachersassistant.backend.service;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.Teacher;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class TeacherService implements CrudService<Teacher> {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        super();
        this.teacherRepository = teacherRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public Teacher saveTeacher(User currentUser, Long id, BiConsumer<User, Teacher> teacherFiller) {
        Teacher teacher;
        if (id == null) {
            teacher = new Teacher();
        } else {
            teacher = load(id);
        }
        teacherFiller.accept(currentUser, teacher);
        return teacherRepository.save(teacher);
    }

    @Transactional(rollbackOn = Exception.class)
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public JpaRepository<Teacher, Long> getRepository() {
        return teacherRepository;
    }

    @Override
    @Transactional
    public Teacher createNew(User currentUser) {
        Teacher teacher = new Teacher();
        return teacher;
    }
}
