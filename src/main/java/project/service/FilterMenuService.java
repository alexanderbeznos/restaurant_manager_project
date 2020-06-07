package project.service;

import org.springframework.data.jpa.domain.Specification;
import project.entities.Category_;
import project.entities.Dish;
import project.entities.Dish_;
import project.entities.common.FilterMenu;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class FilterMenuService implements Specification<Dish> {

    private FilterMenu filterMenu;

    public FilterMenuService(FilterMenu filterMenu) {
        this.filterMenu = filterMenu;
    }

    @Override
    public Predicate toPredicate(Root<Dish> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Path<Boolean> spicy = root.get(Dish_.spicy);
        Path<Boolean> forVegans = root.get(Dish_.forVegans);
        Path<Boolean> withoutSugar = root.get(Dish_.withoutSugar);
        Path<Boolean> withoutGluten = root.get(Dish_.withoutGluten);
        Path<Long> categoryId = root.get(Dish_.category).get(Category_.id);

        final List<Predicate> predicates = new ArrayList<>();

        if (filterMenu.isSpicy()) {
            predicates.add(criteriaBuilder.isTrue(spicy));
        }
        if (filterMenu.isForVegans()) {
            predicates.add(criteriaBuilder.isTrue(forVegans));
        }
        if (filterMenu.isWithoutSugar()) {
            predicates.add(criteriaBuilder.isTrue(withoutSugar));
        }
        if (filterMenu.isWithoutGluten()) {
            predicates.add(criteriaBuilder.isTrue(withoutGluten));
        }
        if (filterMenu.getCategoryId() != null) {
            predicates.add(criteriaBuilder.equal(categoryId, filterMenu.getCategoryId()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
