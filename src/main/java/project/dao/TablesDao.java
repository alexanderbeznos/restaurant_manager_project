package project.dao;

import project.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TablesDao extends JpaRepository<Tables, Long> {
}
