package project.entities;

import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Entity
@Table(name = "menu_category")
@SequenceGenerator(name = "menu_category_id_seq", sequenceName = "menu_category_id_seq", allocationSize = 1)
public class Category {

    private Long id;
    private String name;

    public Category() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_category_id_seq")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
}
