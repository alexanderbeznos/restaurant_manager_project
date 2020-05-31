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
    private Boolean vegetarian;
    private Boolean sugar;
    private Boolean gluten;

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

    @Column(name = "vegetarian")
    public Boolean getVegetarian() {
        return vegetarian;
    }

    @Column(name = "sugar")
    public Boolean getSugar() {
        return sugar;
    }

    @Column(name = "gluten")
    public Boolean getGluten() {
        return gluten;
    }
}
