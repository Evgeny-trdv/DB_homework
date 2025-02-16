package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
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
        return facultyRepository.save(faculty);
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
    public List<Student> getStudentsByFacultyId(Long facultyId) {
        return facultyRepository.findById(facultyId).get().getStudents();
    }


}
