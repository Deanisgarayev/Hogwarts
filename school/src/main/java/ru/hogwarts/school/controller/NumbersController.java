package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.NumberService;

@RestController
@RequestMapping("/numbers")
public class NumbersController {
    NumberService numberService;

    @Autowired
    public NumbersController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping
    public int numbers() {
        return numberService.numbers();
    }
}
