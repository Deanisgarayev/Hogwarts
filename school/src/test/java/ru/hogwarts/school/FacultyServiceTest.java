package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacultyServiceTest {
    private final FacultyService out = new FacultyService();

    @Test
    public void writeStudentTest() {
        Faculty faculty = new Faculty(1L, "Harry Potter", "Gryffindor");
        Faculty result = out.writeFaculty(new Faculty( 1L,"Harry Potter", "Gryffindor"));
        assertEquals(faculty, result);
    }
    @Test
    public void findStudentTest() {
        Faculty faculty = new Faculty(1L, "Harry Potter", "Gryffindor");
        Faculty result = out.writeFaculty(new Faculty( 1L,"Harry Potter", "Gryffindor"));
        out.findFaculty(1L);
        assertEquals(faculty, result);
    }
    @Test
    public void changeStudentTest() {
        Faculty faculty = new Faculty(1L, "Harry Potter", "Gryffindor");
        Faculty result = out.changeFaculty(new Faculty( 1L,"Harry Potter", "Gryffindor"));
        assertEquals(faculty, result);
    }
    @Test
    public void removeStudentTest() {
        Faculty faculty = new Faculty(1L, "Harry Potter", "Gryffindor");
        Faculty result = out.writeFaculty(new Faculty( 1L,"Harry Potter", "Gryffindor"));
        out.removeFaculty( 1L);
        assertEquals(faculty, result);
    }
}

