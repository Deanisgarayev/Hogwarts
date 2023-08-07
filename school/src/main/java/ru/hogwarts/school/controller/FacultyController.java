package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;

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

//    @GetMapping
//    public ResponseEntity<Collection<Faculty>> findByFaculty(@RequestParam String color) {
//        if (color != null && !color.isBlank()) {
//            return ResponseEntity.ok(facultyService.findByColor(color));
//        }
//        return ResponseEntity.ok(Collections.emptyList());
//    }

    @PutMapping
    public ResponseEntity<Faculty> changeFaculty(@RequestBody Faculty faculty) {
        Faculty foundfaculty = facultyService.changeFaculty(faculty);
        if (foundfaculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> removefaculty(@PathVariable Long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }


}
