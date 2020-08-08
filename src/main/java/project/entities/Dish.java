package project.entities;

import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "dishes")
public class Dish {

    private Long id;
    private String name;
    private Category category;
    private String description;
    private String ingredients;
    private String photo;
    private Integer price;
    private String calories;
    private Boolean spicy;
    private Boolean forVegans;
    private Boolean withoutSugar;
    private Boolean withoutGluten;
    private Integer starOne;
    private Integer starTwo;
    private Integer starThree;
    private Integer starFour;
    private Integer starFive;

    public Dish() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @ManyToOne
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
    public Integer getPrice() {
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

    @Column(name = "star_one")
    public Integer getStarOne() {
        return starOne;
    }

    @Column(name = "star_two")
    public Integer getStarTwo() {
        return starTwo;
    }

    @Column(name = "star_three")
    public Integer getStarThree() {
        return starThree;
    }

    @Column(name = "star_four")
    public Integer getStarFour() {
        return starFour;
    }

    @Column(name = "star_five")
    public Integer getStarFive() {
        return starFive;
    }
}
