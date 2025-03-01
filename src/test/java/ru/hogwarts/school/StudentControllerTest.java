package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private StudentController studentController;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws Exception {
		Assertions.assertThat(studentController).isNotNull();
	}

	@Test
	public void testGetStudents() throws Exception {
		Assertions.assertThat(restTemplate.getForObject(
				"http://localhost:" + port + "/students", Collection.class))
				.isNotNull();
	}

	@Test
	public void testGetStudent() throws Exception {
		Assertions.assertThat(restTemplate.getForObject(
				"http://localhost:" + port + "/students/2", Student.class))
				.isNotNull();
	}

	@Test
	public void testPostStudent() throws Exception {
		Student student = new Student();
		student.setName("John");
		student.setAge(19);
		student.setFaculty(null);
		student.setAvatar(null);
		Assertions.assertThat(restTemplate.postForObject("http://localhost:" + port
				+ "/students", student, Student.class)).isNotNull();
	}

	@Test
	public void testPutStudent() throws Exception {
		Student student = new Student("Mark", 21, null, null);
		restTemplate.put("http://localhost:" + port + "/students/" + 352, student);
	}

	@Test
	public void testDeleteStudent() throws Exception {
		restTemplate.delete("http://localhost:" + port + "/students/352");
	}

	@Test
	public void testGetListStudentByAge() throws Exception {
		Assertions.assertThat(restTemplate.getForObject(
						"http://localhost:" + port + "/students/by-age/15", Collection.class))
				.isNotNull();
	}

	@Test
	public void testGetListStudentBetween() throws Exception {
		Assertions.assertThat(restTemplate.getForObject(
						"http://localhost:" + port + "/students/by-age-between?minAge=15&maxAge=18", Collection.class))
				.isNotNull();
	}

	@Test
	public void testFindStudentsByFacultyId() throws Exception {
		Assertions.assertThat(restTemplate.getForObject(
						"http://localhost:" + port + "/students/faculty/1", Collection.class))
				.isNotNull();
	}

}
