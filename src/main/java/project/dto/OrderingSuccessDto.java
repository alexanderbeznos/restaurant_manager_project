package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderingSuccessDto {

    private Long dishId;
    private String timeDish;
    private String comment;
    private Long reserveTablesId;
}
