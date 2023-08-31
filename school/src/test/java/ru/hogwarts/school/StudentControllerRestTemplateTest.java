package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerRestTemplateTest {
    @LocalServerPort
    private int port;
    @Autowired
    StudentController studentController;
    @Autowired
    TestRestTemplate testRestTemplate;
    @Test
    public void contextLoad()throws Exception {

    }
    @Test
    public void wrightStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        Assertions.assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty", student, String.class)).isNotNull();
    }
    @Test
    public void changeStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        this.testRestTemplate.put("http://localhost:" + port + "/student", student, String.class);
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/" + student.getId(), String.class)).isNotNull();

    }

    @Test
    public void findStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/" + student.getId(), String.class)).isNotNull();
    }

    @Test
    public void deleteStudentTest()throws Exception {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        this.testRestTemplate.delete("http://localhost:" + port + "/student/" + student.getId(), String.class);
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/" + student.getId(), String.class)).isNotNull();
    }
    @Test
    public void findByAgeTest() throws Exception{
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/"
                + student.getName() + student.getAge(), String.class)).isNull();
    }

    @Test
    public void findByAgeBetweenTest() throws Exception{
        Student student = new Student();
        student.setName("Harry");
        student.setAge(7);
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/"
                + student.getName() + 1 + 10, String.class)).isNotNull();
}

}
