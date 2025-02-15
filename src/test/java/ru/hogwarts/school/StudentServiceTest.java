package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.impl.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    public static Student createdStudentFirst() {
        Student student = new Student();
        student.setName("John");
        student.setAge(14);
        student.setId(1L);
        return student;
    }

    public static Student createdStudentThird() {
        Student student = new Student();
        student.setName("Rick");
        student.setAge(14);
        student.setId(3L);
        return student;
    }

    public static List<Student> studentList = new ArrayList<>(List.of(
            createdStudentFirst(),
            createdStudentThird()
    ));

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl out;

    @Test
    public void shouldReturnResultOfCreateOrEditStudentWhenIsCreatedOrEdited() {
        Mockito.when(studentRepository.save(createdStudentFirst())).thenReturn(createdStudentFirst());
        Assertions.assertEquals(createdStudentFirst(), out.createStudent(createdStudentFirst()));
    }

    @Test
    public void shouldReturnResultOfGetStudentWhenIsFound() {
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(createdStudentFirst()));
        Assertions.assertEquals(createdStudentFirst(), out.getStudent(1L));
    }

    @Test
    public void shouldReturnOfGetListByAgeWhenIsFound() {
        Mockito.when(studentRepository.findAll()).thenReturn(List.of(
                createdStudentFirst(),
                createdStudentThird()
        ));
        Assertions.assertIterableEquals(studentList, out.getListStudentByAge(14));
    }

}
