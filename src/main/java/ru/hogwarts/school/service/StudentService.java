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

    Collection<Student> getAllStudents();

    Collection<Student> getAllStudentsByAgeBetween(int min, int max);

    //Collection<Student> getAllStudents();

    Collection<Student> findStudentsByFacultyId(Long facultyId);

    Faculty getFacultyByStudentId(Long id);

    Integer getAmountStudents();

    Integer getAverageAgeStudents();

    Collection<Student> getLastFiveStudents();

    Collection<String> getNameStudentsStartingWith(String letter);

    Integer getAverageAgeStudentByStreamApi();
}
