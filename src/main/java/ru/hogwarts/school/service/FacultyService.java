package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty createFaculty(Faculty student);

    Faculty getFaculty(Long id);

    Faculty editFaculty(Long id, Faculty student);

    void removeFaculty(Long id);

    List<Faculty> getListStudentByAge(String color);
}
