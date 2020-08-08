package project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import project.dao.ItemDao;
import project.entities.Dish;
import project.entities.common.Cart;
import project.service.DishService;
import project.service.ItemService;
import project.service.ReserveTablesService;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @Mock
    DishService dishService;

    @Mock
    ReserveTablesService reserveTablesService;

    @Mock
    ItemDao itemDao;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void whenCartIsEmptyAndAddFirstDish() {
        HttpSession httpSessionMock = Mockito.mock(HttpSession.class);
        Dish dish = TestUtils.createDish();
        when(dishService.findById(1L)).thenReturn(dish);
        Cart resultCart = itemService.put(1L, httpSessionMock, null);

        assertNotNull(resultCart);
        assertEquals(1, resultCart.getProducts().size());
        assertFalse(resultCart.isReserved());
        assertEquals(resultCart.getProducts().get(0).getDish(), dish);
    }

    @Test
    public void whenDishInCartAndAddThisDishYet() {
        Cart cart = TestUtils.createCart();
        HttpSession httpSessionMock = Mockito.mock(HttpSession.class);
        when(httpSessionMock.getAttribute("cart")).thenReturn(cart);
        Cart resultCart = itemService.put(1L, httpSessionMock, null);

        assertNotNull(resultCart);
        assertEquals(1, resultCart.getProducts().size());
        assertFalse(resultCart.isReserved());
        assertEquals(2, resultCart.getProducts().get(0).getCount());
    }
}
