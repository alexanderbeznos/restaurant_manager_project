package project.entities;

import lombok.Setter;
import project.entities.common.OrderType;

import javax.persistence.*;
import java.util.List;

@Setter
@Entity
@Table(name = "order_food")
public class OrderFood {

    private Long id;
    private OrderType orderType;
    private User user;
    private ReserveTables reserveTable;
    private String nameOfUser;
    private String address;
    private String phone;
    private String description;
    private List<Item> items;

    public OrderFood() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Column(name = "order_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public OrderType getOrderType() {
        return orderType;
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    @ManyToOne
    @JoinColumn(name = "reserved_table_id")
    public ReserveTables getReserveTable() {
        return reserveTable;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }


    @Column(name = "name_of_user")
    public String getNameOfUser() {
        return nameOfUser;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Item> getItems() {
        return items;
    }
}
