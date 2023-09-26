package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.NotFound;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

//finds all faculties from db
    public Collection<Faculty> findAllFaculties() {
        logger.debug("requesting find all faculties");
        return facultyRepository.findAll();
    }

//    adds new faculty to db
    public Faculty writeFaculty(Faculty faculty) {
        logger.debug("requesting write faculty: {}", faculty);
        return facultyRepository.save(faculty);
    }

//finds faculty by id from db
    public Faculty findFaculty(Long id) {
        logger.debug("requesting find faculty by id: {}", id);
        return facultyRepository.findById(id).get();
    }

//    find faculty by name or by color from db
    public Collection<Faculty> findByPart(String name, String color) {
        logger.debug("requesting find faculty by name: {},or color: {}", name, color);
        return facultyRepository.findByNameOrColorIgnoreCase(name, color);
    }

//    edit faculty at db
    public Faculty changeFaculty(Faculty faculty) {
        logger.debug("requesting change faculty: {}", faculty);
        return facultyRepository.save(faculty);
    }

//    delete faculty from db
    public void removeFaculty(Long id) {
        logger.debug("requesting delete faculty by id: {}", id);
        facultyRepository.deleteById(id);
    }
//finds faculty with the longest name
    public Faculty findFacultiesWithLongName() {
        return facultyRepository.findAll().stream()
                .max(Comparator.comparing(faculty -> faculty.getName().length()))
                .orElseThrow(() -> new NotFound("there are not elements"));
    }

}
