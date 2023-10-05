package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.NotFound;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

//    finds all faculties from the db
    public Collection<Faculty> findAllFaculties() {
        logger.debug("requesting find all faculties");
        return facultyRepository.findAll();
    }

//    adds new faculty to the db
    public Faculty writeFaculty(Faculty faculty) {
        logger.debug("requesting write faculty: {}", faculty);
        return facultyRepository.save(faculty);
    }

//    finds faculty by id from the db
    public Faculty findFaculty(Long id) {
        logger.debug("requesting find faculty by id: {}", id);
        return facultyRepository.findById(id).get();
    }

//    finds faculty by name or by color from the db
    public Collection<Faculty> findByPart(String name, String color) {
        logger.debug("requesting find faculty by name: {},or color: {}", name, color);
        return facultyRepository.findByNameOrColorIgnoreCase(name, color);
    }

//    edits faculty at the db
    public Faculty changeFaculty(Faculty faculty) {
        logger.debug("requesting change faculty: {}", faculty);
        return facultyRepository.save(faculty);
    }

//    deletes faculty from the db
    public void removeFaculty(Long id) {
        logger.debug("requesting delete faculty by id: {}", id);
        facultyRepository.deleteById(id);
    }

//    finds faculty with the longest name from the db
    public Faculty findFacultiesWithLongName() {
        return facultyRepository.findAll().stream()
                .max(Comparator.comparing(faculty -> faculty.getName().length()))
                .orElseThrow(() -> new NotFound("there are not elements"));
    }

}
