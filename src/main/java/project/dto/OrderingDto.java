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
public class OrderingDto {

    private int id;
    private Dish dish;
    private int count;
}
