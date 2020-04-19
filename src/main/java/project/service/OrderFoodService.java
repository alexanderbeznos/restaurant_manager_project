package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.OrderFoodDao;
import project.entities.OrderFood;

import java.util.List;

@Service
public class OrderFoodService {

    private final OrderFoodDao orderFoodDao;

    @Autowired
    public OrderFoodService(OrderFoodDao orderFoodDao) {
        this.orderFoodDao = orderFoodDao;
    }

    public List<OrderFood> findAll() {
        return orderFoodDao.findAll();
    }

}
