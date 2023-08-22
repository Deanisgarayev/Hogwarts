package test;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTestRestTemplate {
    @LocalServerPort
    private int port;
    @Autowired
    FacultyController facultyController;
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void contextLoad() throws  Exception{
        Assertions.assertThat(facultyController).isNotNull();
    }
    @Test
    public void testPostFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("first");
        faculty.setColor("green");
        Assertions.assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class)).isNotNull();
        Assertions.assertThat(this.testRestTemplate.delete("http://localhost:" + port + "/faculty",  faculty.getId(1L), String.class)).isNotNull();
    }
}
