package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    public Collection<Faculty> findFacultiesByColorIgnoreCase(String color);

    public Collection<Faculty> findFacultiesByNameIgnoreCase(String name);

}
