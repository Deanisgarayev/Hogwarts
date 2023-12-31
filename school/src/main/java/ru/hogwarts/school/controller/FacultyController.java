package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    public FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

//    add new faculty to the db
    @PostMapping
    public Faculty wrightFaculty(@RequestBody Faculty faculty) {
        return facultyService.writeFaculty(faculty);
    }

//    find faculty by id from the db
    @GetMapping("{id}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

//    find faculty by name or by color from the db
    @GetMapping
    public ResponseEntity<Collection<Faculty>> findFacultyByNaveOrColor(@RequestParam(required = false) String name, @RequestParam(required = false) String color) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findByPart(name, color));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByPart(name, color));
        }
        return ResponseEntity.ok(facultyService.findAllFaculties());
    }

//    edit faculty at the db
    @PutMapping
    public ResponseEntity<Faculty> changeFaculty(@RequestBody Faculty faculty) {
        Faculty foundfaculty = facultyService.changeFaculty(faculty);
        if (foundfaculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }

//    delete faculty from the db
    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> removeFaculty(@PathVariable Long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }

//    finds faculty with the longest name from the db
    @GetMapping("/long_name")
    public Faculty findFacultiesWithLongName() {
        return facultyService.findFacultiesWithLongName();
    }

}
