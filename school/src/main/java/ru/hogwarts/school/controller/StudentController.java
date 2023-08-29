package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.entity.AvgAgeOfStudents;
import ru.hogwarts.school.entity.CountStudents;
import ru.hogwarts.school.entity.FiveLastStudents;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.LongToIntFunction;

@RestController
@RequestMapping("/student")
public class StudentController {
    public StudentService studentService;
@Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/avgAgeOfStudents")
    public List<AvgAgeOfStudents> getAvgAgeOfStudents() {
        return studentService.getAvgAgeOfStudents();
    }

    @GetMapping("/countStudents")
    public List<CountStudents> getCountStudents() {
        return studentService.getCountStudents();
    }

    @GetMapping("/fiveLastStudents")
    public List<FiveLastStudents> getFiveLastStudents() {
        return studentService.getFiveLastStudents();
    }
    @PostMapping
    public Student writeStudent(@RequestBody Student student) {
        return studentService.writeStudent(student);
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> findStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findByAge(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(params = {"min", "max"})
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @PutMapping
    public ResponseEntity<Student> changeStudent(@RequestBody Student student) {
        Student foundstudent = studentService.changeStudent(student);
        if (foundstudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
        }


    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable Long id) {
         studentService.removeStudent(id);
         return ResponseEntity.ok().build();
    }

    @GetMapping("/avatars")
    public ResponseEntity<List<Avatar>> getPagesOfAvatars(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        List<Avatar> avatars = studentService.getAllAvatars(pageNumber, pageSize);
        return ResponseEntity.ok(avatars);
    }
    @PostMapping(value = "/{id}/avatar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("file is too big");
        }
        studentService.uploadAvatar(id,avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]>downLoadAvatar(@PathVariable Long id) {
        Avatar avatar = studentService.findAvatar(id);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = studentService.findAvatar(id);
        Path path = Path.of(avatar.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }
}
