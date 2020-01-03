package com.gmail.michzuerch.teachersassistant.backend.service;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.Student;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.BiConsumer;

@Service
public class StudentService implements CrudService<Student> {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Transactional(rollbackOn = Exception.class)
	public Student saveStudent(User currentUser, Long id, BiConsumer<User, Student> studentFiller) {
		Student student;
		if (id == null) {
			student = new Student();
		} else {
			student = load(id);
		}
		studentFiller.accept(currentUser, student);
		return studentRepository.save(student);
	}

	@Transactional(rollbackOn = Exception.class)
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public JpaRepository<Student, Long> getRepository() {
		return studentRepository;
	}

	@Override
	@Transactional
	public Student createNew(User currentUser) {
	    Student student = new Student();
		return student;
	}
}
