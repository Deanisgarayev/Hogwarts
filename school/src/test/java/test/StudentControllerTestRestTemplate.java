package test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTestRestTemplate {
    @LocalServerPort
    private int port;
    @Autowired
    StudentController studentController;
    @Autowired
    TestRestTemplate testRestTemplate;
    @Test
    public void contextLoad()throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }
//    @Test
//    public void studentTest()throws Exception {
//        Student student = new Student();
//        student.setId(1L);
//        student.setName("Harry");
//        student.setAge(7);
//        Assertions.assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class)).isNotNull();
//    }
}
