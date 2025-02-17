package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public Collection<Student> findByAgeBetween(int min, int max);

    public List<Student> findByFacultyId(long facultyId);

    public Collection<Student> findStudentsByFacultyId(Long facultyId);

}
