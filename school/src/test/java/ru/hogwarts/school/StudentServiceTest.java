package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {
    private final StudentService out = new StudentService();

    @Test
    public void writeStudentTest() {
        Student student = new Student(1L, "Harry Potter", 7);
        Student result = out.writeStudent(new Student( 1L,"Harry Potter", 7));
        assertEquals(student, result);
    }
    @Test
    public void findStudentTest() {
        Student student = new Student(1L, "Harry Potter", 7);
        Student result = out.writeStudent(new Student( 1L,"Harry Potter", 7));
        out.findStudent(1L);
        assertEquals(student, result);
    }
    @Test
    public void changeStudentTest() {
        Student student = new Student(1L, "Harry Potter", 7);
        Student result = out.changeStudent(new Student( 1L,"Harry Potter", 7));
        assertEquals(student, result);
    }
    @Test
    public void removeStudentTest() {
        Student student = new Student(1L, "Harry Potter", 7);
        Student result = out.writeStudent(new Student( 1L,"Harry Potter", 7));
         out.removeStudent( 1L);
        assertEquals(student, result);
    }
}

