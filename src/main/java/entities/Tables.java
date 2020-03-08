package entities;

import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "tables")
public class Tables {

    private Long id;
    private Integer numberOfTable;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "number_of_table")
    public Integer getNumberOfTable() {
        return numberOfTable;
    }
}
