package entities;

import lombok.Setter;
import javax.persistence.*;

@Setter
@Entity
@Table(name = "order_food")
public class OrderFood {

    private Long id;
    private String description;
    private ReserveTables reserveTables;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "reserve_tables_id")
    public ReserveTables getReserveTables() {
        return reserveTables;
    }

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }
}
