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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(tableDto.getStartDate(), formatter);
        LocalDateTime finishDate = LocalDateTime.parse(tableDto.getFinishDate(), formatter);
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
        allTables.forEach(f -> listTables.add(new TableShowDto(Integer.toString(f.getNumberOfTable()), !distinctTables.contains(f.getNumberOfTable()))));
        return listTables;
    }

    public InfoAboutTableDto getInfoAboutTable(String id) {
        int tableId = Integer.parseInt(id.substring(5));
        Tables table = tablesDao.findByNumberOfTable(tableId);
        return new InfoAboutTableDto(tableId, table.getSeats());
    }

    public FindTable getReservationTable(TableDto tableDto, Principal principal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(tableDto.getStartDate(), formatter);
        LocalDateTime finishDate = LocalDateTime.parse(tableDto.getFinishDate(), formatter);
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
        ReserveTables save = reserveTablesDao.save(new ReserveTables(null, startDate, finishDate, tableDto.getSeats(), tableDto.getNumberTable(), user.getId()));
        findTable.setReservationId(save.getId());
        return findTable;
    }

}

