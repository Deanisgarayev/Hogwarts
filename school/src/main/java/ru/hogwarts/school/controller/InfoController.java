package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.Port;

@RestController
@RequestMapping("/get port")
public class InfoController {
    @GetMapping
    public Port port() {
        return port();

    }
}
