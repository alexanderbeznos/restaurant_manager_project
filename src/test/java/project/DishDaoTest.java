package project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import project.dao.DishDao;
import project.entities.Dish;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class DishDaoTest {

    @Resource
    DishDao dishDao;

    @Test
    public void whenDishIsPresent() {
        Optional<Dish> optional = dishDao.findById(1L);
        assertTrue(optional.isPresent());
    }

    @Test
    public void whenSaveDish() {
        Dish dish = new Dish();
        Dish dishSaved = dishDao.save(dish);
        assertNotNull(dishSaved.getId());
    }

    @Test
    public void whenDeleteDish() {
        Dish dish = new Dish();
        dishDao.save(dish);
        dishDao.delete(dish);
        Optional<Dish> optionalAfter = dishDao.findById(dish.getId());
        assertFalse(optionalAfter.isPresent());

    }
}
