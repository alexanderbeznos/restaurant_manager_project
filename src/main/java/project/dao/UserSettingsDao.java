package project.dao;

import project.entities.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingsDao extends JpaRepository<UserSettings, Long> {

}
