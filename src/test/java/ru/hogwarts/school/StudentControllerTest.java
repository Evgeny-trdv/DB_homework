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
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private StudentController studentController;

	@Autowired
	private StudentRepository studentRepository;

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
		Student studentPost = new Student("John", 19, null, null);
		Assertions.assertThat(restTemplate.postForObject("http://localhost:" + port
				+ "/students", studentPost, Student.class)).isNotNull();
	}

	@Test
	public void testPutStudent() throws Exception {
		Long testedId = 452L;
		Student student = new Student("Mark", 21, null, null);
		ResponseEntity<Student> responsePut = restTemplate.exchange(
				"http://localhost:" + port + "/students/" + testedId,
				HttpMethod.PUT,
				new HttpEntity<>(student),
				Student.class);

		Assertions.assertThat(responsePut.getStatusCode()).isEqualTo(HttpStatus.OK);

		Student updatedStudent = studentRepository.findById(testedId).orElseThrow();
		Assertions.assertThat(updatedStudent.getName()).isEqualTo(student.getName());
		Assertions.assertThat(updatedStudent.getAge()).isEqualTo(student.getAge());
	}

	@Test
	public void testDeleteStudent() throws Exception {

		Long testedId = 552L;
		Assertions.assertThat(studentRepository.findById(testedId).isPresent());
		ResponseEntity<Void> responseDelete = restTemplate.exchange(
				"http://localhost:" + port + "/students/" + testedId,
				HttpMethod.DELETE,
				null,
				Void.class);

		Assertions.assertThat(responseDelete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
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
