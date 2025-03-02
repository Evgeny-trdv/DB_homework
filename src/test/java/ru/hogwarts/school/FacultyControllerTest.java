package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private FacultyRepository facultyRepository;

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
        Faculty facultyPost = new Faculty("Music", "Pink", null);
        Assertions.assertThat(restTemplate.postForObject("http://localhost:" + port
                + "/faculties", facultyPost, Faculty.class)).isNotNull();
    }

    @Test
    public void testPutFaculty() throws Exception {
        Long testedId = 152L;
        Faculty faculty = new Faculty("Game", "Green", null);
        ResponseEntity<Faculty> responsePut = restTemplate.exchange(
                "http://localhost:" + port + "/faculties/" + testedId,
                HttpMethod.PUT,
                new HttpEntity<>(faculty),
                Faculty.class
        );

        Assertions.assertThat(responsePut.getStatusCode()).isEqualTo(HttpStatus.OK);

        Faculty updatedFaculty = facultyRepository.findById(testedId).orElseThrow();
        Assertions.assertThat(updatedFaculty.getName()).isEqualTo(faculty.getName());
        Assertions.assertThat(updatedFaculty.getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Long testedId = 152L;
        ResponseEntity<Void> responseDelete = restTemplate.exchange(
                "http://localhost:" + port + "/faculties/" + testedId,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        Assertions.assertThat(responseDelete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
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
