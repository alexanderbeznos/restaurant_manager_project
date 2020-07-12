package project.dao;

import project.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TablesDao extends JpaRepository<Tables, Long> {

    Tables findByNumberOfTable(Integer id);
}
