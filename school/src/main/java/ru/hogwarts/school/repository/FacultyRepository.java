package ru.hogwarts.school.repository;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
//    List<Faculty> findByColor(String faculty);

    Collection<Faculty> findByNameOrColorIgnoreCase(String name, String color);

//    Collection<Faculty> findAllByNameContainsIgnoreCase(String part);

}
