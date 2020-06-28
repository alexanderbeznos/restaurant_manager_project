package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.UserSettingsDao;
import project.entities.UserSettings;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserSettingsService {

    private final UserSettingsDao userSettingsDao;


    public UserSettings findById(Long userSettingsId) {
        Optional<UserSettings> optional = userSettingsDao.findById(userSettingsId);
        return optional.orElseThrow(() -> new EntityNotFoundException(String.format("UserSettings with id %s is not found", userSettingsId)));
    }

    public void saveAndFlush(UserSettings userSettings) {
        userSettingsDao.saveAndFlush(userSettings);
    }
}
