package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty editFaculty(Long id, Faculty faculty) {
        Optional<Faculty> foundFaculty = facultyRepository.findById(id);
        if (foundFaculty.isPresent()) {
            Faculty updatedFaculty = foundFaculty.get();
            updatedFaculty.setName(faculty.getName());
            updatedFaculty.setColor(faculty.getColor());
            updatedFaculty.setStudents(faculty.getStudents());
            return facultyRepository.save(updatedFaculty);
        } else {
            throw new IllegalArgumentException("Faculty not found");
        }
    }

    @Override
    public void removeFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> getListFacultyByColor(String color) {
        return facultyRepository.findAll()
                .stream()
                .filter(e -> e.getColor().equals(color))
                .toList();
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Collection<Faculty> getListFacultiesByColor(String color) {
        return facultyRepository.findFacultiesByColorIgnoreCase(color);
    }

    @Override
    public Collection<Faculty> getListFacultiesByName(String name) {
        return facultyRepository.findFacultiesByNameIgnoreCase(name);
    }

    @Override
    public Faculty getFacultyByStudentId(Long studentId) {
        return studentRepository.findById(studentId).get().getFaculty();
    }


}
