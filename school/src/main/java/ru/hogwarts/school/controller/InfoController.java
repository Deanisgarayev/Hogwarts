package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.Port;

@RestController
@RequestMapping("/info")
public class InfoController {
    @Value("${service.port}")
    private Integer port;
    @GetMapping
    public Integer getPort( ) {
        return port;

    }
}
