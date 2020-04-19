package project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.UserSettingsDao;
import project.entities.UserSettings;



@Service
public class UserSettingsService {

    private final UserSettingsDao userSettingsDao;

    public UserSettingsService(UserSettingsDao userSettingsDao) {
        this.userSettingsDao = userSettingsDao;
    }

    @Transactional
    public UserSettings findById(Long id) {
        return userSettingsDao.findById(id).orElse(null);
    }

    @Transactional
    public void saveAndFlush(UserSettings userSettings) {
        userSettingsDao.saveAndFlush(userSettings);
    }
}
