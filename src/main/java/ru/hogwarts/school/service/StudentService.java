package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);

    Student getStudent(Long id);

    Student editStudent(Long id, Student student);

    void removeStudent(Long id);

    List<Student> getListStudentByAge(int age);

    Collection<Student> getAllStudentsByAgeBetween(int min, int max);

    Collection<Student> getAllStudents();

    Faculty getFacultyByStudentId(Long id);
}
