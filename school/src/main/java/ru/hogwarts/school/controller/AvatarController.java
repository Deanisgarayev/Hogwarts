package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.entity.AvgAgeOfStudents;
import ru.hogwarts.school.entity.CountStudents;
import ru.hogwarts.school.entity.FiveLastStudents;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import java.util.List;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    AvatarService avatarService;

    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

//    find average age of students from the db
    @GetMapping("/avgAgeOfStudents")
    public AvgAgeOfStudents getAvgAgeOfStudents() {
        return avatarService.getAvgAgeOfStudents();
    }

//    find total count of students from the db
    @GetMapping("/countStudents")
    public CountStudents getCountStudents() {
        return avatarService.getCountStudents();
    }

//    find the 5 last students on the list from the db
    @GetMapping("/fiveLastStudents")
    public List<FiveLastStudents> getFiveLastStudents() {
        return avatarService.getFiveLastStudents();
    }

//    find all students by pages from the db
    @GetMapping("/avatars")
    public ResponseEntity<List<Avatar>> getPagesOfAvatars(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        List<Avatar> avatars = avatarService.getAllAvatars(pageNumber, pageSize);
        return ResponseEntity.ok(avatars);
    }
}