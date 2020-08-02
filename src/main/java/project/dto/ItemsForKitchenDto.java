package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.entities.Dish;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemsForKitchenDto {

    private int number;
    private Dish dish;
    private int count;
    private String servingTime;
    private String difference;
    private String comment;
    private boolean delivery;
}
