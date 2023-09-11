package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//    public ResponseEntity<Collection<Faculty>> findByColor(@RequestParam String color) {
//        if (color != null && !color.isBlank()) {
//            return ResponseEntity.ok(facultyService.findByColor(color));
//        }
//        return ResponseEntity.ok(Collections.emptyList());
//    }


    @GetMapping
    public ResponseEntity<Collection<Faculty>> findFacultyByNaveOrColor(@RequestParam(required = false) String name, @RequestParam(required = false) String color){
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findByPart(name, color));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByPart(name, color));
        }
        return ResponseEntity.ok(facultyService.findAllFaculties());
    }
//    @GetMapping
//    public ResponseEntity findFaculties(@RequestParam String name, @RequestParam String color, @RequestParam String namePart){
//        if (name!= null && !name.isBlank()) {
//            return ResponseEntity.ok(facultyService.findByPart(name));
//        }
//        if (color!= null && !color.isBlank()) {
//            return ResponseEntity.ok(facultyService.findByPart(color));
//        }
//        if (namePart!= null && !namePart.isBlank()) {
//            return ResponseEntity.ok(facultyService.findByPart(namePart));
//        }
//
//        return ResponseEntity.ok(facultyService.findAllFaculties());
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
    public ResponseEntity<Faculty> removeFaculty(@PathVariable Long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/long_name")
    public List<Faculty> findFacultiesWithLongName() {
        return facultyService.findFacultiesWithLongName();
    }

}
