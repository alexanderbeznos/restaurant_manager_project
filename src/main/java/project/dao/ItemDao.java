package project.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import project.entities.Item;

public interface ItemDao extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

    @Query(value = "SELECT * FROM items i where i.take is null",
    nativeQuery = true)
    Page<Item> findAllItemWithPagination(Pageable pageable);
}
