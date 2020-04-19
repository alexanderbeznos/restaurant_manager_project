package project.entities;

import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "reserve_tables")
@SequenceGenerator(name = "reserve_tables_id_seq", sequenceName = "reserve_tables_id_seq", allocationSize = 1)
public class ReserveTables {

    private Long id;
    private String startTime;
    private String finishTime;
    private Tables table;
    private User user;

    public ReserveTables() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserve_tables_id_seq")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    @Column(name = "finish_time")
    public String getFinishTime() {
        return finishTime;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tables_id")
    public Tables getTable() {
        return table;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }
}
