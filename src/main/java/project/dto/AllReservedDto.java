package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AllReservedDto {

    private Long reservedId;
    private String startDate;
    private String finishDate;
    private Integer tableNumber;
    private Integer numberOfPeople;
}
