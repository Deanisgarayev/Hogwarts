package ru.hogwarts.school.service;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service

public class StudentService {
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
    @Value("${avatars.dir.path}")
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

    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElseThrow();
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
    public void uploadAvatar(Long studentID, MultipartFile file) throws IOException{
        Student student = findStudent(studentID);
        Path filePath = Path.of(avatarsDir, studentID + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try(InputStream is = file.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
            ) {
            bis.transferTo(bos);
        }
        Avatar avatar = avatarRepository.findByStudentId(studentID).orElseGet(Avatar::new);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        avatarRepository.save(avatar);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}

