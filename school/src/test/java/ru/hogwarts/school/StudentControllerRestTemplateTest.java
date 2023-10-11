package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.bind.annotation.GetMapping;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.OptionalDouble;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerRestTemplateTest {
    @LocalServerPort
    private int port;
    @Autowired
    StudentController studentController;
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void contextLoad() throws Exception {

    }

    @Test
    public void findStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/" + student.getId(), String.class)).isNotNull();
    }

    @Test
    public void deleteStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        this.testRestTemplate.delete("http://localhost:" + port + "/student/" + student.getId(), String.class);
    }

    @Test
    public void findByAgeTest() throws Exception {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/"
                + student.getAge(), String.class)).isNotNull();
    }

    @Test
    public void findByAgeBetweenTest() throws Exception {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/"
                + student.getName() + 1 + 10, String.class)).isNotNull();
    }

    @Test
    public void findStudentsByAlphabetTest() {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/names_by_alphabet",
                String.class)).isNotNull();

    }

    @Test
    public void findAvgAgeOfStudentsTest() {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/avg_age",
                String.class)).isNotNull();
    }
    @Test
    public void getStudentsWithParallelStreams() {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/get_students_with_parallel_streams"
                + student.getId() , String.class)).isNotNull();
    }
    @Test
    public void getStudentsWithParallelSynchronizedStreams() {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/get_students_with_parallel_synchronized_streams"
                +student.getId(),
                String.class)).isNotNull();
    }
}
