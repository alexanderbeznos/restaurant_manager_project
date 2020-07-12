package project.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.entities.ReserveTables;
import org.springframework.data.jpa.repository.JpaRepository;
import project.entities.Tables;

import java.time.LocalDateTime;
import java.util.List;

public interface ReserveTablesDao extends JpaRepository<ReserveTables, Long> {

    @Query("select r from ReserveTables r where ((:startDate between r.startTime and r.finishTime) "
            + "or (:finishDate between r.startTime and r.finishTime) or (:startDate < r.startTime and :finishDate > r.finishTime)) "
            + "and r.tableNumber IN :tableNumberList")
    List<ReserveTables> findAllByStartDateFinishDateSeats(@Param("startDate") LocalDateTime startDate,
                                                          @Param("finishDate") LocalDateTime finishDate,
                                                          @Param("tableNumberList") List<Integer> tableNumberList);


    List<ReserveTables> findByUser(Long id);
}
