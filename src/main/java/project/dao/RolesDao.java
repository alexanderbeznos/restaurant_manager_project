package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entities.Roles;

public interface RolesDao extends JpaRepository<Roles, Long> {

    Roles findByName(String name);
}
