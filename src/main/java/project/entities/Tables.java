package project.entities;

import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "tables")
@SequenceGenerator(name = "tables_id_seq", sequenceName = "tables_id_seq", allocationSize = 1)
public class Tables {

    private Long id;
    private Integer numberOfTable;
    private Integer seats;


    public Tables() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tables_id_seq")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "number_of_table")
    public Integer getNumberOfTable() {
        return numberOfTable;
    }

    @Column(name = "seats")
    public Integer getSeats() {
        return seats;
    }
}
