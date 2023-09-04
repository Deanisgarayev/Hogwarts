package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.entity.AvgAgeOfStudents;
import ru.hogwarts.school.entity.CountStudents;
import ru.hogwarts.school.entity.FiveLastStudents;
import ru.hogwarts.school.model.Avatar;

import java.util.List;
import java.util.Optional;



public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);
    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    CountStudents countStudents();

    @Query(value = "SELECT avg(age) as age FROM student ", nativeQuery = true)
    AvgAgeOfStudents avgAgeOfStudents();
    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<FiveLastStudents> fiveLastStudents();
}
