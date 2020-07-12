package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TableDto {

    private String startDate;
    private String finishDate;
    private Integer seats;
    private Integer numberTable;
}
