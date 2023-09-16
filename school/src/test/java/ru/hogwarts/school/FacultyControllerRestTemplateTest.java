package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerRestTemplateTest {
    @LocalServerPort
    private int port;
    @Autowired
    FacultyController facultyController;
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void contextLoad() throws  Exception{

    }
//    @Test
//    public void wrightFacultyTest() throws Exception {
//        Faculty faculty = new Faculty();
//        faculty.setName("first");
//        faculty.setColor("green");
//        Assertions.assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class)).isNotNull();
//    }
//    @Test
//    public void changeFacultyTest() throws Exception {
//        Faculty faculty = new Faculty();
//        faculty.setName("first");
//        faculty.setColor("green");
//       this.testRestTemplate.put("http://localhost:" + port + "/faculty", faculty, String.class);
//        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/"
//                + faculty.getName() + faculty.getColor(), String.class)).isNotNull();
//
//    }

    @Test
    public void findStudentTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("first");
        faculty.setColor("green");
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/" + faculty.getId(), String.class)).isNotNull();
    }

    @Test
    public void deleteStudentTest()throws Exception {
            Faculty faculty = new Faculty();
            faculty.setName("first");
            faculty.setColor("green");
            this.testRestTemplate.delete("http://localhost:" + port + "/faculty/" + faculty.getId(), String.class);

    }
    @Test
    public void findFacultyByNaveOrColorTest() throws Exception{
        Faculty faculty = new Faculty();
        faculty.setName("first");
        faculty.setColor("green");
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/"
                + faculty.getName() + faculty.getColor(), String.class)).isNotNull();
    }
}
