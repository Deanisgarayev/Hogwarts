package test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AvatarRepository avatarRepository;
    @SpyBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void wrighStudentyTest()throws Exception{
        final Long id = 1L;
        final String name = "Harry";
        final int age = 7;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", "Harry");
        studentObject.put("age", 7);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }
    @Test
    public void changeStudentTest() throws Exception {
        final Long id = 1L;
        final String name = "Harry";
        final int age = 7;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", "Harry");
        studentObject.put("age", 7);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        mockMvc.perform(MockMvcRequestBuilders

                        .put("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void findStudentTest() throws Exception {

        final Long id = 1L;
        final String name = "Harry";
        final int age = 7;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", "Harry");
        studentObject.put("age", 7);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }
    @Test
    public void deleteStudentTest() throws Exception {

        final Long id = 1L;
        final String name = "Harry";
        final int age = 7;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", "Harry");
        studentObject.put("age", 7);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        doNothing().when(studentRepository).deleteById(id);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + id))
                .andExpect(status().isOk());
    }
    @Test
    public void findByAgeTest() throws Exception {
        final Long id = 1L;
        final String name = "Harry";
        final int age = 7;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", "Harry");
        studentObject.put("age", 7);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.findByAge(any(Integer.class))).thenReturn(List.of(student));
//        uploadAvatar
//        downLoadAvatar

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findByAgeBetweenTest() throws Exception {

        final Long id = 1L;
        final String name = "Harry";
        final int age = 7;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", "Harry");
        studentObject.put("age", 7);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        doNothing().when(studentRepository).findByAgeBetween(1, 10);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + 1 + 10))
                .andExpect(status().isOk());

    }
}

