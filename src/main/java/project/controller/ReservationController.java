package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.entities.ReserveTables;
import project.service.ReserveTablesService;

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
}
