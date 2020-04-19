package project.entities;

import lombok.Setter;
import javax.persistence.*;

@Setter
@Entity
@Table(name = "order_food")
@SequenceGenerator(name = "order_food_id_seq", sequenceName = "order_food_id_seq", allocationSize = 1)
public class OrderFood {

    private Long id;
    private String description;
    private ReserveTables reserveTables;
    private User user;

    public OrderFood() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_food_id_seq")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reserve_tables_id")
    public ReserveTables getReserveTables() {
        return reserveTables;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }
}
