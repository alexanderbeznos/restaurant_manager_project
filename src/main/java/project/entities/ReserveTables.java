package project.entities;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Setter
@AllArgsConstructor
@Entity
@Table(name = "reserve_tables")
@SequenceGenerator(name = "reserve_tables_id_seq", sequenceName = "reserve_tables_id_seq", allocationSize = 1)
public class ReserveTables {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private Integer numberOfPeople;
    private Integer tableNumber;
    private Long user;
    private String comment;
    private List<OrderFood> orderFood;

    public ReserveTables() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserve_tables_id_seq")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "start_time")
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Column(name = "finish_time")
    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    @Column(name = "number_of_people")
    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }


    @Column(name = "table_number")
    public Integer getTableNumber() {
        return tableNumber;
    }

    @Column(name = "user_id")
    public Long getUser() {
        return user;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    @OneToMany(mappedBy = "reserveTable", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<OrderFood> getOrderFood() {
        return orderFood;
    }
}




