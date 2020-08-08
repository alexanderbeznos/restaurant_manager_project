package project.dao;

import project.entities.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodDao extends JpaRepository<OrderFood, Long> {

}
