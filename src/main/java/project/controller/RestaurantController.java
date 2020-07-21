package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dto.*;
import project.entities.ReserveTables;
import project.entities.User;
import project.service.ReserveTablesService;
import project.service.TablesService;
import project.service.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(value = "/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final TablesService tablesService;
    private final ReserveTablesService reserveTablesService;


    @GetMapping(value = "")
    public String showRestaurant() {
        return "restaurant";
    }

    @ResponseBody()
    @PostMapping(value = "/show-tables")
    public List<TableShowDto> showTables(@RequestBody TableDto tableDto) {
        return tablesService.showTables(tableDto);
    }

    @ResponseBody()
    @GetMapping(value = "/about-table/{id}")
    public InfoAboutTableDto showInfoTables(@PathVariable("id") String id) {
        return tablesService.getInfoAboutTable(id);
    }

    @ResponseBody()
    @PostMapping(value = "/after-choose-table")
    public FindTable reservationTable(@RequestBody TableDto tableDto, Principal principal) {
        return tablesService.getReservationTable(tableDto, principal);
    }

    @GetMapping(value = "/success-reservation/{id}")
    public String getSuccessReservation(@PathVariable("id") Long id, Model model) {
        return reserveTablesService.getSuccessReservePage(id, model);

    }
}
