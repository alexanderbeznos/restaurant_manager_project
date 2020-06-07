package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.entities.Dish;

public interface DishDao extends JpaRepository<Dish, Long>, JpaSpecificationExecutor<Dish> {

}
