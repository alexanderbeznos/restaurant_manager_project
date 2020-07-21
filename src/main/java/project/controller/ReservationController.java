package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dto.AfterOrderingDto;
import project.dto.OrderingSuccessDto;
import project.service.ReserveTablesService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReserveTablesService reserveTablesService;

    @GetMapping(value = "")
    public String getReservation(Model model, Principal principal) {
        return reserveTablesService.getAllReserves(model, principal);
    }

    @GetMapping(value = "/{id}")
    public String getReservation(@PathVariable("id") Long id, Model model, Principal principal) {
        return reserveTablesService.deleteReserve(id, model, principal);
    }

    @GetMapping(value = "/pre-order")
    public String getPreOrder(Model model, Principal principal) {
        return reserveTablesService.getPreReserve(model, principal);
    }

    @GetMapping(value = "/ordering/{reserveId}")
    public String getPreOrder(@PathVariable("reserveId") Long id, Model model, HttpSession session) {
        return reserveTablesService.ordering(id, model, session);
    }

    @ResponseBody()
    @PostMapping(value = "/order-success")
    public AfterOrderingDto getPreOrder(@RequestBody List<OrderingSuccessDto> orderingSuccessDto, HttpSession session, Principal principal) {
        return reserveTablesService.orderingSuccess(orderingSuccessDto, session, principal);
    }
}
