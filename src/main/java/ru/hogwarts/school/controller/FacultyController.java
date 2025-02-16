package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.impl.FacultyServiceImpl;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyServiceImpl facultyService;

    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty student) {
        return facultyService.createFaculty(student);
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
    public void removeFaculty(@PathVariable Long id) {
        facultyService.removeFaculty(id);
    }

    @GetMapping("/by-color/{color}")
    public Collection<Faculty> getListFacultyByColor(@PathVariable String color) {
        return facultyService.getListFacultyByColor(color);
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

    @GetMapping("/faculties-list/{facultyId}")
    public List<Student> getStudentsByFacultyId(@PathVariable Long facultyId) {
        return facultyService.getStudentsByFacultyId(facultyId);
    }
}
