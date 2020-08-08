package project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.service.ItemService;

import javax.servlet.http.HttpSession;
import java.security.Principal;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    public void whenOpenSiteThenReturnMenuPage() throws Exception {
        this.mockMvc.perform(get("/menu/category/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Меню")));
    }

    @Test
    public void whenAnonymousOpenRestaurantPageThenReturnLoginPage() throws Exception {
        this.mockMvc.perform(get("/restaurant"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void whenAddDishToBasket() throws Exception {
        final boolean[] findBy = {false};
        doAnswer(i -> {
            findBy[0] = true;
            return null;
        }).when(itemService).put(any(Long.class), any(HttpSession.class), nullable(Principal.class));
        mockMvc.perform(get("/menu/put/1"))
                .andExpect(status().isOk());
        assertTrue(findBy[0]);
    }
}
