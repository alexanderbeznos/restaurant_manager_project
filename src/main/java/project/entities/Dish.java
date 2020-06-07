package project.entities;

import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "dishes")
@SequenceGenerator(name = "dishes_id_seq", sequenceName = "dishes_id_seq", allocationSize = 1)
public class Dish {

    private Long id;
    private String name;
    private Category category;
    private String description;
    private String ingredients;
    private String photo;
    private String price;
    private String calories;
    private Boolean spicy;
    private Boolean forVegans;
    private Boolean withoutSugar;
    private Boolean withoutGluten;

    public Dish() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dishes_id_seq")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_category_id")
    public Category getCategory() {
        return category;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "ingredients")
    public String getIngredients() {
        return ingredients;
    }

    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    @Column(name = "price")
    public String getPrice() {
        return price;
    }

    @Column(name = "calories")
    public String getCalories() {
        return calories;
    }

    @Column(name = "spicy")
    public Boolean getSpicy() {
        return spicy;
    }

    @Column(name = "for_vegans")
    public Boolean getForVegans() {
        return forVegans;
    }

    @Column(name = "without_sugar")
    public Boolean getWithoutSugar() {
        return withoutSugar;
    }

    @Column(name = "without_gluten")
    public Boolean getWithoutGluten() {
        return withoutGluten;
    }









}
