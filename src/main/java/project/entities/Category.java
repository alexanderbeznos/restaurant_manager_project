package project.entities;

import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "menu_category")
public class Category {

    private Long id;
    private String name;

    public Category() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
}
