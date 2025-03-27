package ru.hogwarts.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerMVCTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FacultyRepository facultyRepository;

    @MockitoBean
    private FacultyService facultyService;

    @Test
    public void createFaculty() throws Exception {
        Faculty creatingFaculty = new Faculty("Math", "red", null);
        creatingFaculty.setId(22L);

        Mockito.when(facultyService.createFaculty(Mockito.any(Faculty.class))).thenReturn(creatingFaculty);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/faculties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(creatingFaculty)));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(22L))
                .andExpect(jsonPath("$.name").value("Math"));
    }

    @Test
    public void getFaculty() throws Exception {
        Faculty faculty = new Faculty("Math", "red", null);
        faculty.setId(22L);

        Mockito.when(facultyService.getFaculty(Mockito.any(Long.class))).thenReturn(faculty);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/faculties/" + faculty.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faculty)));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(22L))
                .andExpect(jsonPath("$.name").value("Math"));
    }

    @Test
    public void updateFaculty() throws Exception {
        Faculty updatingFaculty = new Faculty("Math", "red", null);
        updatingFaculty.setId(22L);

        Mockito.when(facultyService.editFaculty(Mockito.any(Long.class), Mockito.any(Faculty.class))).thenReturn(updatingFaculty);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/faculties/" + updatingFaculty.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatingFaculty)));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(22L))
                .andExpect(jsonPath("$.name").value("Math"));
    }

    @Test
    public void deleteFaculty() throws Exception {
        Faculty faculty = new Faculty("Math", "red", null);
        faculty.setId(22L);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/faculties/" + faculty.getId()));

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void getListFaculties() throws Exception {
        Faculty facultyFirst = new Faculty("Math", "red", null);
        facultyFirst.setId(22L);
        Faculty facultySecond = new Faculty("biology", "white", null);
        facultySecond.setId(23L);
        Collection<Faculty> faculties = new ArrayList<>(List.of(facultyFirst, facultySecond));

        Mockito.when(facultyService.getAllFaculties()).thenReturn(faculties);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/faculties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faculties)));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(22L))
                .andExpect(jsonPath("$[0].name").value("Math"))
                .andExpect(jsonPath("$[1].id").value(23L))
                .andExpect(jsonPath("$[1].name").value("biology"));
    }

    @Test
    public void getFacultyByStudentId() throws Exception {
        Faculty faculty = new Faculty("Math", "red", null);
        faculty.setId(22L);
        Student gettingFirstStudent = new Student("Harry", 19, faculty, null);
        gettingFirstStudent.setId(11L);

        Mockito.when(facultyService.getFacultyByStudentId(Mockito.any(Long.class))).thenReturn(faculty);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/faculties/student/" + gettingFirstStudent.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gettingFirstStudent))
                .content(objectMapper.writeValueAsString(faculty)));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(22L))
                .andExpect(jsonPath("$.name").value("Math"));
    }
}
