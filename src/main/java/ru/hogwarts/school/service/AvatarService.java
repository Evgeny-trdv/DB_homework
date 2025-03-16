package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.Collection;

public interface AvatarService {
    void uploadAvatar(Long longId, MultipartFile avatarFile) throws IOException;

    Avatar findAvatar(Long longId);

    Collection<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize);
}
