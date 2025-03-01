package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testGetFaculty() throws Exception {
        Assertions.assertThat(restTemplate.getForObject(
                        "http://localhost:" + port + "/faculties/1", Faculty.class))
                .isNotNull();
    }

    @Test
    public void testPostFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Music");
        faculty.setColor("Pink");
        Assertions.assertThat(restTemplate.postForObject("http://localhost:" + port
                + "/faculties", faculty, Faculty.class)).isNotNull();
    }

    @Test
    public void testPutFaculty() throws Exception {
        Faculty faculty = new Faculty("Game", "Green", null);
        restTemplate.put("http://localhost:" + port + "/faculties/" + 102, faculty);
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        restTemplate.delete("http://localhost:" + port + "/faculties/102");
    }

    @Test
    public void testGetListFaculties() throws Exception {
        Assertions.assertThat(restTemplate.getForObject(
                        "http://localhost:" + port + "/faculties", Collection.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyByStudentId() throws Exception {
        Assertions.assertThat(restTemplate.getForObject(
                        "http://localhost:" + port + "/faculties/student/402", Faculty.class))
                .isNotNull();
    }


}
