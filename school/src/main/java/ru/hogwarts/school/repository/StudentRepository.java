package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.entity.AvgAgeOfStudents;
import ru.hogwarts.school.entity.CountStudents;
import ru.hogwarts.school.entity.FiveLastStudents;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);


}
