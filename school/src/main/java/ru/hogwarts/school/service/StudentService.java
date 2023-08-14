package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
//@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
//    @Value("${avatars.dir.path}")
    private String avatarsDir;
@Autowired
    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
    this.avatarRepository = avatarRepository;
    }


    public Student writeStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max ) {
        return studentRepository.findByAgeBetween(min, max);
    }
    public Collection<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student changeStudent(Student student) {
        return studentRepository.save(student);
    }

    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

