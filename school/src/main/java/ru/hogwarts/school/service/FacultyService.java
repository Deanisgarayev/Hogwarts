package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
@Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty writeFaculty(Faculty faculty) {
        return facultyRepository.save(faculty) ;
    }

    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).get() ;
    }

    public List<Faculty> findByColor(String color) {
        List<Faculty> result = new ArrayList<>();
        return facultyRepository.findByColor(color) ;
    }

    public Faculty changeFaculty(Faculty faculty) {
        return facultyRepository.save(faculty) ;
    }

    public void removeFaculty(Long id) {
         facultyRepository.deleteById(id); ;
    }
}
