package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty writeFaculty(Faculty faculty) {
        return facultyRepository.save(faculty) ;
    }

    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).get() ;
    }

//    public Collection<Faculty> findByColor(String color) {
//        List<Faculty> result = new ArrayList<>();
//        for (Faculty faculty : faculties.values()) {
//            if (faculty.getColor().equals(color)) {
//                result.add(faculty);
//            }
//        }
//        return facultyRepository.save(faculty) ;
//    }

    public Faculty changeFaculty(Faculty faculty) {
        return facultyRepository.save(faculty) ;
    }

    public void removeFaculty(Long id) {
         facultyRepository.deleteById(id); ;
    }
}
