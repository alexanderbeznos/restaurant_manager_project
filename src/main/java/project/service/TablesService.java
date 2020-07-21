package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import project.dao.ReserveTablesDao;
import project.dao.TablesDao;
import project.dto.FindTable;
import project.dto.InfoAboutTableDto;
import project.dto.TableDto;
import project.dto.TableShowDto;
import project.entities.ReserveTables;
import project.entities.Tables;
import project.entities.User;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@Transactional
@RequiredArgsConstructor
public class TablesService {

    private final TablesDao tablesDao;
    private final ReserveTablesDao reserveTablesDao;
    private final UserService userService;


    public List<TableShowDto> showTables(TableDto tableDto) {
        LocalDateTime startDate = changeStringToLocalDateTime(tableDto.getStartDate());
        LocalDateTime finishDate = changeStringToLocalDateTime(tableDto.getFinishDate());
        List<Tables> allTables = tablesDao.findAll();
        List<Integer> allTablesInt = allTables.stream()
                .filter(f -> f.getSeats() >= tableDto.getSeats())
                .map(Tables::getNumberOfTable)
                .collect(Collectors.toList());

        List<ReserveTables> allReserveTable = reserveTablesDao.findAllByStartDateFinishDateSeats(startDate, finishDate, allTablesInt);
        List<Integer> distinctTables = allReserveTable.stream()
                .map(ReserveTables::getTableNumber)
                .distinct()
                .collect(Collectors.toList());
        List<TableShowDto> listTables = new ArrayList<>();
        allTables.forEach(f -> {
            boolean ready = !distinctTables.contains(f.getNumberOfTable()) && tableDto.getSeats() <= f.getSeats();
            listTables.add(new TableShowDto(Integer.toString(f.getNumberOfTable()), ready));
        });
        return listTables;
    }

    public LocalDateTime changeStringToLocalDateTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return LocalDateTime.parse(time, formatter);
    }

    public InfoAboutTableDto getInfoAboutTable(String id) {
        int tableId = Integer.parseInt(id.substring(5));
        Tables table = tablesDao.findByNumberOfTable(tableId);
        return new InfoAboutTableDto(tableId, table.getSeats());
    }

    public FindTable getReservationTable(TableDto tableDto, Principal principal) {
        LocalDateTime startDate = changeStringToLocalDateTime(tableDto.getStartDate());
        LocalDateTime finishDate = changeStringToLocalDateTime(tableDto.getFinishDate());
        List<Integer> allTablesInt = new ArrayList<>();
        allTablesInt.add(tableDto.getNumberTable());
        List<ReserveTables> reserveTable = reserveTablesDao.findAllByStartDateFinishDateSeats(startDate, finishDate, allTablesInt);
        FindTable findTable = new FindTable();
        if (reserveTable.size() > 0) {
            findTable.setReservation(false);
            return findTable;
        }
        String login = principal.getName();
        User user = userService.findByLogin(login);
        ReserveTables save = reserveTablesDao.save(new ReserveTables(null, startDate, finishDate, tableDto.getSeats(), tableDto.getNumberTable(), user.getId(), tableDto.getComment(), null));
        findTable.setReservationId(save.getId());
        return findTable;
    }

}

