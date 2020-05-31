package project.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.entities.Category;
import project.entities.Dish;


public interface DishDao extends JpaRepository<Dish, Long> {

    Page<Dish> findAllByCategory(Category category, Pageable  pageable);
}
