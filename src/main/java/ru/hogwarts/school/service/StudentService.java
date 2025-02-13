package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);

    Student getStudent(Long id);

    Student editStudent(Long id, Student student);

    void removeStudent(Long id);

    List<Student> getListStudentByAge(int age);
}
