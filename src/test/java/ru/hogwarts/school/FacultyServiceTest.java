package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.impl.FacultyServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    public static Faculty createdFacultyFirst() {
        Faculty faculty = new Faculty();
        faculty.setName("Math");
        faculty.setColor("red");
        faculty.setId(1L);
        return faculty;
    }

    public static Faculty createdFacultySecond() {
        Faculty faculty = new Faculty();
        faculty.setName("History");
        faculty.setColor("red");
        faculty.setId(1L);
        return faculty;
    }

    public static List<Faculty> facultyList = new ArrayList<>(List.of(
            createdFacultyFirst(),
            createdFacultySecond()
    ));


    @Mock
    FacultyRepository facultyRepository;

    @InjectMocks
    FacultyServiceImpl out;

    @Test
    public void shouldReturnResultOfCreateOrEditFacultyWhenIsCreatedOrEdited() {
        Mockito.when(facultyRepository.save(createdFacultyFirst())).thenReturn(createdFacultyFirst());
        Assertions.assertEquals(createdFacultyFirst(), out.createFaculty(createdFacultyFirst()));
    }

    @Test
    public void shouldReturnResultOfGetFacultyWhenIsFound() {
        Mockito.when(facultyRepository.findById(1L)).thenReturn(Optional.of(createdFacultyFirst()));
        Assertions.assertEquals(createdFacultyFirst(), out.getFaculty(1L));
    }

    @Test
    public void shouldReturnOfGetListByColorWhenIsFound() {
        Mockito.when(facultyRepository.findAll()).thenReturn(List.of(
                createdFacultyFirst(),
                createdFacultySecond()
        ));
        Assertions.assertIterableEquals(facultyList, out.getListStudentByAge("red"));
    }

}
