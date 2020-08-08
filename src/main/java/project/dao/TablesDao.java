package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entities.Tables;

public interface TablesDao extends JpaRepository<Tables, Long> {

    Tables findByNumberOfTable(Integer id);
}
