package project.entities;

import lombok.Getter;
import lombok.Setter;
import project.entities.Dish;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "items")
@SequenceGenerator(name = "items_id_seq", sequenceName = "items_id_seq", allocationSize = 1)
public class Item {

    private Long id;
    private Dish dish;
    private int count;
    private OrderFood order;
    private String comment;
    private Boolean done;


    public Item() {
    }

    public Item(Dish dish, int count) {
        this.dish = dish;
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "items_id_seq")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "dish_id")
    public Dish getDish() {
        return dish;
    }

    @Column(name = "count")
    public int getCount() {
        return count;
    }

    @ManyToOne
    @JoinColumn(name = "order_food_id")
    public OrderFood getOrder() {
        return order;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    @Column(name = "done")
    public Boolean getDone() {
        return done;
    }
}
