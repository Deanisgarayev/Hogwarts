package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.AvgAgeOfStudents;
import ru.hogwarts.school.entity.CountStudents;
import ru.hogwarts.school.entity.FiveLastStudents;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;

import java.util.List;

@Service
public class AvatarService {
    AvatarRepository avatarRepository;
    Logger logger = LoggerFactory.getLogger(AvatarService.class);

    @Autowired
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public AvgAgeOfStudents getAvgAgeOfStudents() {
        logger.debug("requesting avg age of students");
        return avatarRepository.avgAgeOfStudents();
    }

    public CountStudents getCountStudents() {
        logger.debug("requesting count of students");
        return avatarRepository.countStudents();
    }

    public List<FiveLastStudents> getFiveLastStudents() {
        logger.debug("requesting five last students");
        return avatarRepository.fiveLastStudents();
    }

    public List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {
        logger.debug("requesting all students by page number: {}, and size: {}", pageNumber, pageSize);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}
