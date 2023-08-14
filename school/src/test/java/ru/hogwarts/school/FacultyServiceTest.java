package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {
    private FacultyService out;
    private FacultyRepository facultyRepository;

    @BeforeEach
    public void setUp() {
        facultyRepository = mock(FacultyRepository.class);
        out = new FacultyService(facultyRepository);
    }
    private List<Faculty> faculties() {
        return List.of(new Faculty(1L, "Harry Potter", "Gryffindor"),
                new Faculty(2L, "Harry", "Green")
                );
    }
    @Test
    public void findAllFacultiesTest() {
        when(facultyRepository.findAll()).thenReturn(faculties());
        out.writeFaculty(new Faculty( 1L,"Harry Potter", "Gryffindor"));
        out.writeFaculty(new Faculty(2L, "Harry", "Green"));
        assertIterableEquals(faculties(), out.findAllFaculties());
        verify(facultyRepository, times(1)).findAll();
    }

    @Test
    public void writeFacultyTest() {
        when(facultyRepository.save( new Faculty(1L, "Harry Potter", "Gryffindor"))).thenReturn(new Faculty(1L, "Harry Potter", "Gryffindor"));

        Faculty faculty = new Faculty(1L, "Harry Potter", "Gryffindor");
        Faculty result = out.writeFaculty(new Faculty( 1L,"Harry Potter", "Gryffindor"));
        assertEquals(faculty, result);
        verify(facultyRepository, times(1)).save(new Faculty(1L, "Harry Potter", "Gryffindor"));
    }
    @Test
    public void findFacultyTest() {
        when(facultyRepository.findById( 1L)).thenReturn(Optional.of(new Faculty(1L, "Harry Potter", "Gryffindor")));

        Faculty faculty = new Faculty(1L, "Harry Potter", "Gryffindor");
        Faculty result = out.findFaculty(1L);
        verify(facultyRepository, times(1)).findById(1L);
    }
    @Test
    public void findByPartTest() {
        when(facultyRepository.save( new Faculty(1L, "Harry Potter", "Gryffindor"))).thenReturn(new Faculty(1L, "Harry Potter", "Gryffindor"));

        Faculty faculty = new Faculty(1L, "Harry Potter", "Gryffindor");
        Faculty result = out.writeFaculty(new Faculty( 1L,"Harry Potter", "Gryffindor"));
        out.findByPart("Harry Potter", "Gryffindor");
        assertEquals(faculty, result);
        verify(facultyRepository, times(1)).findByNameOrColorIgnoreCase( "Harry Potter", "Gryffindor");
    }
    @Test
    public void changeFacultyTest() {
        when(facultyRepository.save( new Faculty(1L, "Harry Potter", "Gryffindor"))).thenReturn(new Faculty(1L, "Harry Potter", "Gryffindor"));

        Faculty faculty = new Faculty(1L, "Harry Potter", "Gryffindor");
        Faculty result = out.changeFaculty(new Faculty( 1L,"Harry Potter", "Gryffindor"));
        assertEquals(faculty, result);
        verify(facultyRepository, times(1)).save(new Faculty(1L, "Harry Potter", "Gryffindor"));
    }
    @Test
    public void removeFacultyTest() {
        when(facultyRepository.findById( 1L)).thenReturn(Optional.of(new Faculty(1L, "Harry Potter", "Gryffindor")));
        Faculty faculty = new Faculty(1L, "Harry Potter", "Gryffindor");
        Faculty result = out.findFaculty(1L);
        out.removeFaculty(1L);
        verify(facultyRepository, times(1)).deleteById(1L);
    }
}

