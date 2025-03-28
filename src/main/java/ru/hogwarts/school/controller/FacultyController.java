package ru.hogwarts.school.controller;

import org.apache.commons.collections4.Get;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.impl.FacultyServiceImpl;
import ru.hogwarts.school.service.impl.StudentServiceImpl;

import java.util.Collection;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> editFaculty(@PathVariable Long id, @RequestBody Faculty student) {
        Faculty foundFaculty = facultyService.editFaculty(id, student);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> removeFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            ResponseEntity.notFound().build();
        }
        facultyService.removeFaculty(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public Collection<Faculty> getListFaculties(@RequestParam(required = false) String color,
                                                @RequestParam(required = false) String name) {
        if (color != null && !color.isBlank()) {
            return facultyService.getListFacultiesByColor(color);
        }
        if (name != null && !name.isBlank()) {
            return facultyService.getListFacultiesByName(name);
        }
        return facultyService.getAllFaculties();
    }

    @GetMapping("/student/{studentId}")
    public Faculty getFacultyByStudentId(@PathVariable Long studentId) {
        return facultyService.getFacultyByStudentId(studentId);
    }

    @GetMapping("/get-faculty-with-lenghtest-name")
    public String getFacultyWithLenghtestName() {
        return facultyService.getFacultyWithLenghtestName();
    }

    @GetMapping("/parallel")
    public Integer getInteger() {
        return facultyService.getParallelAmount();
    }

}
