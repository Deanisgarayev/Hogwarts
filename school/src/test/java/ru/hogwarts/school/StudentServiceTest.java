package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.Student;

import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    private StudentService out;
    private StudentRepository studentRepository;
    private AvatarRepository avatarRepository;
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        studentRepository = mock(StudentRepository.class);
        avatarRepository = mock((AvatarRepository.class));
        studentService = mock((StudentService.class));
        out = new StudentService(studentRepository, avatarRepository, studentService);
    }
    private List<Student> students() {
        return List.of(
                new Student(2L, "Ron", 8),
                new Student(3L, "Hermione", 9)
        );
    }
    @Test
    public void findByAgeBetweenTest() {
        when(studentRepository.save( new Student(1L, "Harry Potter", 7))).thenReturn(new Student(1L, "Harry Potter", 7));
        Student student = new Student(1L, "Harry Potter", 7);
        Student result =  out.writeStudent(new Student( 1L,"Harry Potter", 7));
        out.findByAgeBetween(7,7);
        assertEquals(student, result);
        verify(studentRepository, times(1)).findByAgeBetween(7,7);
    }

    @Test
    public void writeStudentTest() {
        when(studentRepository.save( new Student(1L, "Harry Potter", 7))).thenReturn(new Student(1L, "Harry Potter", 7));

        Student student = new Student(1L, "Harry Potter", 7);
        Student result = out.writeStudent(new Student( 1L,"Harry Potter", 7));
        assertEquals(student, result);
        verify(studentRepository, times(1)).save(new Student(1L, "Harry Potter", 7));
    }

    @Test
    public void findAvatar() {
        when(avatarRepository.findByStudentId(1L)).thenReturn(Optional.of(new Avatar()));
        out.findAvatar(1L);
        verify(avatarRepository, times(1)).findByStudentId(1L);

    }
    @Test
    public void findStudentTest() {
        when(studentRepository.findById( 1L)).thenReturn(Optional.of(new Student(1L, "Harry Potter", 7)));

        Student student = new Student(1L, "Harry Potter", 7);
        Student result = out.findStudent(1L);
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    public void findByAgeTest() {
        when(studentRepository.save( new Student(1L, "Harry Potter", 7))).thenReturn(new Student(1L, "Harry Potter", 7));

        Student student = new Student(1L, "Harry Potter", 7);
        Student result = out.writeStudent(new Student( 1L,"Harry Potter", 7));
        out.findByAge(7);
        assertEquals(student, result);
        verify(studentRepository, times(1)).findByAge(7);
    }
    @Test
    public void findAllStudentsTest() {
        when(studentRepository.findAll()).thenReturn(students());
        out.writeStudent(new Student( 2L, "Ron", 8));
        out.writeStudent(new Student(3L, "Hermione", 9));
        assertIterableEquals(students(), out.findAllStudents());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void changeStudentTest() {
        when(studentRepository.save( new Student(1L, "Harry Potter", 7))).thenReturn(new Student(1L, "Harry Potter", 7));

        Student student = new Student(1L, "Harry Potter", 7);
        Student result = out.changeStudent(new Student( 1L,"Harry Potter", 7));
        assertEquals(student, result);
        verify(studentRepository, times(1)).save(new Student(1L, "Harry Potter", 7));
    }
    @Test
    public void removeStudentTest() {
        when(studentRepository.findById( 1L)).thenReturn(Optional.of(new Student(1L, "Harry Potter", 7)));
        Student student = new Student(1L, "Harry Potter", 7);
        Student result = out.findStudent(1L);
        out.removeStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }
}


