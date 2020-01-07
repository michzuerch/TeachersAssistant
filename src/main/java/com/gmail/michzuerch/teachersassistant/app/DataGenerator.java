package com.gmail.michzuerch.teachersassistant.app;

import com.gmail.michzuerch.teachersassistant.backend.data.Role;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.Classroom;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.Lession;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.School;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.SchoolClass;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.SchoolGrade;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.SchoolSubject;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.Student;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.Teacher;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.User;
import com.gmail.michzuerch.teachersassistant.backend.repositories.*;
import com.gmail.michzuerch.teachersassistant.backend.repositories.report.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@SpringComponent
public class DataGenerator implements HasLogger {
    private final Random random = new Random(1L);

    private UserRepository userRepository;

    private ClassroomRepository classroomRepository;
    private LessionRepository lessionRepository;
    private SchoolClassRepository schoolClassRepository;
    private SchoolGradeRepository schoolGradeRepository;
    private SchoolRepository schoolRepository;
    private SchoolSubjectRepository schoolSubjectRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    private ReportCSSRepository reportCSSRepository;
    private ReportCSSImageRepository reportCSSImageRepository;
    private ReportFOPRepository reportFOPRepository;
    private ReportFOPImageRepository reportFOPImageRepository;
    private ReportJasperRepository reportJasperRepository;
    private ReportJasperImageRepository reportJasperImageRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataGenerator(UserRepository userRepository,
                         ClassroomRepository classroomRepository,
                         LessionRepository lessionRepository,
                         SchoolClassRepository schoolClassRepository,
                         SchoolGradeRepository schoolGradeRepository,
                         SchoolRepository schoolRepository,
                         SchoolSubjectRepository schoolSubjectRepository,
                         StudentRepository studentRepository,
                         TeacherRepository teacherRepository,
                         ReportCSSRepository reportCSSRepository,
                         ReportCSSImageRepository reportCSSImageRepository,
                         ReportFOPRepository reportFOPRepository,
                         ReportFOPImageRepository reportFOPImageRepository,
                         ReportJasperRepository reportJasperRepository,
                         ReportJasperImageRepository reportJasperImageRepository,

                         PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.classroomRepository = classroomRepository;
        this.lessionRepository = lessionRepository;
        this.schoolClassRepository = schoolClassRepository;
        this.schoolGradeRepository = schoolGradeRepository;
        this.schoolRepository = schoolRepository;
        this.schoolSubjectRepository = schoolSubjectRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.reportCSSRepository = reportCSSRepository;
        this.reportCSSImageRepository = reportCSSImageRepository;
        this.reportFOPRepository = reportFOPRepository;
        this.reportFOPImageRepository = reportFOPImageRepository;
        this.reportJasperRepository = reportJasperRepository;
        this.reportJasperImageRepository = reportJasperImageRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void loadData() {
        StopWatch stopWatch = new StopWatch("TeachersAssistant DataGenerator.loadData()");

        stopWatch.start();

        if (userRepository.count() != 0L) {
            getLogger().info("Using existing database");
            return;
        }

        getLogger().info("Generating demo data");

        getLogger().info("... generating users");
        User baker = createBaker(userRepository, passwordEncoder);
        User barista = createBarista(userRepository, passwordEncoder);
        createAdmin(userRepository, passwordEncoder);
        // A set of products without constrains that can be deleted
        createDeletableUsers(userRepository, passwordEncoder);

        getLogger().info("... generating schools");
        createSchools(schoolRepository);

        stopWatch.stop();
        
        getLogger().info("Generated demo data. Time:" + stopWatch.getTotalTimeMillis() + "ms.");
    }


    private void createSchools(SchoolRepository schoolRepository) {
        School school = new School.Builder()
                .description("Testschool")
                .city("Budapesti")
                .build();
        school = schoolRepository.save(school);

        Classroom classroom = new Classroom.Builder()
                .description("Test Classroom")
                .school(school)
                .build();
        classroom = classroomRepository.save(classroom);

        Teacher teacher = new Teacher.Builder()
                .firstname("Michael")
                .lastname("Zürcher")
                .school(school)
                .build();
        teacher = teacherRepository.save(teacher);

        Lession lession = new Lession.Builder()
                .description("Sample Lession Testdata")
                .start(LocalDateTime.now())
                .ende(LocalDateTime.now())
                .classroom(classroom)
                .teacher(teacher)
                .build();
        lession = lessionRepository.save(lession);

        SchoolClass schoolClass = new SchoolClass.Builder()
                .description("Testclass")
                .school(school)
                .build();
        schoolClass = schoolClassRepository.save(schoolClass);


        Student student = new Student.Builder()
                .lastname("Student")
                .firstname("Dummer")
                .schoolClass(schoolClass)
                .build();
        student = studentRepository.save(student);

        // Schulnote in german
        SchoolGrade schoolGrade = new SchoolGrade.Builder()
                .description("Prüfung von 1 Januar 2020")
                .note(BigDecimal.TEN)
                .timestamp(LocalDateTime.now())
                .student(student)
                .build();
        schoolGrade = schoolGradeRepository.save(schoolGrade);

        // Schulfach in german
        SchoolSubject schoolSubject = new SchoolSubject.Builder()
                .description("Biologie")
                .school(school)
                .build();
        schoolSubject = schoolSubjectRepository.save(schoolSubject);

        schoolGrade.setSchoolSubject(schoolSubject);
        schoolGrade = schoolGradeRepository.save(schoolGrade);



    }

    private User createBaker(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return userRepository.save(
                createUser("baker@vaadin.com", "Heidi", "Carter", passwordEncoder.encode("baker"), Role.BAKER, false));
    }

    private User createBarista(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return userRepository.save(createUser("barista@vaadin.com", "Malin", "Castro",
                passwordEncoder.encode("barista"), Role.BARISTA, true));
    }

    private User createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return userRepository.save(
                createUser("admin@michzuerch.gmail.com", "Michael", "Zürcher", passwordEncoder.encode("admin"), Role.ADMIN, true));
    }

    private void createDeletableUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        userRepository.save(
                createUser("peter@vaadin.com", "Peter", "Bush", passwordEncoder.encode("peter"), Role.BARISTA, false));
        userRepository
                .save(createUser("mary@vaadin.com", "Mary", "Ocon", passwordEncoder.encode("mary"), Role.BAKER, true));
    }

    private User createUser(String email, String firstName, String lastName, String passwordHash, String role,
                            boolean locked) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPasswordHash(passwordHash);
        user.setRole(role);
        user.setLocked(locked);
        return user;
    }
}
