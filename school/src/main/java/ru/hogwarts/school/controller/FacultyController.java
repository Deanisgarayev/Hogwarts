package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    public FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty wrightFaculty(@RequestBody Faculty faculty) {
        return facultyService.writeFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> changeFaculty(@RequestBody Faculty faculty) {
        Faculty foundfaculty = facultyService.changeFaculty(faculty);
        if (foundfaculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }
    @DeleteMapping("{id}")
    public Faculty removefaculty(@PathVariable Long id) {
        return facultyService.removeFaculty(id);
    }


}
