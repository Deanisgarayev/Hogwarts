package ru.hogwarts.school.service;

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

    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }
    public AvgAgeOfStudents getAvgAgeOfStudents() {
        return avatarRepository.avgAgeOfStudents();
    }

    public CountStudents getCountStudents() {
        return avatarRepository.countStudents();
    }

    public List<FiveLastStudents> getFiveLastStudents() {
        return avatarRepository.fiveLastStudents();
    }
    public List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}
