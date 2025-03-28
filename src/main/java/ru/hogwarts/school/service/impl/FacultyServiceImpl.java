package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class FacultyServiceImpl implements FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method create faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        logger.info("Was invoked method get faculty");
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty editFaculty(Long id, Faculty faculty) {
        logger.info("Was invoked method edit faculty");
        Optional<Faculty> foundFaculty = facultyRepository.findById(id);
        if (foundFaculty.isPresent()) {
            Faculty updatedFaculty = foundFaculty.get();
            updatedFaculty.setName(faculty.getName());
            updatedFaculty.setColor(faculty.getColor());
            updatedFaculty.setStudents(faculty.getStudents());
            return facultyRepository.save(updatedFaculty);
        } else {
            logger.error("A faculty with id {} was not found", id);
            throw new IllegalArgumentException("Faculty not found");
        }
    }

    @Override
    public void removeFaculty(Long id) {
        logger.info("Was invoked method remove faculty");
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        logger.info("Was invoked method get list faculty");
        return facultyRepository.findAll();
    }

    @Override
    public Collection<Faculty> getListFacultiesByColor(String color) {
        logger.info("Was invoked method get list faculty by color");
        return facultyRepository.findFacultiesByColorIgnoreCase(color);
    }

    @Override
    public Collection<Faculty> getListFacultiesByName(String name) {
        logger.info("Was invoked method get list faculty by name");
        return facultyRepository.findFacultiesByNameIgnoreCase(name);
    }

    @Override
    public Faculty getFacultyByStudentId(Long studentId) {
        logger.info("Was invoked method get faculty by student id");
        return studentRepository.findById(studentId).get().getFaculty();
    }

    @Override
    public String getFacultyWithLenghtestName() {
        return facultyRepository
                .findAll()
                .stream()
                .map(Faculty::getName)
                .sorted(Comparator.reverseOrder())
                .findFirst().get();
    }

    @Override
    public Integer getParallelAmount() {
        long startTime = System.nanoTime();
        int sum = IntStream.iterate(1, a -> a+1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, Integer::sum);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Метод выполнился за " + (duration / 1_000_000) + " мс");
        return sum;
    }
}
