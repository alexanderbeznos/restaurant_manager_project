package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.DishDao;
import project.entities.Category;
import project.entities.Dish;


@Service
public class DishService {

    private final DishDao dishDao;

    @Autowired
    public DishService(DishDao dishDao) {
        this.dishDao = dishDao;
    }


    @Transactional
    public Page<Dish> findAll(FilterMenuService filterMenuService, Pageable  pageable) {
        return dishDao.findAll(filterMenuService, pageable);
    }


}
