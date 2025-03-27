package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface FacultyService {
    Faculty createFaculty(Faculty student);

    Faculty getFaculty(Long id);

    Faculty editFaculty(Long id, Faculty student);

    void removeFaculty(Long id);

    Collection<Faculty> getAllFaculties();

    Collection<Faculty> getListFacultiesByColor(String color);

    Collection<Faculty> getListFacultiesByName(String name);

    Faculty getFacultyByStudentId(Long studentId);

    String getFacultyWithLenghtestName();
}
