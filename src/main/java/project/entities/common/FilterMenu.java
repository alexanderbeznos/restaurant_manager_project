package project.entities.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterMenu {

    private boolean spicy = false;
    private boolean forVegans = false;
    private boolean withoutSugar = false;
    private boolean withoutGluten = false;
    private Long categoryId;

}
