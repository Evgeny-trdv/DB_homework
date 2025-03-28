package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        logger.info("Was invoked method create student");
        if (student.getName() == null || student.getName().isEmpty()) {
            logger.error("An empty name was entered");
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long id) {
        logger.info("Was invoked method get student");
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent(Long id, Student student) {
        logger.info("Was invoked method edit student");
        Optional<Student> foundStudent = studentRepository.findById(id);
        if (foundStudent.isPresent()) {
            Student updateStudent = foundStudent.get();
            updateStudent.setName(student.getName());
            updateStudent.setFaculty(student.getFaculty());
            updateStudent.setAge(student.getAge());
            updateStudent.setAvatar(student.getAvatar());
            return studentRepository.save(updateStudent);
        } else {
            logger.error("An student with id {} was not found", id);
            throw new IllegalArgumentException("Student not found");
        }
    }

    @Override
    public void removeStudent(Long id) {
        logger.info("Was invoked method remove student");
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getListStudentByAge(int age) {
        logger.info("Was invoked method get list students by age");
        return studentRepository.findAll()
                .stream()
                .filter(e -> e.getAge() == age)
                .toList();
    }

    @Override
    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method get list students");
        return studentRepository.findAll();
    }

    @Override
    public Collection<Student> getAllStudentsByAgeBetween(int min, int max) {
        logger.info("Was invoked method get list students by age between");
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Collection<Student> findStudentsByFacultyId(Long facultyId) {
        logger.info("Was invoked method find students by faculty id");
        return studentRepository.findByFacultyId(facultyId);
    }

    @Override
    public Faculty getFacultyByStudentId(Long id) {
        logger.info("Was invoked method get faculty by student id");
        return studentRepository.findById(id).get().getFaculty();
    }

    @Override
    public Integer getAmountStudents() {
        logger.info("Was invoked method get amount students");
        return studentRepository.getAmountStudents();
    }

    @Override
    public Integer getAverageAgeStudents() {
        logger.info("Was invoked method get average age students");
        return studentRepository.getAverageAgeStudents();
    }

    @Override
    public Collection<Student> getLastFiveStudents() {
        logger.info("Was invoked method get last five students");
        return studentRepository.getLastFiveStudents();
    }

    @Override
    public void getNameStudentsPrintParallel() {
        studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .limit(3)
                .forEach(System.out::println);

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            studentRepository.findAll()
                    .stream()
                    .map(Student::getName)
                    .skip(3)
                    .limit(3)
                    .forEach(System.out::println);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            studentRepository.findAll()
                    .stream()
                    .map(Student::getName)
                    .skip(6)
                    .forEach(System.out::println);
        }).start();
    }

    @Override
    public void getNameStudentsPrintSynchronized() {
        synchronized (StudentServiceImpl.class) {
            getNameStudentsPrintParallel();
        }
    }
}
