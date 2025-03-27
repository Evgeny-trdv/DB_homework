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
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentController.class)
public class StudentControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private StudentRepository studentRepository;

    @MockitoBean
    private StudentService studentService;

    @Test
    public void createStudent() throws Exception {
        Student creatingStudent = new Student("Harry", 19, null, null);
        creatingStudent.setId(11L);

        Mockito.when(studentService.createStudent(Mockito.any(Student.class))).thenReturn(creatingStudent);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(creatingStudent)));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(creatingStudent.getId()))
                .andExpect(jsonPath("$.name").value(creatingStudent.getName()))
                .andExpect(jsonPath("$.age").value(creatingStudent.getAge()));
    }

    @Test
    public void getStudent() throws Exception {
        Student gettingStudent = new Student("Harry", 19, null, null);
        gettingStudent.setId(11L);

        Mockito.when(studentService.getStudent(Mockito.any(Long.class))).thenReturn(gettingStudent);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/students/" + gettingStudent.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gettingStudent)));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(gettingStudent.getId()))
                .andExpect(jsonPath("$.name").value(gettingStudent.getName()))
                .andExpect(jsonPath("$.age").value(gettingStudent.getAge()));
    }

    @Test
    public void getAllStudents() throws Exception {
        Student gettingFirstStudent = new Student("Harry", 19, null, null);
        gettingFirstStudent.setId(11L);
        Student gettingSecondStudent = new Student("Lara", 18, null, null);
        gettingSecondStudent.setId(12L);
        Collection<Student> students = List.of(gettingFirstStudent, gettingSecondStudent);

        Mockito.when(studentService.getAllStudents()).thenReturn(students);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(students)));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(gettingFirstStudent.getId()))
                .andExpect(jsonPath("$[0].name").value(gettingFirstStudent.getName()))
                .andExpect(jsonPath("$[0].age").value(gettingFirstStudent.getAge()))
                .andExpect(jsonPath("$[1].id").value(gettingSecondStudent.getId()))
                .andExpect(jsonPath("$[1].name").value(gettingSecondStudent.getName()))
                .andExpect(jsonPath("$[1].age").value(gettingSecondStudent.getAge()));
    }

    @Test
    public void updateStudent() throws Exception {
        Student updatingStudent = new Student("Harry", 19, null, null);
        updatingStudent.setId(11L);

        Mockito.when(studentService.editStudent(Mockito.any(Long.class), Mockito.any(Student.class))).thenReturn(updatingStudent);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/students/" + updatingStudent.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatingStudent)));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatingStudent.getId()))
                .andExpect(jsonPath("$.name").value(updatingStudent.getName()))
                .andExpect(jsonPath("$.age").value(updatingStudent.getAge()));
    }

    @Test
    public void deleteStudent() throws Exception {
        Student deletingStudent = new Student("Harry", 19, null, null);
        deletingStudent.setId(11L);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/students/" + deletingStudent.getId()));

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void getListStudentByAge() throws Exception {
        int age = 19;
        Student gettingFirstStudent = new Student("Harry", 19, null, null);
        gettingFirstStudent.setId(11L);
        Student gettingSecondStudent = new Student("Lara", 19, null, null);
        gettingSecondStudent.setId(12L);
        List<Student> students = List.of(gettingFirstStudent, gettingSecondStudent);

        Mockito.when(studentService.getListStudentByAge(age)).thenReturn(students);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/students/by-age/" + age)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(students)));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(gettingFirstStudent.getId()))
                .andExpect(jsonPath("$[0].name").value(gettingFirstStudent.getName()))
                .andExpect(jsonPath("$[0].age").value(gettingFirstStudent.getAge()))
                .andExpect(jsonPath("$[1].id").value(gettingSecondStudent.getId()))
                .andExpect(jsonPath("$[1].name").value(gettingSecondStudent.getName()))
                .andExpect(jsonPath("$[1].age").value(gettingSecondStudent.getAge()));
    }

    @Test
    public void getListStudentBetween() throws Exception {
        int minAge = 18;
        int maxAge = 20;
        Student gettingFirstStudent = new Student("Harry", 19, null, null);
        gettingFirstStudent.setId(11L);
        Student gettingSecondStudent = new Student("Lara", 19, null, null);
        gettingSecondStudent.setId(12L);
        Collection<Student> students = List.of(gettingFirstStudent, gettingSecondStudent);

        Mockito.when(studentService.getAllStudentsByAgeBetween(minAge, maxAge)).thenReturn(students);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/students/by-age-between?minAge=" + minAge + "&maxAge=" + maxAge)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(students)));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(gettingFirstStudent.getId()))
                .andExpect(jsonPath("$[0].name").value(gettingFirstStudent.getName()))
                .andExpect(jsonPath("$[0].age").value(gettingFirstStudent.getAge()))
                .andExpect(jsonPath("$[1].id").value(gettingSecondStudent.getId()))
                .andExpect(jsonPath("$[1].name").value(gettingSecondStudent.getName()))
                .andExpect(jsonPath("$[1].age").value(gettingSecondStudent.getAge()));
    }

    @Test
    public void findStudentsByFacultyId() throws Exception {
        Student gettingFirstStudent = new Student("Harry", 19, new Faculty("Math", "red", null), null);
        gettingFirstStudent.setId(11L);
        Student gettingSecondStudent = new Student("Lara", 19, new Faculty("Math", "red", null), null);
        gettingSecondStudent.setId(12L);
        Faculty faculty = new Faculty("Math", "red", new HashSet<>(List.of(gettingFirstStudent, gettingSecondStudent)));
        faculty.setId(22L);
        Collection<Student> students = List.of(gettingFirstStudent, gettingSecondStudent);

        Mockito.when(studentService.findStudentsByFacultyId(Mockito.anyLong())).thenReturn(students);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/students/faculty/" + faculty.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(students))
                .content(objectMapper.writeValueAsString(faculty)));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(gettingFirstStudent.getId()))
                .andExpect(jsonPath("$[0].name").value(gettingFirstStudent.getName()))
                .andExpect(jsonPath("$[0].age").value(gettingFirstStudent.getAge()))
                .andExpect(jsonPath("$[1].id").value(gettingSecondStudent.getId()))
                .andExpect(jsonPath("$[1].name").value(gettingSecondStudent.getName()))
                .andExpect(jsonPath("$[1].age").value(gettingSecondStudent.getAge()));
    }
}
