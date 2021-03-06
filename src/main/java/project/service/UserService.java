package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.UserDao;
import project.entities.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;


    public void saveAndFlush(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveAndFlush(user);
    }

    public void saveOrUpdate(User user) {
        userDao.saveAndFlush(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public User findById(Long userId) {
        Optional<User> optional = userDao.findById(userId);
        return optional.orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s is not found", userId)));
    }
}
