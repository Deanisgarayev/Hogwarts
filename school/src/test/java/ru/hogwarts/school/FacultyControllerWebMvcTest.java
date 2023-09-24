package ru.hogwarts.school;

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
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void wrightFacultyTest() throws Exception {
        final Long id = 1L;
        final String name = "first";
        final String color = "green";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "first");
        facultyObject.put("color", "green");

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void changeFacultyTest() throws Exception {

        final Long id = 1L;
        final String name = "first";
        final String color = "green";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", 1);
        facultyObject.put("name", "first");
        facultyObject.put("color", "green");

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders

                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }


    @Test
    public void findFacultyTest() throws Exception {

        final Long id = 1L;
        final String name = "first";
        final String color = "green";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "first");
        facultyObject.put("color", "green");

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void deleteFacultyTest() throws Exception {

        final Long id = 1L;
        final String name = "first";
        final String color = "green";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "first");
        facultyObject.put("color", "green");

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        doNothing().when(facultyRepository).deleteById(id);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void findFacultyByNaveOrColorTest() throws Exception {

        final Long id = 1L;
        final String name = "first";
        final String color = "green";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "first");
        facultyObject.put("color", "green");

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findByNameOrColorIgnoreCase(name, color)).thenReturn(List.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?name=first"))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?color=green"))
                .andExpect(status().isOk());

    }

    @Test
    public void findFacultiesWithLongNameTest() throws Exception {
        final Long id = 1L;
        final String name = "first";
        final String color = "green";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "first");
        facultyObject.put("color", "green");

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findAll()).thenReturn(List.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/long_name"))
                .andExpect(status().isOk());
    }
}

