package project.entities;

import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Entity
@Table(name = "items")
public class Item {

    private Long id;
    private Dish dish;
    private int count;
    private OrderFood order;
    private LocalDateTime servingTime;
    private String comment;
    private Boolean take;


    public Item() {
    }

    public Item(Dish dish, int count) {
        this.dish = dish;
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "serving_time")
    public LocalDateTime getServingTime() {
        return servingTime;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    @Column(name = "take")
    public Boolean getTake() {
        return take;
    }
}
