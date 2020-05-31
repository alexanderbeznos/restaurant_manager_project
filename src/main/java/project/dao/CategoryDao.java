package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entities.Category;

import java.util.Optional;

public interface CategoryDao extends JpaRepository<Category, Long> {

     Optional<Category> findById(Long id);
}
