package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Map;
@Service
public class StudentService {
    Map<Long, Student> students;

    public StudentService(Map<Long, Student> students) {
        this.students = students;
    }
    private Long lastId = 0L;

    public Student writeStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent(Long id) {
        return students.get(id);
    }

    public Student changeStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student removeStudent(Long id) {
        return students.remove(id);
    }
}

