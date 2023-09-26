package ru.hogwarts.school.service;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private  StudentRepository studentRepository;
    private  AvatarRepository avatarRepository;

    @Value("${avatars.dir.path}")
    private String avatarsDir;

    @Autowired
    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;

    }

    public StudentService() {

    }

    //finds all students from db
    public Student writeStudent(Student student) {
        logger.debug("requesting write student: {}", student);
        return studentRepository.save(student);
    }

    //finds student by id from db
    public Avatar findAvatar(Long studentId) {
        logger.debug("requesting find student by studentId: {}", studentId);
        return avatarRepository.findByStudentId(studentId).orElseThrow();
    }

    //finds student by id from db
    public List<Student> findByAge(int age) {
        logger.debug("requesting find student by age: {}", age);
        return studentRepository.findByAge(age);
    }

    //finds students by between min and max age from db
    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.debug("requesting find student by age between: {}, {}", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

//    finds all students from db
    public Collection<Student> findAllStudents() {
        logger.debug("requesting find All students");
        return studentRepository.findAll();
    }

    //    edits student at db
    public Student changeStudent(Student student) {
        logger.debug("requesting change student: {}", student);
        return studentRepository.save(student);
    }

//    deletes student from db
    public void removeStudent(Long id) {
        logger.debug("requesting delete student by id: {}", id);
        studentRepository.deleteById(id);
    }

//    finds student by id from db
    public Student findStudent(Long id) {
        logger.debug("requesting find student by id: {}", id);
        return studentRepository.findById(id).get();
    }
//uploads file to db
    public void uploadAvatar(Long studentID, MultipartFile file) throws IOException {
        logger.debug("requesting upload avatar by studentId: {}, and file: {}", studentID, file);
        Student student = findStudent(studentID);
        Path filePath = Path.of("./avatar", studentID + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = file.getInputStream();
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

//file name gets extended
    private String getExtension(String fileName) {
        logger.debug("requesting extension file name: {}", fileName);
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
//finds name of the students starts with a by alphabet from db
    public List<Student> findStudentsByAlphabet() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getName().startsWith("A"))
                .sorted(Comparator.comparing(student -> student.getName()))
                .collect(Collectors.toList());
    }

//finds average age of students from db
    public OptionalDouble findAvgAgeOfStudents() {
        return studentRepository.findAll().stream().mapToInt(student -> student.getAge()).average();
    }

//finds  students' name by id from db
    public void getAllStudents(long id) {
        List<String> students = studentRepository.findById(id).map(student -> student.getName()).stream().collect(Collectors.toList());
        System.out.println(students);
    }

    //finds  students' name by certain ids from db synchronized
    public void getStudentsFromParallelStreams() {
        this.getAllStudents(49L);
        this.getAllStudents(50L);
        new Thread(() -> {
            this.getAllStudents(52L);
            this.getAllStudents(57L);
        }).start();
        new Thread(() -> {
            this.getAllStudents(59L);
            this.getAllStudents(65L);
        }).start();
    }

    //finds  students' name by ids from db synchronized
    public void getStudentsFromParallelSynchronizedStreams() {


        this.getAllStudentsSynchronized(49L);
        this.getAllStudentsSynchronized(50L);
        new Thread(() -> {
            this.getAllStudentsSynchronized(52L);
            this.getAllStudentsSynchronized(57L);
        }).start();
        new Thread(() -> {
            this.getAllStudentsSynchronized(59L);
            this.getAllStudentsSynchronized(65L);
        }).start();
    }

    public final Object flag = new Object();

    //finds  students' name by id from db synchronized
    public void getAllStudentsSynchronized(long id) {
        synchronized (flag) {
            List<String> students = studentRepository.findById(id).map(student -> student.getName()).stream().collect(Collectors.toList());
            System.out.println(students);
        }
    }
}