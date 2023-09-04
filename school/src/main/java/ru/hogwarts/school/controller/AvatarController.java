package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.entity.AvgAgeOfStudents;
import ru.hogwarts.school.entity.CountStudents;
import ru.hogwarts.school.entity.FiveLastStudents;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import java.util.List;

@RestController
public class AvatarController {
    AvatarService avatarService;

    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping("/avgAgeOfStudents")
    public AvgAgeOfStudents getAvgAgeOfStudents() {
        return avatarService.getAvgAgeOfStudents();
    }

    @GetMapping("/countStudents")
    public CountStudents getCountStudents() {
        return avatarService.getCountStudents();
    }

    @GetMapping("/fiveLastStudents")
    public List<FiveLastStudents> getFiveLastStudents() {
        return avatarService.getFiveLastStudents();
    }

    @GetMapping("/avatars")
    public ResponseEntity<List<Avatar>> getPagesOfAvatars(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        List<Avatar> avatars = avatarService.getAllAvatars(pageNumber, pageSize);
        return ResponseEntity.ok(avatars);
    }
}