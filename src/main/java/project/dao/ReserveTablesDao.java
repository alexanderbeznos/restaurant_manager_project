package project.dao;

import project.entities.ReserveTables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveTablesDao extends JpaRepository<ReserveTables, Long> {
}
