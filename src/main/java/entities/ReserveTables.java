package entities;

import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "reserve_tables")
public class ReserveTables {

    private Long id;
    private String startTime;
    private String finish_time;
    private Tables table;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    @Column(name = "finish_time")
    public String getFinish_time() {
        return finish_time;
    }

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "tables_id")
    public Tables getTable() {
        return table;
    }

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }
}
