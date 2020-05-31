package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.CategoryDao;
import project.entities.Category;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional
    public Category findById(Long category) {
        Optional<Category> categoryId = categoryDao.findById(category);
        return categoryId.orElseThrow();
    }

    @Transactional
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
