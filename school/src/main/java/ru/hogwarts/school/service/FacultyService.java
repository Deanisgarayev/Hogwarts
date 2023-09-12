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

    public Collection<Faculty> findAllFaculties() {
    logger.debug("requesting find all faculties");
    return facultyRepository.findAll();
    }
    public Faculty writeFaculty(Faculty faculty) {
        logger.debug("requesting write faculty: {}", faculty);
        return facultyRepository.save(faculty) ;
    }

    public Faculty findFaculty(Long id) {
        logger.debug("requesting find faculty by id: {}", id);
    return facultyRepository.findById(id).get() ;
    }

//    public List<Faculty> findByColor(String color) {
//        List<Faculty> result = new ArrayList<>();
//        return facultyRepository.findByColor(color) ;
//    }

//    public Collection<Faculty> findByPart(String part) {
//        return facultyRepository.findByNameContainsIgnoreCase(part);
//    }
        public Collection<Faculty> findByPart(String name, String color) {
            logger.debug("requesting find faculty by name: {},or color: {}", name, color);
        return facultyRepository.findByNameOrColorIgnoreCase(name, color);
    }
    public Faculty changeFaculty(Faculty faculty) {
        logger.debug("requesting change faculty: {}", faculty);
        return facultyRepository.save(faculty) ;
    }

    public void removeFaculty(Long id) {
        logger.debug("requesting delete faculty by id: {}", id);
    facultyRepository.deleteById(id);
    }
    public Faculty findFacultiesWithLongName() {
    return facultyRepository.findAll().stream()
//            .filter(faculty -> faculty.getName().length()>10)
//            .map(faculty -> faculty.getName())
            .max(Comparator.comparing(faculty -> faculty.getName().length()))
            .orElseThrow(()-> new NotFound("there are not elements"));
//            .collect(Collectors.toList());
    }

}
