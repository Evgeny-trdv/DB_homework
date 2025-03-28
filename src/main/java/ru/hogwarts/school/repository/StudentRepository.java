package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Collection<Student> findByAgeBetween(int min, int max);

    public List<Student> findByFacultyId(long facultyId);

    public Collection<Student> findStudentsByFacultyId(Long facultyId);

    public Student findById(long id);

    @Query(value = "SELECT COUNT(*) FROM student" , nativeQuery = true)
    public Integer getAmountStudents();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    public Integer getAverageAgeStudents();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    public Collection<Student> getLastFiveStudents();
}
