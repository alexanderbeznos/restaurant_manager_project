package project.entities;

import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Setter
@Entity
@Table(name = "order_food")
@SequenceGenerator(name = "order_food_id_seq", sequenceName = "order_food_id_seq", allocationSize = 1)
public class OrderFood {

    private Long id;
    private User user;
    private String nameOfUser;
    private String address;
    private String phone;
    private String description;
    private List<Item> listItems;

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

    @OneToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
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
    public List<Item> getListItems() {
        return listItems;
    }
}
