package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.CategoryDao;
import project.entities.Category;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryDao categoryDao;


    public Category findById(Long categoryId) {
        Optional<Category> optional = categoryDao.findById(categoryId);
        return optional.orElseThrow(() -> new EntityNotFoundException(String.format("Category with id %s is not found", categoryId)));
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
